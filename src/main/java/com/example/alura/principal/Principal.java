package com.example.alura.principal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
		var categoria2 = new Categoria(null, "Eletrônicos");
		
		var fornecedor = new Fornecedor(null, "Móveis & CIA");
		var fornecedor2 = new Fornecedor(null, "Eletrônicos & CIA");
		
		var produto = new Produto(null, "Monitor", 200.00, categoria2, fornecedor);
		var produto2 = new Produto(null, "Computador", 2000.00, categoria2, fornecedor2);

//		categoria.setProdutos(Arrays.asList(produto));
		categoria2.setProdutos(Arrays.asList(produto2, produto));
		
		var categoriadb = categoriaRepository.save(categoria);
		var categoriadb2 = categoriaRepository.save(categoria2);

		
//		var pedido = new Pedido(null, LocalDate.now(), categoriadb.getProdutos());
		var pedido = new Pedido(null, null, categoriadb.getProdutos());
		var pedido2 = new Pedido(null, null, categoriadb2.getProdutos());



		pedidoRepository.save(pedido);
		pedidoRepository.save(pedido2);

		
//		System.out.println(pedidoRepository.findAll());
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
	
	public void buscarPorTrecho() {
		System.out.println("Digite um trecho do nome do produto:");
		String nome = leitura.nextLine();
		
		Optional<Produto> buscarProduto = produtoRepository.findByNomeContainsIgnoreCase(nome);
		
		if (buscarProduto.isPresent()) {
			System.out.println(buscarProduto.get());
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorCategoria() {
		System.out.println("Digite a categoria do produto:");
		String categoria = leitura.nextLine();
		
		Optional<Categoria> buscarProduto = categoriaRepository.findByNomeEqualsIgnoreCase(categoria);
		
		if (buscarProduto.isPresent()) {
			System.out.println(buscarProduto.get().getProdutos());
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorValor() {
		System.out.println("Digite a valor mínimo do produto:");
		Double valor = leitura.nextDouble();
		
		List<Produto> buscarProduto = produtoRepository.findAllByPrecoGreaterThanEqual(valor);
		
		if (!buscarProduto.isEmpty()) {
			System.out.println(buscarProduto);
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorValorMaximo() {
		System.out.println("Digite a valor máximo do produto:");
		Double valor = leitura.nextDouble();
		
		List<Produto> buscarProduto = produtoRepository.findAllByPrecoLessThanEqual(valor);
		
		if (!buscarProduto.isEmpty()) {
			System.out.println(buscarProduto);
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarEntregaNula() {
		System.out.println("Produtos sem data de entrega:");
		List<Pedido> buscarPedidos = pedidoRepository.findAllByDataIsNull();
		
		if (!buscarPedidos.isEmpty()) {
			buscarPedidos.forEach(System.out::println);
		} else {
			System.out.println("Produto não encontrado.");
		}
	}

	public void buscarEntregaNotNull() {
		System.out.println("Produtos com data de entrega:");
		List<Pedido> buscarPedidos = pedidoRepository.findAllByDataNotNull();
		
		if (!buscarPedidos.isEmpty()) {
			buscarPedidos.forEach(System.out::println);
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorCategoriaValor() {
		System.out.println("Digite a categoria do produto:");
		String categoria = leitura.nextLine();
		
		List<Produto> buscarProduto = produtoRepository.findAllByCategoriaNomeOrderByPreco(categoria);
		
		if (!buscarProduto.isEmpty()) {
			buscarProduto.forEach(System.out::println);
			
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorCategoriaValorDesc() {
		System.out.println("Digite a categoria do produto:");
		String categoria = leitura.nextLine();
		
		List<Produto> buscarProduto = produtoRepository.findAllByCategoriaNomeOrderByPrecoDesc(categoria);
		
		if (!buscarProduto.isEmpty()) {
			buscarProduto.forEach(System.out::println);
			
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
}



