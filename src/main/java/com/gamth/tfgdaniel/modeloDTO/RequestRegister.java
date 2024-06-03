package com.gamth.tfgdaniel.modeloDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestRegister {
    private String nombre;
    private String apodo;
    private String correo;
    private String password;
    private String imgPerfil;
}
