package Service;

import Entity.Categoria;
import Entity.Departamento;
import Entity.Tarefa;
import Repository.CategoriaRepository;
import Repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa adicionar(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public List<Tarefa> listar() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
