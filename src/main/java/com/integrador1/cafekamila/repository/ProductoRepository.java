package com.integrador1.cafekamila.repository;

import com.integrador1.cafekamila.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}