package com.gamth.tfgdaniel.controlador;

import com.gamth.tfgdaniel.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.modeloDTO.UsuarioDTO;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {


    @Qualifier("userServiceImpl")
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> consultarTodos(){
    List<Usuario> userBBDD=usuarioService.consultarTodos();
    List<UsuarioDTO> userDTO= new ArrayList<>();
    for(Usuario user:userBBDD){
        UsuarioDTO usersDTO=new UsuarioDTO();
        userDTO.add(usersDTO.castUsuarioADto(user));

    }
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO>consultarUno(@PathVariable("idUsuario") Integer idUsuario) {
        Usuario u = usuarioService.consultarUno(idUsuario);
        if (u == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Usuario" + idUsuario + " no encontrado");
        }
        return new ResponseEntity<>(new UsuarioDTO().castUsuarioADto(u), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioDTO u){
        Usuario u1=u.castUsuario();
        u1=usuarioService.crear(u1);
        return new ResponseEntity<>(new UsuarioDTO().castUsuarioADto(u1),HttpStatus.CREATED);
    }


    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UsuarioDTO u){
        Usuario u1=u.castUsuario();
        u1.setIdUsuario(idUsuario);
        u1=usuarioService.modificar(u1);
        return new ResponseEntity<>(new UsuarioDTO().castUsuarioADto(u1),HttpStatus.OK);
    }


    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable ("idUsuario") Integer idUsuario){
        if(usuarioService.consultarUno(idUsuario)!=null){
            usuarioService.eliminar(idUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ExcepcionPersonalizadaNoEncontrado("Usuario no encontrado");
        }
    }

    @PutMapping("/actual/{idUsuario}/password")
    public ResponseEntity<Void> actualizarPassword(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Map<String, String> passwordMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        Usuario usuario = usuarioService.consultarUno(idUsuario);

        if (usuario == null || !usuario.getApodo().equals(authenticatedUsername)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String nuevaPassword = passwordMap.get("nuevaPassword");
        if (nuevaPassword == null || nuevaPassword.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        usuarioService.actualizarPassword(idUsuario, nuevaPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/actual/{idUsuario}/fotoPerfil")
    public ResponseEntity<Void> actualizarFotoPerfil(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Map<String, String> fotoPerfilMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        Usuario usuario = usuarioService.consultarUno(idUsuario);

        if (usuario == null || !usuario.getApodo().equals(authenticatedUsername)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String nuevaFotoPerfil = fotoPerfilMap.get("fotoPerfil");
        if (nuevaFotoPerfil == null || nuevaFotoPerfil.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        usuario.setImgPerfil(nuevaFotoPerfil);
        usuarioService.modificar(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/actual/{idUsuario}")
    public ResponseEntity<Void> actualizarUsuarioAutenticado(@PathVariable("idUsuario") Integer idUsuario, @RequestBody UsuarioDTO u) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        Usuario usuario = usuarioService.consultarUno(idUsuario);

        if (usuario == null || !usuario.getApodo().equals(authenticatedUsername)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Guardar el antiguo apodo
        String oldApodo = usuario.getApodo();

        if (u.getNombre() != null && !u.getNombre().isEmpty()) {
            usuario.setNombre(u.getNombre());
        }
        if (u.getApodo() != null && !u.getApodo().isEmpty()) {
            usuario.setApodo(u.getApodo());
        }
        if (u.getCorreo() != null && !u.getCorreo().isEmpty()) {
            usuario.setCorreo(u.getCorreo());
        }
        if (u.getPassword() != null && !u.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(u.getPassword()));
        }
        if (u.getImgPerfil() != null && !u.getImgPerfil().isEmpty()) {
            usuario.setImgPerfil(u.getImgPerfil());
        }


        // Actualizar el contexto de seguridad con el nuevo apodo
        if (!oldApodo.equals(u.getApodo())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(u.getApodo());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/actual/{idUsuario}")
    public ResponseEntity<Void> eliminarUsuarioAutenticado(@PathVariable("idUsuario") Integer idUsuario) {
        Usuario usuario = usuarioService.consultarUno(idUsuario);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        usuarioService.eliminar(usuario.getIdUsuario());
        SecurityContextHolder.clearContext(); // Opcionalmente limpiar el contexto de seguridad para cerrar sesión
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/porapodo")
    public ResponseEntity<Optional<Usuario>> findByApodo(@RequestParam(name = "apodo") String apodo) {
        Optional<Usuario> usuario = usuarioService.findByApodo(apodo);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String apodo = authentication.getName();
        Optional<Usuario> usuario = usuarioService.findByApodo(apodo);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/porapodolike")
    public ResponseEntity<List<Usuario>> findByApodoLike(@RequestParam(name = "apodo") String apodo) {
        List<Usuario> usuarios = usuarioService.findByApodoLike(apodo);
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }


}
