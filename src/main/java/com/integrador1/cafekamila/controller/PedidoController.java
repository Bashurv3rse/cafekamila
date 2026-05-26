package com.integrador1.cafekamila.controller;

import com.integrador1.cafekamila.model.Pedido;
import com.integrador1.cafekamila.repository.PedidoRepository;
import com.integrador1.cafekamila.service.PedidoService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarPedidos() {

        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {

        return pedidoService.guardarPedido(pedido);
    }

    // NUEVO ENDPOINT
    @PutMapping("/{id}/estado")
    public Pedido cambiarEstado(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body
    ) {

        return pedidoService.cambiarEstado(
                id,
                body.get("estado")
        );
    }
}