package com.example.alura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.alura.model.Produto;

@Repository
public interface ProdutoRepositoryJPQL extends JpaRepository<Produto, String> {

	@Query("SELECT p FROM Produto p WHERE p.preco >= :valor")
	List<Produto> buscarPorValor(Double valor);
}
