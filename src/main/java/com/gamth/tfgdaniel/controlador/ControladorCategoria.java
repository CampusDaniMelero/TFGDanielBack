package com.gamth.tfgdaniel.controlador;


import com.gamth.tfgdaniel.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.gamth.tfgdaniel.modelo.Categoria;
import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.modeloDTO.CategoriaDTO;
import com.gamth.tfgdaniel.modeloDTO.UsuarioDTO;
import com.gamth.tfgdaniel.servicio.ICategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCategoria {
    @Autowired
    private ICategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> consultarTodos(){
        List<Categoria> categoriaBBDD=categoriaService.consultarTodos();
        List<CategoriaDTO> categoriaDTO= new ArrayList<>();
        for(Categoria categoria:categoriaBBDD){
            CategoriaDTO categoriaDTOs=new CategoriaDTO();
            categoriaDTO.add(categoriaDTOs.castCategoriaADto(categoria));
        }
        return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO>consultarUno(@PathVariable("idCategoria") Integer idCategoria) {
        Categoria u = categoriaService.consultarUno(idCategoria);
        if (u == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Categoria" + idCategoria + " no encontrado");
        }
        return new ResponseEntity<>(new CategoriaDTO().castCategoriaADto(u), HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody CategoriaDTO u){
        Categoria u1=u.castCategoria();
        u1=categoriaService.crear(u1);
        return new ResponseEntity<>(new CategoriaDTO().castCategoriaADto(u1),HttpStatus.CREATED);

    }



    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable ("idCategoria") int idCategoria){
        if (categoriaService.consultarUno(idCategoria)!=null){
            categoriaService.eliminar(idCategoria);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


}
    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable("idCategoria") int idCategoria, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaDTO.castCategoria();
        categoria.setIdCategoria(idCategoria);
        Categoria categoriaActualizada = categoriaService.modificar(categoria);
        CategoriaDTO categoriaActualizadaDTO = new CategoriaDTO().castCategoriaADto(categoriaActualizada);
        return new ResponseEntity<>(categoriaActualizadaDTO, HttpStatus.OK);
    }
}
