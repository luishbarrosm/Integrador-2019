package com.integrador.model;

public class Cartao extends Cliente {
	private String numero;
	private String nome;
	private int idCart;
	private String cvv;
	private String validade;
	private int idCli;


	public Cartao() {
		
	}

	public Cartao(String numero, String nome, int idCart, String cvv, String validade) {
		
		this.numero = numero;
		this.nome = nome;
		this.idCart = idCart;
		this.cvv = cvv;
		this.validade = validade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public int getIdCli() {
		return idCli;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}
	

	}


