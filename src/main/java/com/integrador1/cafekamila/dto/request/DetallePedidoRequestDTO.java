package com.integrador1.cafekamila.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetallePedidoRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long idProducto;

    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}