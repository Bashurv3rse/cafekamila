package com.integrador1.cafekamila.repository;

import com.integrador1.cafekamila.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import com.integrador1.cafekamila.model.Pedido.EstadoPedido;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
    List<Pedido> findByEstadoIn(List<Pedido.EstadoPedido> estados);
}