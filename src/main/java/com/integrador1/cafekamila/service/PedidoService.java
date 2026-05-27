package com.integrador1.cafekamila.service;

import com.integrador1.cafekamila.model.DetallePedido;
import com.integrador1.cafekamila.model.Pedido;
import com.integrador1.cafekamila.model.Producto;
import com.integrador1.cafekamila.repository.PedidoRepository;
import com.integrador1.cafekamila.repository.ProductoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Pedido guardarPedido(Pedido pedido) {

        // VALIDAR QUE EL PEDIDO TENGA DETALLES
        if (pedido.getDetalles() == null
                || pedido.getDetalles().isEmpty()) {

            throw new RuntimeException(
                    "El pedido debe tener al menos un producto"
            );
        }

        double total = 0;

        pedido.setFechaHora(LocalDateTime.now());
        pedido.setEstado("Pendiente");

        for (DetallePedido detalle : pedido.getDetalles()) {

            // VALIDAR CANTIDAD
            if (detalle.getCantidad() <= 0) {

                throw new RuntimeException(
                        "La cantidad debe ser mayor a 0"
                );
            }

            Producto productoReal = productoRepository
                    .findById(detalle.getProducto().getIdProducto())
                    .orElseThrow(() -> new RuntimeException(
                    "Producto no encontrado"
            ));

            // VALIDAR STOCK
            if (productoReal.getStock() < detalle.getCantidad()) {

                throw new RuntimeException(
                        "Stock insuficiente para el producto: "
                        + productoReal.getNombre()
                );
            }

            // DESCONTAR STOCK
            productoReal.setStock(
                    productoReal.getStock() - detalle.getCantidad()
            );

            productoRepository.save(productoReal);

            detalle.setProducto(productoReal);

            detalle.setPedido(pedido);

            double precio;

            // PRECIO MAYOR O MENOR
            if (pedido.getTipoPedido().equalsIgnoreCase("Mayor")) {

                precio = productoReal.getPrecioMayor();

            } else {

                precio = productoReal.getPrecioMenor();
            }

            double subtotal = detalle.getCantidad() * precio;

            detalle.setSubtotal(subtotal);

            total += subtotal;
        }

        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    // CAMBIAR ESTADO DEL PEDIDO
    public Pedido cambiarEstado(Integer idPedido, String nuevoEstado) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException(
                "Pedido no encontrado"
        ));

        // VALIDAR SI YA ESTA CANCELADO
        if (pedido.getEstado().equalsIgnoreCase("Cancelado")) {

            throw new RuntimeException(
                    "El pedido ya está cancelado"
            );
        }

        // SI EL NUEVO ESTADO ES CANCELADO
        // DEVOLVER STOCK
        if (nuevoEstado.equalsIgnoreCase("Cancelado")) {

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
}