package com.integrador1.cafekamila.dto;

public class ProductoSimpleDTO {

    private Long idProducto;

    private String nombre;

    public ProductoSimpleDTO() {
    }

    public ProductoSimpleDTO(Long idProducto) {
        this.idProducto = idProducto;
    }

    public ProductoSimpleDTO(
            Long idProducto,
            String nombre
    ) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}