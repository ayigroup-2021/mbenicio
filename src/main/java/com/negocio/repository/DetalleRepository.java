package com.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.negocio.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer>{
}
