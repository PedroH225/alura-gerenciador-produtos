package com.example.alura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.alura.principal.Principal;
import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.FornecedorRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepository;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	
	private CategoriaRepository categoriaRepository;
	
	private ProdutoRepository produtoRepository;
	
	private PedidoRepository pedidoRepository;
	
	private FornecedorRepository fornecedorRepository;

	
	public GerenciadorPedidosApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {
		
		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.fornecedorRepository = fornecedorRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(categoriaRepository, produtoRepository, 
				pedidoRepository, fornecedorRepository);
		
		principal.registrar();
		principal.buscarPorValorOuNome();
		
	}

}
