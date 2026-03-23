package com.example.demo.controller;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {
    private final PedidoService service;
    public PedidoController(PedidoService pedidoService) {
        service = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido request = service.adicionar(pedido);
        URI uri;
        uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = service.listar();
        return ResponseEntity.ok().body(pedidos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = service.buscarPorId(id);

        if(pedido.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido.get());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPedidoPorId(@PathVariable long id) {
        Optional<Pedido> pedido = service.buscarPorId(id);

        if(pedido.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
