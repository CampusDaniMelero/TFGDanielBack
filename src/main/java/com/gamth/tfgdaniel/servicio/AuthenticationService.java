package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modeloDTO.JwtAuthenticationResponse;
import com.gamth.tfgdaniel.modeloDTO.RequestLogin;
import com.gamth.tfgdaniel.modeloDTO.RequestRegister;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {

    JwtAuthenticationResponse signin( RequestLogin request);

    JwtAuthenticationResponse signup(RequestRegister request);

}
