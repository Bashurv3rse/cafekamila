package com.integrador1.cafekamila.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String mensaje;
    private int status;
    private LocalDateTime fecha;

    public ErrorResponse(String mensaje, int status) {

        this.mensaje = mensaje;
        this.status = status;
        this.fecha = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}