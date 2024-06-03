package com.gamth.tfgdaniel.modeloDTO;


import com.gamth.tfgdaniel.modelo.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.MalformedURLException;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
    private int idUsuario;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String nombre;

    @NotEmpty
    @Size(min = 8, max = 100)
    @Column(length = 100 , nullable = false , unique = true)
    private String apodo;

    @NotEmpty
    @Column(length = 150 , nullable = false, unique = true)
    @Email
    private String correo;

    @NotEmpty
    @Column(length = 100 , nullable = false)
    private String password;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;


    @URL
    private String imgPerfil;



    public Usuario castUsuario(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(this.idUsuario);
        usuario.setNombre(this.nombre);
        usuario.setApodo(this.apodo);
        usuario.setCorreo(this.correo);
        usuario.setPassword(this.password);
       usuario.setImgPerfil(this.imgPerfil);
        return usuario;
    }



    public UsuarioDTO castUsuarioADto(Usuario u){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(u.getIdUsuario());
        usuarioDTO.setNombre(u.getNombre());
        usuarioDTO.setApodo(u.getApodo());
        usuarioDTO.setPassword(u.getPassword());
        usuarioDTO.setCorreo(u.getCorreo());
        usuarioDTO.setImgPerfil(u.getImgPerfil());
        return usuarioDTO;
    }
}
