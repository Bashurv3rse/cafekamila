package com.integrador1.cafekamila.controller;

import com.integrador1.cafekamila.dto.request.PedidoRequestDTO;
import com.integrador1.cafekamila.dto.response.PedidoResponseDTO;
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

    // LISTAR TODOS
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // HISTORIAL (ENTREGADOS Y CANCELADOS)
    @GetMapping("/historial")
    public List<PedidoResponseDTO> obtenerHistorial() {

        return pedidoService.obtenerHistorial();
    }

    // OBTENER POR ESTADO
    @GetMapping("/estado/{estado}")
    public List<PedidoResponseDTO> obtenerPorEstado(
            @PathVariable String estado
    ) {

        Pedido.EstadoPedido estadoPedido =
                Pedido.EstadoPedido.valueOf(
                        estado.toUpperCase()
                );

        return pedidoService.obtenerPedidosPorEstado(
                estadoPedido
        );
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {

        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Pedido no encontrado"
                ));
    }

    // CREAR PEDIDO
    @PostMapping
    public PedidoResponseDTO crearPedido(
            @RequestBody PedidoRequestDTO pedidoDTO
    ) {
        return pedidoService.guardarPedido(pedidoDTO);
    }

    // CAMBIAR ESTADO
    @PutMapping("/{id}/estado")
    public Pedido cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {

        String estadoTexto = body.get("estado");

        Pedido.EstadoPedido nuevoEstado =
                Pedido.EstadoPedido.valueOf(
                        estadoTexto.toUpperCase()
                );

        return pedidoService.cambiarEstado(
                id,
                nuevoEstado
        );
    }
}