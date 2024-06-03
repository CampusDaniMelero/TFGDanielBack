package com.gamth.tfgdaniel.controlador;

import com.gamth.tfgdaniel.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.gamth.tfgdaniel.modelo.Categoria;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.modeloDTO.PostDTO;
import com.gamth.tfgdaniel.servicio.ICategoriaService;
import com.gamth.tfgdaniel.servicio.IPostService;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPost {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> consultarTodos(){
        List<Post> postBBDD=postService.consultarTodos();
        List<PostDTO> postDTO= new ArrayList<>();
        for(Post post:postBBDD){
            PostDTO postsDTO=new PostDTO();
            postDTO.add(postsDTO.castPostADto(post));
        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{idPost}")
    public ResponseEntity<Void> eliminarPost(@PathVariable ("idPost") int idPost){
        if (postService.consultarUno(idPost)!=null){
            postService.eliminar(idPost);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
            throw new ExcepcionPersonalizadaNoEncontrado("Post no encontrado");
        }
    }


    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PostDTO>> consultarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(idUsuario);
        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Post> postBBDD = postService.consultarTodos();
        List<PostDTO> postDTO = new ArrayList<>();
        for (Post post : postBBDD) {
            if (post.getIdUsuario() != null && post.getIdUsuario().getIdUsuario() == idUsuario) {
                PostDTO postsDTO = new PostDTO();
                postDTO.add(postsDTO.castPostADto(post));
            }
        }

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> insertar(@Valid @RequestBody PostDTO p) {
        Post p1 = new Post();
        p1.setDescripcionPost(p.getDescripcionPost());
        p1.setNombreVideojuego(p.getNombreVideojuego());

        if (p.getImgPost1() != null) {
            try {
                p1.setImgPost1(new URL(p.getImgPost1()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }



        // Verificar que el usuario existe en la base de datos
        Optional<Usuario> usuarioOpt = usuarioService.findById(p.getIdUsuario());
        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Asignar usuario al post
        p1.setIdUsuario(usuarioOpt.get());

        // Verificar y asignar categorías
        if (p.getCategorias() == null) {
            p.setCategorias(new ArrayList<>());
        }
        p1.setCategorias(new ArrayList<>());

        // Crear el post
        p1 = postService.crear(p1);

        return new ResponseEntity<>(new PostDTO().castPostADto(p1), HttpStatus.CREATED);
    }





    @GetMapping("/{idPost}")
    public ResponseEntity<PostDTO>consultarUno(@PathVariable("idPost") Integer idPost) {
        Post p = postService.consultarUno(idPost);
        if (p == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Post" + idPost + " no encontrado");
        }
        return new ResponseEntity<>(new PostDTO().castPostADto(p), HttpStatus.OK);

    }

    @PutMapping // Modificar un post
    public ResponseEntity<PostDTO> modificar(@RequestBody PostDTO p) {
        Post p1 = postService.consultarUno(p.getIdPost());
        if (p1 == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("Post no encontrado: " + p.getIdPost());
        }

        // Verificar que el post pertenece al usuario especificado
        if (p1.getIdUsuario() == null || p1.getIdUsuario().getIdUsuario() != p.getIdUsuario()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        p1.setDescripcionPost(p.getDescripcionPost());
        p1.setNombreVideojuego(p.getNombreVideojuego());
        p1.setCategorias(new ArrayList<>());
        for (Integer idCategoria : p.getCategorias()) {
            Categoria categoria = categoriaService.consultarUno(idCategoria);
            if (categoria != null) {
                p1.getCategorias().add(categoria);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


        if (p.getImgPost1() != null) {
            try {
                p1.setImgPost1(new URL(p.getImgPost1()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }



        p1 = postService.modificar(p1);
        return new ResponseEntity<>(new PostDTO().castPostADto(p1), HttpStatus.OK);
    }





    @GetMapping("/pornombrevideojuego")
    public ResponseEntity<List<PostDTO>> consultarPorNombreVideojuego(@RequestParam("nombreVideojuego") String nombreVideojuego) {
        List<Post> postBBDD = postService.findByNombreVideojuego(nombreVideojuego);
        List<PostDTO> postDTO = new ArrayList<>();
        for (Post post : postBBDD) {
            PostDTO postsDTO = new PostDTO();
            postDTO.add(postsDTO.castPostADto(post));
        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @GetMapping("/pornombrevideojuego/{idUsuario}")
    public ResponseEntity<List<PostDTO>> consultarPorNombreVideojuego(@RequestParam("nombreVideojuego") String nombreVideojuego, @PathVariable("idUsuario") int idUsuario){
        List<Post> postBBDD=postService.consultarTodos();
        List<PostDTO> postDTO= new ArrayList<>();
        for(Post post:postBBDD){
            PostDTO postsDTO=new PostDTO();
            postDTO.add(postsDTO.castPostADto(post));
        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @GetMapping("/porcategoria")
    public ResponseEntity<?> consultarPorNombreCategoria(@RequestParam("nombreCategoria") String nombreCategoria) {
        List<Post> postBBDD = postService.findByNombreCategoria(nombreCategoria); // Utiliza el servicio para buscar posts por nombre de categoría
        if (postBBDD.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada"); // Retorna un mensaje personalizado con el código de estado HTTP 404 si no se encuentra ningún post con la categoría especificada
        }

        List<PostDTO> postDTO = new ArrayList<>();
        for(Post post : postBBDD){
            PostDTO postsDTO = new PostDTO();
            postDTO.add(postsDTO.castPostADto(post));
        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }



}
