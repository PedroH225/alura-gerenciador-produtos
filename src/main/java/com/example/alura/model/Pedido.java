package com.example.alura.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private LocalDate data;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pedido_produto", 
	joinColumns = @JoinColumn(name = "pedido_id"),
	inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos;

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(String id, LocalDate data, List<Produto> produtos) {
		this.id = id;
		this.data = data;
		this.produtos = produtos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", produtos=" + produtos + "]";
	}

	
	
}
