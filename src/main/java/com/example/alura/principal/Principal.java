package com.example.alura.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
	    var categoria1 = new Categoria(null, "Móvel");
	    var categoria2 = new Categoria(null, "Eletrônicos");
	    var categoria3 = new Categoria(null, "Eletrodomésticos");
	    var categoria4 = new Categoria(null, "Ferramentas");

	    var fornecedor1 = new Fornecedor(null, "Móveis & CIA");
	    var fornecedor2 = new Fornecedor(null, "Eletrônicos & CIA");
	    var fornecedor3 = new Fornecedor(null, "Casa & Eletro");
	    var fornecedor4 = new Fornecedor(null, "Ferramentas Plus");

	    var produto1 = new Produto(null, "Monitor", 1000.00, categoria2, fornecedor2);
	    var produto2 = new Produto(null, "Computador", 2000.00, categoria2, fornecedor2);
	    var produto3 = new Produto(null, "Cadeira", 200.00, categoria1, fornecedor1);
	    var produto4 = new Produto(null, "Sofá", 1500.00, categoria1, fornecedor1);
	    var produto5 = new Produto(null, "Geladeira", 3000.00, categoria3, fornecedor3);
	    var produto6 = new Produto(null, "Micro-ondas", 800.00, categoria3, fornecedor3);
	    var produto7 = new Produto(null, "Furadeira", 500.00, categoria4, fornecedor4);
	    var produto8 = new Produto(null, "Serra Elétrica", 1200.00, categoria4, fornecedor4);
	    var produto9 = new Produto(null, "Mouse", 150.00, categoria2, fornecedor2);
	    var produto10 = new Produto(null, "Teclado", 250.00, categoria2, fornecedor2);
	    
	    categoria1.setProdutos(Arrays.asList(produto3, produto4));
	    categoria2.setProdutos(Arrays.asList(produto1, produto2, produto9, produto10));
	    categoria3.setProdutos(Arrays.asList(produto5, produto6));
	    categoria4.setProdutos(Arrays.asList(produto7, produto8));

	    var categoriadb1 = categoriaRepository.save(categoria1);
	    var categoriadb2 = categoriaRepository.save(categoria2);
	    var categoriadb3 = categoriaRepository.save(categoria3);
	    var categoriadb4 = categoriaRepository.save(categoria4);

	    var pedido1 = new Pedido(null, LocalDate.now(), categoriadb2.getProdutos());
	    var pedido2 = new Pedido(null, null, Arrays.asList(categoriadb2.getProdutos().get(1)));
	    var pedido3 = new Pedido(null, LocalDate.now(), categoriadb1.getProdutos());
	    var pedido4 = new Pedido(null, LocalDate.of(2023, 12, 15), Arrays.asList(categoriadb3.getProdutos().get(0)));
	    var pedido5 = new Pedido(null, null, Arrays.asList(categoriadb4.getProdutos().get(1)));
	    var pedido6 = new Pedido(null, LocalDate.of(2024, 1, 20), Arrays.asList(produto6, produto7));
	    var pedido7 = new Pedido(null, LocalDate.now(), Arrays.asList(produto5, produto8));

	    pedidoRepository.save(pedido1);
	    pedidoRepository.save(pedido2);
	    pedidoRepository.save(pedido3);
	    pedidoRepository.save(pedido4);
	    pedidoRepository.save(pedido5);
	    pedidoRepository.save(pedido6);
	    pedidoRepository.save(pedido7);

	    var pedidos = pedidoRepository.findAll();
	    
	    pedidos.forEach(System.out::println);
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

	public void countProdutoCategoria() {
		System.out.println("Digite a categoria do produto:");
		String categoria = leitura.nextLine();

		Integer countCategoria = produtoRepository.countByCategoriaNome(categoria);

		if (countCategoria != null) {
			System.out.println("A categoria " + categoria + " possúi " 
		+ countCategoria + " produtos registrados.");

		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void countProdutoValor() {
		System.out.println("Digite o valor do produto:");
		Double valor = leitura.nextDouble();

		Integer countProduto = produtoRepository.countByPrecoGreaterThanEqual(valor);

		if (countProduto != null) {
			System.out.println("Há " + countProduto + " produtos registrados com valor maior ou igual " + valor);

		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorValorOuNome() {
		System.out.println("Digite o nome do produto:");
		String nome = leitura.nextLine();
		
		System.out.println("Digite a valor máximo do produto:");
		Double valor = leitura.nextDouble();

		List<Produto> buscarProduto = produtoRepository.findAllByPrecoLessThanEqualOrNomeContainsIgnoreCase
				(valor, nome);

		if (!buscarProduto.isEmpty()) {
			System.out.println("Produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado:");
			buscarProduto.forEach(System.out::println);
		} else {
			System.out.println("Produto não encontrado.");
		}
	}
	
	public void buscarPorData() {
		System.out.println("Digite uma data (dd/MM/yyyy):");
		String dataString = leitura.nextLine();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			LocalDate data = LocalDate.parse(dataString, dtf);
			
			List<Pedido> buscarPedidos = pedidoRepository.findAllByDataAfter(data);
			
			if (!buscarPedidos.isEmpty()) {
				buscarPedidos.forEach(System.out::println);
			} else {
				System.out.println("Nenhum produto encontrado.");
			}
			
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida: " + dataString);
		}
	}

	public void buscarPorDataAntes() {
		System.out.println("Digite uma data (dd/MM/yyyy):");
		String dataString = leitura.nextLine();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			LocalDate data = LocalDate.parse(dataString, dtf);
			
			List<Pedido> buscarPedidos = pedidoRepository.findAllByDataBefore(data);
			
			if (!buscarPedidos.isEmpty()) {
				System.out.println("Pedidos com data de entrega antes de " + dataString + ":");
				buscarPedidos.forEach(System.out::println);
			} else {
				System.out.println("Nenhum produto encontrado.");
			}
			
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida: " + dataString);
		}
	}
	
	public void buscarPorDataEntre() {
		System.out.println("Digite uma data (dd/MM/yyyy):");
		String dataString = leitura.nextLine();
		
		System.out.println("Digite outra data (dd/MM/yyyy):");
		String dataString2 = leitura.nextLine();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			LocalDate data = LocalDate.parse(dataString, dtf);
			LocalDate data2 = LocalDate.parse(dataString2, dtf);

			
			List<Pedido> buscarPedidos = pedidoRepository.findAllByDataBetween(data, data2);
			
			if (!buscarPedidos.isEmpty()) {
				System.out.println("Pedidos com data de entrega entre " 
			+ dataString  + " e " + dataString2 + ":");

				buscarPedidos.forEach(System.out::println);
			} else {
				System.out.println("Nenhum produto encontrado.");
			}
			
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida: " + dataString);
		}
	}
	
	public void top3MaisCaros() {
		System.out.println("Top 3 produtos mais caros:");
		var buscarProdutos = produtoRepository.findTop3ByOrderByPrecoDesc();
		
		if (!buscarProdutos.isEmpty()) {
			buscarProdutos.forEach(System.out::println);
		} else {
			System.out.println("Nenhum produto encontrado!");
		}
	}
	
	public void top5MaisBaratos() {
		System.out.println("Top 5 produtos mais baratos:");
		var buscarProdutos = produtoRepository.findTop5ByOrderByPreco();
		
		if (!buscarProdutos.isEmpty()) {
			buscarProdutos.forEach(System.out::println);
		} else {
			System.out.println("Nenhum produto encontrado!");
		}
	}
}




