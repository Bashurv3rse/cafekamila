package com.integrador1.cafekamila.controller;

import com.integrador1.cafekamila.model.Cliente;
import com.integrador1.cafekamila.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {

        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente agregarCliente(@RequestBody Cliente cliente) {

        return clienteRepository.save(cliente);
    }
}