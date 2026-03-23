package com.example.demo.controller;

import com.example.demo.entity.Departamento;
import com.example.demo.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Departamento> salvarDepartamento(@RequestBody Departamento departamento){
        Departamento request =  service.adicionar(departamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listarDepartamentos(){
        List<Departamento> departamentos = service.listar();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarDepartamentoPorId(@PathVariable Long id){
        Optional<Departamento> departamento =  service.buscarPorId(id);

        if(departamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departamento.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDepartamentoPorId(@PathVariable long id) {
        Optional<Departamento> departamento = service.buscarPorId(id);

        if(departamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
