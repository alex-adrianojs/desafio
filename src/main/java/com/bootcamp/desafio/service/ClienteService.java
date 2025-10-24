package com.bootcamp.desafio.service;

import com.bootcamp.desafio.model.Cliente;
import com.bootcamp.desafio.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    Cliente create(ClienteDTO cliente);

    void findByNome(String nome);

    Cliente findById(Integer id);

    List<Cliente> findAll();

    Cliente update(ClienteDTO cliente);

    void delete(Integer id);

}
