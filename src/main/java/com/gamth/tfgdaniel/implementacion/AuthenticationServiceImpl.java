package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.modeloDTO.JwtAuthenticationResponse;
import com.gamth.tfgdaniel.modeloDTO.RequestLogin;
import com.gamth.tfgdaniel.modeloDTO.RequestRegister;
import com.gamth.tfgdaniel.repositorio.IUsuarioRepository;
import com.gamth.tfgdaniel.servicio.AuthenticationService;
import com.gamth.tfgdaniel.servicio.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IUsuarioRepository iUsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public JwtAuthenticationResponse signup(RequestRegister request){
        Usuario usuario=Usuario.builder().nombre(request.getNombre()).apodo(request.getApodo()).correo(request.getCorreo()).password(passwordEncoder.encode(request.getPassword())).imgPerfil(request.getImgPerfil()).build();

        iUsuarioRepository.save(usuario);
        var jwt= jwtService.generateToken(usuario);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(RequestLogin request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getApodo(), request.getPassword()));

         var usuario=iUsuarioRepository.findByApodo(request.getApodo()).orElseThrow(() -> new IllegalArgumentException("Usuario o contraseña no valido"));

            var jwt= jwtService.generateToken((UserDetails) usuario);
            return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
