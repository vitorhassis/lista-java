package com.example.demo.controller;

import com.example.demo.entity.Livro;
import com.example.demo.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Livro> salvarLivro(@RequestBody Livro livro){
        Livro request =  service.adicionar(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros(){
        List<Livro> livros = service.listar();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarLivroPorId(@PathVariable Long id){
        Optional<Livro> livro =  service.buscarPorId(id);

        if(livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livro.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable long id) {
        Optional<Livro> livro = service.buscarPorId(id);

        if(livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
