package com.integrador1.cafekamila.dto;

public class DetallePedidoResponseDTO {

    private Long idDetalle;

    private Integer cantidad;

    private Double subtotal;

    private PedidoSimpleDTO pedido;

    private ProductoSimpleDTO producto;

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public PedidoSimpleDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoSimpleDTO pedido) {
        this.pedido = pedido;
    }

    public ProductoSimpleDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoSimpleDTO producto) {
        this.producto = producto;
    }
}