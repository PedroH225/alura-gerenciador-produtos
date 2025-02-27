package com.example.alura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.alura.principal.Principal;
import com.example.alura.principal.PrincipalJPQL;
import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.FornecedorRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepository;
import com.example.alura.repository.ProdutoRepositoryJPQL;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	
	private CategoriaRepository categoriaRepository;
	
	private ProdutoRepository produtoRepository;
	
	private ProdutoRepositoryJPQL produtoRepositoryJPQL;

	private PedidoRepository pedidoRepository;
	
	private FornecedorRepository fornecedorRepository;

	
	public GerenciadorPedidosApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			ProdutoRepositoryJPQL produtoRepositoryJPQL, PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {
		
		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.produtoRepositoryJPQL = produtoRepositoryJPQL;
		this.fornecedorRepository = fornecedorRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(categoriaRepository, produtoRepository, 
				pedidoRepository, fornecedorRepository);
		
		PrincipalJPQL principalJPQL = new PrincipalJPQL(categoriaRepository, produtoRepositoryJPQL,
				pedidoRepository, fornecedorRepository);
		
		principal.registrar();
		
		System.out.println();
		principalJPQL.ordenarProdutos();
		
	}

}
