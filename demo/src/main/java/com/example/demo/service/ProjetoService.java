package com.example.demo.service;

import com.example.demo.entity.Projeto;
import com.example.demo.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    private final ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public Projeto adicionar(Projeto projeto) {
        return repository.save(projeto);
    }

    public List<Projeto> listar() {
        return repository.findAll();
    }

    public Optional<Projeto> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
