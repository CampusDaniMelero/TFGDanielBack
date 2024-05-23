package com.gamth.tfgdaniel.controlador;


import com.gamth.tfgdaniel.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> consultarTodos(){
        return new ResponseEntity<>(usuarioService.consultarTodos(), HttpStatus.OK);



    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarUno(@PathVariable int id){
        if(usuarioService.consultarUno(id)!=null){
            return new ResponseEntity<>(usuarioService.consultarUno(id), HttpStatus.OK);
        }else{
            throw new ExcepcionPersonalizadaNoEncontrado("Usuario no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.crear(usuario), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> modificar(@Validated @RequestBody Usuario usuario){
       Usuario usuarioModify=usuarioService.consultarUno(usuario.getIdUsuario());
       if(usuarioModify==null){
              throw new ExcepcionPersonalizadaNoEncontrado("Usuario no encontrado");
       }
       usuarioModify.setIdUsuario(usuario.getIdUsuario());
       usuarioModify.setNombre(usuario.getNombre());
         usuarioModify.setApodo(usuario.getApodo());
         usuarioModify.setCorreo(usuario.getCorreo());
         usuarioModify.setPassword(usuario.getPassword());
         usuarioModify.setImgPerfil(usuario.getImgPerfil());
         usuarioModify.setFechaRegistro(usuario.getFechaRegistro());

            return new ResponseEntity<>(usuarioService.modificar(usuarioModify), HttpStatus.OK);


}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable ("id") int id){
        if(usuarioService.consultarUno(id)!=null){
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            throw new ExcepcionPersonalizadaNoEncontrado("Usuario no encontrado");
        }
    }
}
