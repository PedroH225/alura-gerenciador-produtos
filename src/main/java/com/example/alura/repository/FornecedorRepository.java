package com.example.alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alura.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {

}
