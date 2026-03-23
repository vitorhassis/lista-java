package Controller;

import Entity.Categoria;
import Entity.Livro;
import Service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        Categoria request =  service.adicionar(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = service.buscarPorId(id);

        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(categoria.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = service.buscarPorId(id);

        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
