package com.bootcamp.desafio.controller;

import com.bootcamp.desafio.model.Cliente;
import com.bootcamp.desafio.model.dto.ClienteDTO;
import com.bootcamp.desafio.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value= "/cliente")
public class ClienteController {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(x -> mapper.map(x, ClienteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }


}
