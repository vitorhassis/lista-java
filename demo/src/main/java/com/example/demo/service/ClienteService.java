package com.example.demo.service;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public Cliente adicionar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
