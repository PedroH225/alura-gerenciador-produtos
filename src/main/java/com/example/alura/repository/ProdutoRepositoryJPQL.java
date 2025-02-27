package com.example.alura.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.alura.model.Categoria;
import com.example.alura.model.Pedido;
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

	@Query("SELECT ped FROM Produto p JOIN p.pedidos ped WHERE ped.data BETWEEN :data AND :data2")
	List<Pedido> buscarPorPeriodo(LocalDate data, LocalDate data2);
	
	@Query("SELECT AVG(p.preco) FROM Produto p")
	Double mediaPrecoProdutos();
	
	@Query("SELECT MAX(p.preco) FROM Categoria c JOIN c.produtos p WHERE c.nome = :nome")
	Optional<Double> precoMaximoCategoria(String nome);
	
	@Query("SELECT COUNT(p) FROM Categoria c JOIN c.produtos p WHERE c.nome = :nome")
	Optional<Integer> countProdutos(String nome);
	
}




