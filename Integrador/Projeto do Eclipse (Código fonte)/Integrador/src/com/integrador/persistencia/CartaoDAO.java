package com.integrador.persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Cartao;


public class CartaoDAO {
	
	private ConexaoMysql conexao;
	
	public CartaoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "Integraterz");
	}
	

	public Cartao salvar(Cartao cartao) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO cartao(numero,nome,cvv,validade) VALUES(?,?,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, cartao.getNumero());
			statement.setString(2, cartao.getNome());
			statement.setString(3, cartao.getCvv());
			statement.setString(4, cartao.getValidade());
	
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				cartao.setIdCart(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return cartao;
	}
	
	public boolean editar(Cartao cartao) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE cartao SET numero=?, nome=?, cvv=?, validade=? WHERE id_cartao=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, cartao.getNumero());
			statement.setString(2, cartao.getNome());
			statement.setString(3, cartao.getCvv());
			statement.setString(4, cartao.getValidade());
			statement.setLong(5, cartao.getIdCli());
			int linhasAfetadas = statement.executeUpdate();
			if(linhasAfetadas>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return false;
	}
	
	
	public boolean excluir(long id) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE FROM Cartao WHERE id_Cartao=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, id);
			int linhasAfetadas = statement.executeUpdate();
			if(linhasAfetadas>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return false;
	}

	
	public Cartao buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM Cartao WHERE id_Cartao=?";
		Cartao Cartao = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
		
			if(rs.next()) {
				Cartao = new Cartao();
				Cartao.setNumero(rs.getString("numero"));
				Cartao.setNome(rs.getString("nome"));
				Cartao.setIdCart(rs.getInt("id_cartao"));
				Cartao.setCvv(rs.getString("cvv"));
				Cartao.setValidade(rs.getString("validade"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return Cartao;
	}
	
	
	public List<Cartao> buscarTodos() {
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM Cartao";
		List<Cartao> listaCartaos = new ArrayList<Cartao>();
		Cartao Cartao = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Cartao = new Cartao();
				Cartao.setNumero(rs.getString("numero"));
				Cartao.setNome(rs.getString("nome"));
				Cartao.setIdCart(rs.getInt("id_cartao"));
				Cartao.setCvv(rs.getString("cvv"));
				Cartao.setValidade(rs.getString("validade"));
				listaCartaos.add(Cartao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listaCartaos;
	}
	
	
	public Cartao buscarPorEmailSenha(String email, String senha) {
		this.conexao.abrirConexao();
		String sqlBuscarPorLoginSenha = "SELECT * FROM Cartao WHERE email=? AND senha=?";
		Cartao Cartao = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorLoginSenha);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				Cartao = new Cartao();
				Cartao.setNumero(rs.getString("numero"));
				Cartao.setNome(rs.getString("nome"));
				Cartao.setIdCart(rs.getInt("id_cartao"));
				Cartao.setCvv(rs.getString("cvv"));
				Cartao.setValidade(rs.getString("validade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return Cartao;
	}	
}
