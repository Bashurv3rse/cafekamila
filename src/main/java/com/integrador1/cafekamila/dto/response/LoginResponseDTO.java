package com.integrador1.cafekamila.dto.response;

public class LoginResponseDTO {

    private String mensaje;
    private String username;
    private String rol;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(
            String mensaje,
            String username,
            String rol
    ) {
        this.mensaje = mensaje;
        this.username = username;
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}