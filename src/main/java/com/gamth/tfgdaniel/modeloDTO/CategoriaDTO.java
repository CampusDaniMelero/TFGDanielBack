package com.gamth.tfgdaniel.modeloDTO;

import com.gamth.tfgdaniel.modelo.Categoria;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaDTO {

    private int idCategoria;

    @NotEmpty
    @Size(min = 2, max = 80)
    private String nombre;

    public Categoria castCategoria(){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(this.idCategoria);
        categoria.setNombre(this.nombre);
        return categoria;
    }

    public CategoriaDTO castCategoriaADto(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(categoria.getIdCategoria());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }


}
