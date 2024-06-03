package com.gamth.tfgdaniel.controlador;

import com.gamth.tfgdaniel.modelo.Like;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.modeloDTO.LikeDTO;
import com.gamth.tfgdaniel.servicio.ILikeService;
import com.gamth.tfgdaniel.servicio.IPostService;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/likes")
public class ControladorLike {

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPostService postService;

    // Método para obtener todos los likes
    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes() {
        List<Like> likes = likeService.consultarTodos();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Método para obtener los likes de un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Like>> getLikesByUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        List<Like> likes = likeService.findByUsuarioIdUsuario(idUsuario);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Método para obtener los likes de un post específico
    @GetMapping("/post/{idPost}")
    public ResponseEntity<List<Like>> getLikesByPost(@PathVariable("idPost") Integer idPost) {
        List<Like> likes = likeService.findByPostIdPost(idPost);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Método para obtener los likes de un usuario en un post específico
    @GetMapping("/usuario/{idUsuario}/post/{idPost}")
    public ResponseEntity<List<Like>> getLikesByUsuarioAndPost(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idPost") Integer idPost) {
        List<Like> likes = likeService.findByUsuarioIdUsuarioAndPostIdPost(idUsuario, idPost);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody LikeDTO likeDTO) {
        // Obtener el usuario y el post por sus IDs
        Usuario usuario = usuarioService.consultarUno(likeDTO.getIdUsuario());
        Post post = postService.consultarUno(likeDTO.getIdPost());

        // Verificar si el usuario y el post existen
        if (usuario == null || post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Usuario o post no encontrado
        }

        // Crear el nuevo like con los objetos completos de usuario y post
        Like like = new Like();
        like.setIdUsuario(usuario);
        like.setIdPost(post);

        // Verificar si el like ya existe
        List<Like> existingLike = likeService.findByUsuarioIdUsuarioAndPostIdPost(likeDTO.getIdUsuario(), likeDTO.getIdPost());
        if (!existingLike.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // El like ya existe
        }

        // Crear el nuevo like
        Like likeCreado = likeService.crear(like);
        return new ResponseEntity<>(likeCreado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idLike}")
    public ResponseEntity<Void> deleteLike(@PathVariable("idLike") Integer idLike) {
        Like like = likeService.consultarUno(idLike);
        if (like == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Like not found
        }
        likeService.eliminar(idLike);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
