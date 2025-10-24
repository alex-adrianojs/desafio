package com.bootcamp.desafio.service.impl;

import com.bootcamp.desafio.model.Cliente;
import com.bootcamp.desafio.model.dto.ClienteDTO;
import com.bootcamp.desafio.repository.ClienteRepository;
import com.bootcamp.desafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente create(ClienteDTO cliente) {
        return null;
    }

    @Override
    public void findByNome(String nome) {

    }

    @Override
    public Cliente findById(Integer id) {
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public Cliente update(ClienteDTO cliente) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
