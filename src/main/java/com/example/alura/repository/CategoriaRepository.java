package com.example.alura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alura.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String>  {

	Optional<Categoria> findByNomeEqualsIgnoreCase(String nome);

}
