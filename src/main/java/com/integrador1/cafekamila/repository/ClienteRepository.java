package com.integrador1.cafekamila.repository;

import com.integrador1.cafekamila.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}