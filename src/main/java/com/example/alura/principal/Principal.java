package com.example.alura.principal;

import java.time.LocalDate;
import java.util.Arrays;

import com.example.alura.model.Categoria;
import com.example.alura.model.Pedido;
import com.example.alura.model.Produto;
import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepository;

public class Principal {

	private CategoriaRepository categoriaRepository;

	private ProdutoRepository produtoRepository;

	private PedidoRepository pedidoRepository;

	public Principal(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			PedidoRepository pedidoRepository) {

		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}

	public void executar() {
		var categoria = new Categoria(null, "Móvel");
		var produto = new Produto(null, "Cadeira", 200.00, categoria);
		categoria.setProdutos(Arrays.asList(produto));
		
		var categoriadb = categoriaRepository.save(categoria);
		
		var pedido = new Pedido(null, LocalDate.now(), categoriadb.getProdutos());

		pedidoRepository.save(pedido);
		
		System.out.println(pedidoRepository.findAll());
	}
}
