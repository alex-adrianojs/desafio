package com.bootcamp.desafio.service.impl;

import com.bootcamp.desafio.model.Cliente;
import com.bootcamp.desafio.model.dto.ClienteDTO;
import com.bootcamp.desafio.repository.ClienteRepository;
import com.bootcamp.desafio.service.ClienteService;
import com.bootcamp.desafio.service.exceptions.DataIntegratyViolatedException;
import com.bootcamp.desafio.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Cliente create(ClienteDTO cliente) {
        findByEmail(cliente);
        return repository.save(mapper.map(cliente, Cliente.class));
    }

    @Override
    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public void findByEmail(ClienteDTO cliente){
        Optional<Cliente> clienteEntity = repository.findByEmail(cliente.getEmail());
        if(clienteEntity.isPresent() && !clienteEntity.get().getId().equals(cliente.getId())){
            throw new DataIntegratyViolatedException("O e-mail informado já existe");
        }
    }

    @Override
    public List<Cliente>  findByNome(String nome) {
        List<Cliente> clientes = repository.findByNameContainingIgnoreCase(nome);
        if (clientes.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum cliente encontrado com o nome: " + nome);
        }
        return clientes;
    }

    @Override
    public Cliente update(ClienteDTO cliente) {
        findByEmail(cliente);
        return repository.save(mapper.map(cliente, Cliente.class));
    }

    @Override
    public long countClientes() {
        return repository.count();
    }

 }
