package com.bootcamp.desafio.controller;

import com.bootcamp.desafio.model.Cliente;
import com.bootcamp.desafio.model.dto.ClienteDTO;
import com.bootcamp.desafio.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public  ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO cliente){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(service.create(cliente)
                        .getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), ClienteDTO.class));

    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(x -> mapper.map(x, ClienteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO cliente){
        cliente.setId(id);
        return  ResponseEntity.ok().body(mapper.map(service.update(cliente), ClienteDTO.class));
    }

    @GetMapping(value = "nome/{nome}")
    public ResponseEntity<List<ClienteDTO>> findByNome(@PathVariable String nome){
        List<Cliente> clientes = service.findByNome(nome);
        List<ClienteDTO> clientesDTO = clientes.stream()
                .map(c -> mapper.map(c, ClienteDTO.class))
                .toList();
        return ResponseEntity.ok(clientesDTO);

    }

    @GetMapping("/contar")
    public ResponseEntity<Long> countClientes() {
        long total = service.countClientes();
        return ResponseEntity.ok(total);
    }


}
