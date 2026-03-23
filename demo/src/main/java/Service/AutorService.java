package Service;

import Entity.Autor;
import Repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor adicionar(Autor autor) {
        return repository.save(autor);
    }

    public List<Autor> listar() {
        return repository.findAll();
    }

    public Optional<Autor> buscarPorId(long id) {
        return repository.findById(id);
    }

    public void deletarPorId(long id) {
        repository.deleteById(id);
    }
}
