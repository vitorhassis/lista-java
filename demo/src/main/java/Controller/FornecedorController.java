package Controller;

import Entity.Fornecedor;
import Service.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Fornecedor> salvar(@RequestBody Fornecedor fornecedor){
        Fornecedor request =  service.adicionar(fornecedor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar(){
        List<Fornecedor> fornecedores = service.listar();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        Optional<Fornecedor> fornecedor =  service.buscarPorId(id);

        if(fornecedor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fornecedor.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable long id) {
        Optional<Fornecedor> fornecedor = service.buscarPorId(id);

        if(fornecedor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
