package com.integrador1.cafekamila.controller;

import com.integrador1.cafekamila.dto.request.LoginRequestDTO;
import com.integrador1.cafekamila.dto.response.LoginResponseDTO;
import com.integrador1.cafekamila.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO dto
    ) {

        return usuarioService.login(dto);
    }
}