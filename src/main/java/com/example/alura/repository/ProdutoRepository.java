package com.example.alura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alura.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>{

	Optional<Produto> findByNomeEqualsIgnoreCase(String nome);
	
	List<Produto> findAllByPrecoGreaterThanEqual(Double valor);
	
}
