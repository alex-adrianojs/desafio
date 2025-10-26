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
    public void findByNome(String nome) {

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
    public void findByEmail(ClienteDTO user){
        Optional<Cliente> userEntity = repository.findByEmail(user.getEmail());
        if(userEntity.isPresent() && !userEntity.get().getId().equals(user.getId())){
            throw new DataIntegratyViolatedException("O e-mail informado já existe");
        }
    }

    @Override
    public Cliente update(ClienteDTO cliente) {
        findByEmail(cliente);
        return repository.save(mapper.map(cliente, Cliente.class));
    }

 }
