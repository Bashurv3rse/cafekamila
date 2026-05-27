package com.integrador1.cafekamila.service;

import com.integrador1.cafekamila.dto.PedidoSimpleDTO;
import com.integrador1.cafekamila.dto.ProductoSimpleDTO;
import com.integrador1.cafekamila.dto.request.DetallePedidoRequestDTO;
import com.integrador1.cafekamila.dto.request.PedidoRequestDTO;
import com.integrador1.cafekamila.dto.response.DetallePedidoResponseDTO;
import com.integrador1.cafekamila.dto.response.PedidoResponseDTO;
import com.integrador1.cafekamila.model.DetallePedido;
import com.integrador1.cafekamila.model.Pedido;
import com.integrador1.cafekamila.model.Producto;
import com.integrador1.cafekamila.repository.PedidoRepository;
import com.integrador1.cafekamila.repository.ProductoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // GUARDAR PEDIDO
    public PedidoResponseDTO guardarPedido(PedidoRequestDTO pedidoDTO) {

        Pedido pedido = new Pedido();

        pedido.setNombreCliente(pedidoDTO.getNombreCliente());
        pedido.setTipoPedido(pedidoDTO.getTipoPedido());
        pedido.setFechaHora(LocalDateTime.now());

        // ESTADO INICIAL
        pedido.setEstado(Pedido.EstadoPedido.PENDIENTE);

        List<DetallePedido> detalles = new ArrayList<>();

        double total = 0;

        for (DetallePedidoRequestDTO detalleDTO : pedidoDTO.getDetalles()) {

            Producto productoReal = productoRepository
                    .findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException(
                            "Producto no encontrado"
                    ));

            // VALIDAR STOCK
            if (productoReal.getStock() < detalleDTO.getCantidad()) {

                throw new RuntimeException(
                        "Stock insuficiente para el producto: "
                        + productoReal.getNombre()
                );
            }

            // DESCONTAR STOCK
            productoReal.setStock(
                    productoReal.getStock() - detalleDTO.getCantidad()
            );

            productoRepository.save(productoReal);

            double precio;

            // PRECIO MAYOR O MENOR
            if (pedidoDTO.getTipoPedido().equalsIgnoreCase("Mayor")) {

                precio = productoReal.getPrecioMayor();

            } else {

                precio = productoReal.getPrecioMenor();
            }

            double subtotal = detalleDTO.getCantidad() * precio;

            // CREAR DETALLE
            DetallePedido detalle = new DetallePedido();

            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setSubtotal(subtotal);

            detalle.setProducto(productoReal);
            detalle.setPedido(pedido);

            detalles.add(detalle);

            total += subtotal;
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return convertirAPedidoResponseDTO(pedidoGuardado);
    }

    // CAMBIAR ESTADO DEL PEDIDO
    public Pedido cambiarEstado(Long idPedido,Pedido.EstadoPedido nuevoEstado){

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException(
                        "Pedido no encontrado"
                ));

        // VALIDAR SI YA ESTA FINALIZADO
        if (pedido.getEstado() == Pedido.EstadoPedido.ENTREGADO
                || pedido.getEstado() == Pedido.EstadoPedido.CANCELADO) {

            throw new RuntimeException(
                    "El pedido ya está finalizado"
            );
        }

        // DEVOLVER STOCK SI SE CANCELA
        if (nuevoEstado == Pedido.EstadoPedido.CANCELADO) {

            for (DetallePedido detalle : pedido.getDetalles()) {

                Producto producto = detalle.getProducto();

                producto.setStock(
                        producto.getStock() + detalle.getCantidad()
                );

                productoRepository.save(producto);
            }
        }

        pedido.setEstado(nuevoEstado);

        return pedidoRepository.save(pedido);
    }

    // CONVERTIR ENTITY A RESPONSE DTO
    private PedidoResponseDTO convertirAPedidoResponseDTO(Pedido pedido) {

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setIdPedido(pedido.getIdPedido());
        dto.setNombreCliente(pedido.getNombreCliente());
        dto.setTipoPedido(pedido.getTipoPedido());

        // ESTADO ENUM
        dto.setEstado(pedido.getEstado());

        dto.setFechaHora(pedido.getFechaHora());
        dto.setTotal(pedido.getTotal());

        List<DetallePedidoResponseDTO> detallesDTO = new ArrayList<>();

        for (DetallePedido detalle : pedido.getDetalles()) {

            DetallePedidoResponseDTO detalleDTO
                    = new DetallePedidoResponseDTO();

            detalleDTO.setIdDetalle(detalle.getIdDetalle());
            detalleDTO.setCantidad(detalle.getCantidad());
            detalleDTO.setSubtotal(detalle.getSubtotal());

            detalleDTO.setPedido(
                    new PedidoSimpleDTO(
                            pedido.getIdPedido()
                    )
            );

            detalleDTO.setProducto(
                    new ProductoSimpleDTO(
                            detalle.getProducto().getIdProducto()
                    )
            );

            detallesDTO.add(detalleDTO);
        }

        dto.setDetalles(detallesDTO);

        return dto;
    }
    public List<PedidoResponseDTO> obtenerPedidosPorEstado(
        Pedido.EstadoPedido estado) {

    List<Pedido> pedidos = pedidoRepository.findByEstado(estado);

    return pedidos.stream()
            .map(this::convertirAPedidoResponseDTO)
            .toList();
    }
    public List<PedidoResponseDTO> obtenerHistorial() {

    List<Pedido> pedidos =
            pedidoRepository.findByEstadoIn(
                    List.of(
                            Pedido.EstadoPedido.ENTREGADO,
                            Pedido.EstadoPedido.CANCELADO
                    )
            );

    return pedidos.stream()
            .map(this::convertirAPedidoResponseDTO)
            .toList();
    }
}