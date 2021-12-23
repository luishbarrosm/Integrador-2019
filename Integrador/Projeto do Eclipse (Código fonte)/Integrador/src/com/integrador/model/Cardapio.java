package com.integrador.model;

public class Cardapio {
	private int IdCard;
	private String descricao;
	private String nome;
	private double preco;
	
	
	public Cardapio() {
		
	}
	public Cardapio(int idCard, String descricao, String nome, double preco) {
		IdCard = idCard;
		this.descricao = descricao;
		this.nome = nome;
		this.preco = preco;
	}
	public int getIdCard() {
		return IdCard;
	}
	public void setIdCard(int idCard) {
		IdCard = idCard;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	}

	


