package com.integrador1.cafekamila.service;

import com.integrador1.cafekamila.dto.request.LoginRequestDTO;
import com.integrador1.cafekamila.dto.response.LoginResponseDTO;
import com.integrador1.cafekamila.model.Usuario;
import com.integrador1.cafekamila.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    private static final Logger logger =
        LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        if (!usuario.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        
        logger.info(
            "Login exitoso del usuario {}",
            usuario.getUsername()
        );
        
        return new LoginResponseDTO(
                "Login exitoso",
                usuario.getUsername(),
                usuario.getRol().name()
        );
    }
}