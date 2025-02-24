package com.example.alura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.alura.principal.Principal;
import com.example.alura.repository.CategoriaRepository;
import com.example.alura.repository.PedidoRepository;
import com.example.alura.repository.ProdutoRepository;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	
	private CategoriaRepository categoriaRepository;
	
	private ProdutoRepository produtoRepository;
	
	private PedidoRepository pedidoRepository;
	
	public GerenciadorPedidosApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			PedidoRepository pedidoRepository) {
		
		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(categoriaRepository, produtoRepository, pedidoRepository);
		principal.executar();
		
	}

}
