package com.example.alura.principal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import com.example.alura.model.Categoria;
import com.example.alura.model.Fornecedor;
import com.example.alura.model.Pedido;
import com.example.alura.model.Produto;
import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.FornecedorRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepository;

public class Principal {

	private CategoriaRepository categoriaRepository;

	private ProdutoRepository produtoRepository;

	private PedidoRepository pedidoRepository;
	
	private FornecedorRepository fornecedorRepository;
	
	private Scanner leitura = new Scanner(System.in);


	public Principal(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {

		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.fornecedorRepository = fornecedorRepository;
	}

	public void registrar() {
		var categoria = new Categoria(null, "Móvel");
		var fornecedor = new Fornecedor(null, "Móveis & CIA");
		
		var produto = new Produto(null, "Cadeira", 200.00, categoria, fornecedor);
		categoria.setProdutos(Arrays.asList(produto));
		
		var categoriadb = categoriaRepository.save(categoria);
		
		var pedido = new Pedido(null, LocalDate.now(), categoriadb.getProdutos());

		pedidoRepository.save(pedido);
		
		System.out.println(pedidoRepository.findAll());
	}
	
	public void buscarPorNome() {
		System.out.println("Digite o nome do produto:");
		String nome = leitura.nextLine();
		
		Optional<Produto> buscarProduto = produtoRepository.findByNomeEqualsIgnoreCase(nome);
		
		if (buscarProduto.isPresent()) {
			System.out.println(buscarProduto.get());
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
}



