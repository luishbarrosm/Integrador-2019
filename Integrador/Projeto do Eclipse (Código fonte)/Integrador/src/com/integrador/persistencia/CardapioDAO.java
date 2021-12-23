package com.integrador.persistencia;



	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

import com.integrador.model.Cardapio;


	public class CardapioDAO {
		
		private ConexaoMysql conexao;
		
		public CardapioDAO() {
			super();
			this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "Integraterz");
		}
		

		public Cardapio salvar(Cardapio cardapio) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO cardapio(descricao, nome, preco) VALUES(?,?,?)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			
				statement.setString(1, cardapio.getDescricao());
				statement.setString(2, cardapio.getNome());
				statement.setDouble(3, cardapio.getPreco());
				
				

				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					cardapio.setIdCard(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return cardapio;
		}
		
		public boolean editar(Cardapio cardapio) {
			this.conexao.abrirConexao();
			String sqlUpdate = "UPDATE cardapio SET descricao=?, nome=?, preco=?, WHERE id_cardapio=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setString(1, cardapio.getDescricao());
				statement.setString(2, cardapio.getNome());
				statement.setDouble(3, cardapio.getPreco());
				
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
			String sqlExcluir = "DELETE FROM cardapio WHERE id_cardapio=?";
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

		
		public Cardapio buscarPorId(long id) {
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM cardapio WHERE id_cardapio=?";
			Cardapio cardapio = null;
			
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
			
				if(rs.next()) {
					cardapio = new Cardapio();
					cardapio.setIdCard(rs.getInt("id_cardapio"));
					cardapio.setDescricao(rs.getString("descricao"));
					cardapio.setPreco(rs.getDouble("preco"));
					
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return cardapio;
		}
		
		
		public List<Cardapio> buscarTodos() {
			this.conexao.abrirConexao();
			String sqlBuscarTodos = "SELECT * FROM cardapio";
			List<Cardapio> listaCardapios = new ArrayList<Cardapio>();
			Cardapio cardapio = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					cardapio = new Cardapio();
					cardapio.setIdCard(rs.getInt("id_cardapio"));
					cardapio.setDescricao(rs.getString("descricao"));
					cardapio.setPreco(rs.getDouble("preco"));
					listaCardapios.add(cardapio);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return listaCardapios;
		}
		
		
		public Cardapio buscarPorTudo(String descricao, String nome) {
			this.conexao.abrirConexao();
			String sqlBuscarPorLoginSenha = "SELECT * FROM usuario WHERE email=? AND senha=?";
			Cardapio cardapio = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorLoginSenha);
				statement.setString(1, descricao);
				statement.setString(2, nome);
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					cardapio = new Cardapio();
					cardapio.setIdCard(rs.getInt("id_usuario"));
					cardapio.setDescricao(rs.getString("nome"));
					cardapio.setNome(rs.getString("email"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return cardapio;
		}	
	}

	
	
	

