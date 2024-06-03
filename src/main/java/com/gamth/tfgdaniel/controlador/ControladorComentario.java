package com.gamth.tfgdaniel.controlador;

import com.gamth.tfgdaniel.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.gamth.tfgdaniel.modelo.Comentario;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.modeloDTO.ComentarioDTO;
import com.gamth.tfgdaniel.servicio.IComentarioService;
import com.gamth.tfgdaniel.servicio.IPostService;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/comentarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorComentario {
    @Autowired
    private IComentarioService comentarioService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> consultarTodos(){
        List<Comentario> comentariosBBDD=comentarioService.consultarTodos();
        List<ComentarioDTO> comentariosDTO= new ArrayList<>();
        for(Comentario comentario:comentariosBBDD){
            ComentarioDTO comentarioDTO=new ComentarioDTO();
            comentariosDTO.add(comentarioDTO.castComentarioADto(comentario));
        }
        return new ResponseEntity<>(comentariosDTO, HttpStatus.OK);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<ComentarioDTO> consultarUno(@PathVariable("idComentario") Integer idComentario) {
        Comentario c = comentarioService.consultarUno(idComentario);
        if (c == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Comentario" + idComentario + " no encontrado");
        }
        return new ResponseEntity<>(new ComentarioDTO().castComentarioADto(c), HttpStatus.OK);

    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        Usuario usuario = usuarioService.consultarUno(idUsuario);
        if (usuario == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Usuario " + idUsuario + " no encontrado");
        }

        List<Comentario> comentariosBBDD = comentarioService.findByIdUsuario(idUsuario);
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();

        for (Comentario comentario : comentariosBBDD) {
            ComentarioDTO comentarioDTO = new ComentarioDTO();
            comentariosDTO.add(comentarioDTO.castComentarioADto(comentario));
        }

        return new ResponseEntity<>(comentariosDTO, HttpStatus.OK);
    }

    @GetMapping("/post/{idPost}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByPost(@PathVariable("idPost") Integer idPost) {
        Optional<Post> postOptional = Optional.ofNullable(postService.consultarUno(idPost));
        if (!postOptional.isPresent()) {
            throw new ExcepcionPersonalizadaNoEncontrado("Post " + idPost + " no encontrado");
        }

        List<Comentario> comentariosBBDD = comentarioService.findByIdPost(idPost);
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();

        for (Comentario comentario : comentariosBBDD) {
            ComentarioDTO comentarioDTO = new ComentarioDTO();
            comentariosDTO.add(comentarioDTO.castComentarioADto(comentario));
        }

        return new ResponseEntity<>(comentariosDTO, HttpStatus.OK);
    }

    @GetMapping("/post/{idPost}/usuario/{idUsuario}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByPostAndUsuario(
            @PathVariable("idPost") Integer idPost,
            @PathVariable("idUsuario") Integer idUsuario) {

        Optional<Post> postOptional = Optional.ofNullable(postService.consultarUno(idPost));
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioService.consultarUno(idUsuario));

        if (postOptional.isEmpty() || usuarioOptional.isEmpty()) {
            throw new ExcepcionPersonalizadaNoEncontrado("No se encontró el Post o el Usuario especificado");
        }

        List<Comentario> comentariosBBDD = comentarioService.findByIdPostAndIdUsuario(idPost, idUsuario);
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();

        for (Comentario comentario : comentariosBBDD) {
            ComentarioDTO comentarioDTO = new ComentarioDTO();
            comentariosDTO.add(comentarioDTO.castComentarioADto(comentario));
        }

        return new ResponseEntity<>(comentariosDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> agregarComentario(@RequestBody ComentarioDTO comentarioDTO) {

        Comentario comentario = comentarioDTO.castComentario();

        Comentario nuevoComentario = comentarioService.crear(comentario);

        ComentarioDTO nuevoComentarioDTO = new ComentarioDTO().castComentarioADto(nuevoComentario);

        return new ResponseEntity<>(nuevoComentarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idComentario}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(
            @PathVariable("idComentario") Integer idComentario,
            @RequestBody ComentarioDTO comentarioDTO) {

        Comentario comentarioExistente = comentarioService.consultarUno(idComentario);
        if (comentarioExistente == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Comentario " + idComentario + " no encontrado");
        }



        Comentario comentarioActualizado = comentarioService.modificar(comentarioExistente);

        ComentarioDTO comentarioActualizadoDTO = new ComentarioDTO().castComentarioADto(comentarioActualizado);

        return new ResponseEntity<>(comentarioActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<ComentarioDTO> eliminarComentario(@PathVariable("idComentario") Integer idComentario) {
        Comentario comentario = comentarioService.consultarUno(idComentario);
        if (comentario == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Comentario " + idComentario + " no encontrado");
        }

        comentarioService.eliminar(idComentario);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}