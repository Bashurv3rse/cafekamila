package com.integrador1.cafekamila.dto;

public class ProductoSimpleDTO {

    private Long idProducto;

    public ProductoSimpleDTO() {
    }

    public ProductoSimpleDTO(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
}