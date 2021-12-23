package com.integrador.persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Restaurante;

public class RestauranteDAO {
	
	private ConexaoMysql conexao;
	
	public RestauranteDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "integrador");
	}
	

	public Restaurante salvar(Restaurante Restaurante) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO restaurante (nome, rua ,numero, bairro, cidade) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, Restaurante.getNome());
			statement.setString(2, Restaurante.getRua());
			statement.setString(3, Restaurante.getNumero());
			statement.setString(4, Restaurante.getBairro());
			statement.setString(5, Restaurante.getCidade());
			

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				Restaurante.setIdRes(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return Restaurante;
	}
	
	public boolean editar(Restaurante Restaurante) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE Restaurante SET nome=?, email=?, cpf=?, senha=? WHERE id_Restaurante=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setLong(1, Restaurante.getIdRes());
			statement.setString(2, Restaurante.getNome());
			statement.setString(3, Restaurante.getRua());
			statement.setString(4, Restaurante.getNumero());
			statement.setString(5, Restaurante.getBairro());
			statement.setString(6, Restaurante.getCidade());
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
	
	
	public boolean excluir (Long id) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE FROM Restaurante WHERE id_restaurante=?";
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

	
	public Restaurante buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM Restaurante WHERE id_Restaurante=?";
		Restaurante Restaurante = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
		
			if(rs.next()) {
				Restaurante = new Restaurante();
				Restaurante.setIdRes(rs.getInt("id_Restaurante"));
				Restaurante.setNome(rs.getString("nome"));
				Restaurante.setRua(rs.getString("rua"));
				Restaurante.setNumero(rs.getString("numero"));
				Restaurante.setBairro(rs.getString("bairro"));
				Restaurante.setCidade(rs.getString("cidade"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return Restaurante;
	}
	
	
	public List<Restaurante> buscarTodos() {
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM Restaurante";
		List<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		Restaurante Restaurante = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Restaurante = new Restaurante();
				Restaurante.setIdRes(rs.getInt("id_Restaurante"));
				Restaurante.setNome(rs.getString("nome"));
				Restaurante.setRua(rs.getString("rua"));
				Restaurante.setNumero(rs.getString("numero"));
				Restaurante.setBairro(rs.getString("bairro"));
				Restaurante.setCidade(rs.getString("cidade"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listaRestaurantes;
	}
	
	
	public Restaurante buscarPorNomeCidade(String nome, String cidade) {
		this.conexao.abrirConexao();
		String sqlBuscarPorLoginSenha = "SELECT * FROM Restaurante WHERE nome=? AND cidade=?";
		Restaurante Restaurante = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorLoginSenha);
			statement.setString(1, nome);
			statement.setString(2, cidade);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				Restaurante = new Restaurante();
				Restaurante.setNome(rs.getString("nome"));
				Restaurante.setCidade(rs.getString("Cidade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return Restaurante;
	}	
}
