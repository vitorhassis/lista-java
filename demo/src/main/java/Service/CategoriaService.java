package Service;

import Entity.Categoria;
import Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        repository = categoriaRepository;
    }

    public Categoria adicionar(Categoria categoria) {
        return repository.save(categoria);
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
