package com.integrador.model;

public class Restaurante extends Usuario{
	private long idRes;
	private String nome;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;

	public Restaurante() {
		super();
	}

	public Restaurante(long idRes, String nome, String rua, String numero, String bairro, String cidade) {
		super();
		this.idRes = idRes;
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public long getIdRes() {
		return idRes;
	}

	public void setIdRes(long idRes) {
		this.idRes = idRes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}