package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(Produto produto){
        Produto request = service.adicionar(produto);
        URI uri;
        uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> produtos = service.listar();
        return  ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable long id){
        var produto = service.buscarPorId(id);

        if(produto.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable long id) {
        Optional<Produto> produto = service.buscarPorId(id);

        if(produto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
