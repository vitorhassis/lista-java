package com.example.demo.controller;

import com.example.demo.entity.Projeto;
import com.example.demo.service.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/projetos")
public class ProjetoController {

    private final ProjetoService service;

    public  ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Projeto> salvar(@RequestBody Projeto projeto){
        Projeto request =  service.adicionar(projeto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listar(){
        List<Projeto> projetos = service.listar();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        Optional<Projeto> projeto =  service.buscarPorId(id);

        if(projeto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projeto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable long id) {
        Optional<Projeto> projeto = service.buscarPorId(id);

        if(projeto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }

}
