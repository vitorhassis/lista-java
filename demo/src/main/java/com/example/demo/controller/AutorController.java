package com.example.demo.controller;

import com.example.demo.entity.Autor;
import com.example.demo.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/autores")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor request =  service.adicionar(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable long id) {
        Optional<Autor> autor = service.buscarPorId(id);

        if(autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutorPorId(@PathVariable long id) {
        Optional<Autor> autor = service.buscarPorId(id);

        if(autor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
