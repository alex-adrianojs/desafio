package com.bootcamp.desafio.config;

import com.bootcamp.desafio.model.Cliente;
import com.bootcamp.desafio.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private ClienteRepository repository;

    @PostConstruct
    public void startDB(){
        Cliente cliente1 = new Cliente(null, "Alex Adriano", "alex.adriano@gmail.com");
        Cliente cliente2 = new Cliente(null, "Pedro de Lara", "pedro.delara@gmail.com");
        repository.saveAll(List.of(cliente1, cliente2));

    }
}
