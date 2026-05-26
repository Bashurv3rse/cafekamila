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

        double total = 0;

        pedido.setFechaHora(LocalDateTime.now());
        pedido.setEstado("Pendiente");

        for (DetallePedido detalle : pedido.getDetalles()) {

            Producto productoReal = productoRepository
                .findById(detalle.getProducto().getIdProducto())
                .orElseThrow();

            if (productoReal.getStock() < detalle.getCantidad()) {

                throw new RuntimeException(
                    "Stock insuficiente para el producto: "
                    + productoReal.getNombre()
                );
            }

            productoReal.setStock(
                productoReal.getStock() - detalle.getCantidad()
            );

            productoRepository.save(productoReal);

            detalle.setProducto(productoReal);

            detalle.setPedido(pedido);

            double precio;

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

    // NUEVO METODO
    public Pedido cambiarEstado(Integer idPedido, String nuevoEstado) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);

        return pedidoRepository.save(pedido);
    }
}