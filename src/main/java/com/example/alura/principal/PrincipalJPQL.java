package com.example.alura.principal;

import java.util.Scanner;

import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.FornecedorRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepository;
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
	
	
}




