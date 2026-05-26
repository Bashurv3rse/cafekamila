package com.integrador1.cafekamila.dto;

public class PedidoSimpleDTO {

    private Long idPedido;

    public PedidoSimpleDTO() {
    }

    public PedidoSimpleDTO(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
}