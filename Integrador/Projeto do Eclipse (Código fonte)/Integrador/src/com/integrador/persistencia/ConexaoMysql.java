package com.integrador.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql {
	private String ip;
	private String porta;
	private String login;
	private String senha;
	private String nomeBD;
	private Connection conexao;
	
	public ConexaoMysql() {
		super();
	}
	
	public ConexaoMysql(String ip, String porta, String login, String senha, String nomeBD) {
		super();
		this.ip = "localhost";
		this.porta = "3306";
		this.login = "root";
		this.senha = "ifsul2017";
		this.nomeBD = "integrador";
	}
	
	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeBD() {
		return nomeBD;
	}

	public void setNomeBD(String nomeBD) {
		this.nomeBD = nomeBD;
	}
	
	
	public void abrirConexao() {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+this.ip+":"+"3306"+"/"+"integrador";
			this.conexao = (Connection) DriverManager.getConnection(url, login, senha);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void fecharConexao() {
		try {
			if(!this.conexao.isClosed()) {
				this.conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}