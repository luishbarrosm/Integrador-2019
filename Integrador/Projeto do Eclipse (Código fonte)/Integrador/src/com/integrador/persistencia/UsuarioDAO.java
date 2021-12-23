package com.integrador.persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Usuario;

public class UsuarioDAO {
	
	private ConexaoMysql conexao;
	
	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "integrador");
	}
	

	public Usuario salvar(Usuario usuario) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO usuario (nome, email, cpf, senha) VALUES(?,?,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getCpf());
			statement.setString(4, usuario.getSenha());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				usuario.setIdUsu(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}
	
	public boolean editar(Usuario usuario) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE usuario SET nome=?, email=?, cpf=?, senha=? WHERE id_usuario=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getCpf());
			statement.setString(4, usuario.getSenha());
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
	
	
	public boolean excluir(String email) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE FROM usuario WHERE email=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setString(1, email);
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

	
	public Usuario buscarPorId(int id) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM usuario WHERE id_usuario=?";
		Usuario usuario = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
		
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsu(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSenha(rs.getString("senha"));
			
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return usuario;
	}
	
	
	public List<Usuario> buscarTodos() {
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM usuario";
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsu(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCpf(rs.getString("Cpf"));
				usuario.setSenha(rs.getString("senha"));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listaUsuarios;
	}
	
	
	public Usuario buscarPorEmailSenha(String email, String senha) {
		this.conexao.abrirConexao();
		String sqlBuscarPorLoginSenha = "SELECT * FROM usuario WHERE email=? AND senha=?";
		Usuario usuario = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorLoginSenha);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsu(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return usuario;
	}	
}
