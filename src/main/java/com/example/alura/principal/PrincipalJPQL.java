package com.example.alura.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.example.alura.model.Categoria;
import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.FornecedorRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepositoryJPQL;

public class PrincipalJPQL {
	private CategoriaRepository categoriaRepository;

	private ProdutoRepositoryJPQL produtoRepository;

	private PedidoRepository pedidoRepository;

	private FornecedorRepository fornecedorRepository;

	private Scanner leitura = new Scanner(System.in);

	public PrincipalJPQL(CategoriaRepository categoriaRepository, ProdutoRepositoryJPQL produtoRepository,
			PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {

		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.fornecedorRepository = fornecedorRepository;
	}

	public void buscarPorValor() {
		System.out.println("Digite o valor mínimo do produto:");
		Double valor = leitura.nextDouble();
		var buscarProdutos = produtoRepository.buscarPorValor(valor);
		
		if (!buscarProdutos.isEmpty()) {
			buscarProdutos.forEach(System.out::println);
		} else {
			System.out.println("Nenhum produto encontrado.");
		}
	}
	
	public void ordenarProdutos() {
		var produtos = produtoRepository.ordenarProdutos();
		
		if (!produtos.isEmpty()) {
			System.out.println("Produtos ordenados de forma crescente:");
			produtos.forEach(System.out::println);
		} else {
			System.out.println("Nenhum produto encontrado!");
		}
		
	}

	public void ordenarProdutosDesc() {
		var produtos = produtoRepository.ordenarProdutosDesc();
		
		if (!produtos.isEmpty()) {
			System.out.println("Produtos ordenados de forma decrescente:");
			produtos.forEach(System.out::println);
		} else {
			System.out.println("Nenhum produto encontrado!");
		}
		
	}
	
	public void buscarPorPrimeiraLetra() {
		System.out.println("Digite a primeira letra do produto desejado:");
		String letra = leitura.nextLine();
		var produtos = produtoRepository.buscarPorPrimeiraLetra(letra);
		
		if (!produtos.isEmpty()) {
			
			produtos.forEach(System.out::println);
		} else {
			System.out.println("Nenhum produto encontrado!");
		}
		
	}
	
	public void buscarPorPeriodo() {
		System.out.println("Digite uma data (dd/MM/yyyy):");
		String dataString = leitura.nextLine();

		System.out.println("Digite outra data (dd/MM/yyyy):");
		String dataString2 = leitura.nextLine();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			LocalDate data = LocalDate.parse(dataString, dtf);
			LocalDate data2 = LocalDate.parse(dataString2, dtf);
			
			var buscarProdutos = produtoRepository.buscarPorPeriodo(data, data2);
				if (!buscarProdutos.isEmpty()) {
					System.out.println(
							"Pedidos com data de entrega entre " + dataString + " e " + dataString2 + ":");

					buscarProdutos.forEach(System.out::println);
				} else {
					System.out.println("Nenhum pedido encontrado.");
				}
			
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida: " + dataString);
		}
	}

	public void mediaPrecoProdutos() {
		Double media = produtoRepository.mediaPrecoProdutos();
		System.out.println("Média de preço dos produtos: " + media);
	}
	
	public void maximoPrecoProduto() {
		System.out.println("Digite uma categoria:");
		String nome = leitura.nextLine();
		Optional<Double> maximo = produtoRepository.precoMaximoCategoria(nome);
		
		if (maximo.isPresent()) {
			System.out.println("Preço máximo de um produto da categoria " + nome + ": " + maximo.get());

		} else {
			System.out.println("Categoria não encontrada ou sem produtos.");
		}
		
	}

	public void countProdutoCategoria() {
		System.out.println("Digite uma categoria:");
		String nome = leitura.nextLine();
		Optional<Integer> count = produtoRepository.countProdutos(nome);
		
		if (count.isPresent()) {
			System.out.printf("A categoria %s possui: %d produto(s) registrado(s).%n", nome, count.get());

		} else {
			System.out.println("Categoria não encontrada ou sem produtos.");
		}
		
	}

	public void categoriaMais10Produtos() {
		System.out.println("Categorias com mais de 10 produtos: ");
		List<Categoria> categorias = categoriaRepository.categoriaMais10Produtos();
		
		categorias.forEach(c -> System.out.println(c.getNome()));
	}
}




