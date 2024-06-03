package com.gamth.tfgdaniel.controlador;

import com.gamth.tfgdaniel.modeloDTO.JwtAuthenticationResponse;
import com.gamth.tfgdaniel.modeloDTO.RequestLogin;
import com.gamth.tfgdaniel.modeloDTO.RequestRegister;
import com.gamth.tfgdaniel.servicio.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControllerAuthentication {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody RequestLogin request){
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody RequestRegister request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }

}
