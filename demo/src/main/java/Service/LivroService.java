package Service;

import Entity.Livro;
import Repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository livroRepository){
        repository = livroRepository;
    }

    public Livro adicionar(Livro livro) {
        return repository.save(livro);
    }

    public List<Livro> listar () {
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
