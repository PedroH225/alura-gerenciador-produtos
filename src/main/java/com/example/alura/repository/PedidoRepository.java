package com.example.alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alura.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
