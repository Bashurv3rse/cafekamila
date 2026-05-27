package com.integrador1.cafekamila.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import com.integrador1.cafekamila.model.EstadoPedido;
import com.integrador1.cafekamila.model.Pedido;

public class PedidoResponseDTO {

    private Long idPedido;

    private String nombreCliente;

    private String tipoPedido;

    private Pedido.EstadoPedido estado;;

    private LocalDateTime fechaHora;

    private Double total;

    private List<DetallePedidoResponseDTO> detalles;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Pedido.EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(Pedido.EstadoPedido estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetallePedidoResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoResponseDTO> detalles) {
        this.detalles = detalles;
    }
}