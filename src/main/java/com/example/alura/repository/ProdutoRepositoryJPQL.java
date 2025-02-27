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
	
	@Query("SELECT p FROM Produto p ORDER BY p.preco")
	List<Produto> ordenarProdutos();
	
	@Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
	List<Produto> ordenarProdutosDesc();
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE :letra%")
	List<Produto> buscarPorPrimeiraLetra(String letra);
}
