package com.example.demo.controller;
import com.example.demo.entity.Tarefa;
import com.example.demo.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tarefas")
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tarefa> salvarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa request =  service.adicionar(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable long id) {
        Optional<Tarefa> tarefa = service.buscarPorId(id);

        if(tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefaPorId(@PathVariable long id) {
        Optional<Tarefa> tarefa = service.buscarPorId(id);

        if(tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
