package com.example.demo.service;

import com.example.demo.entity.Departamento;
import com.example.demo.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public Departamento adicionar(Departamento departamento) {
        return repository.save(departamento);
    }

    public List<Departamento> listar() {
        return repository.findAll();
    }

    public Optional<Departamento> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarPorId(Long id){
        repository.deleteById(id);
    }

}
