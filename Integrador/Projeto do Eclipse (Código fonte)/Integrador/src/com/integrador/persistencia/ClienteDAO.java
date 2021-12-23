package com.integrador.persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Cliente;



	public class ClienteDAO {
		
		private ConexaoMysql conexao;
		
		public ClienteDAO() {
			super();
			this.conexao = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "integrador");
		}
		

		public Cliente salvar(Cliente cliente) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO cliente(rua, bairro, cidade, numero, complemento) VALUES(?,?,?,?,?)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, cliente.getRua());
				statement.setString(2, cliente.getBairro());
				statement.setString(3, cliente.getCidade());
				statement.setString(4, cliente.getNumero());
				statement.setString(5, cliente.getComplemento());
				
			
				

				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					cliente.setIdCliente(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return cliente;
		}
		
		public boolean editar(Cliente cliente) {
			this.conexao.abrirConexao();
			String sqlUpdate = "UPDATE cliente SET rua=?, bairro=?, cidade=?, numero=?, complemento=?, WHERE id_cliente=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setString(1, cliente.getRua());
				statement.setString(2, cliente.getBairro());
				statement.setString(3, cliente.getCidade());
				statement.setString(4, cliente.getNumero());
				statement.setString(5, cliente.getComplemento());
				statement.setInt(6, cliente.getIdCliente());
				statement.setInt(7, cliente.getIdUsu());
				
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
			String sqlExcluir = "DELETE FROM cliente WHERE id_cliente=?";
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

		
		public Cliente buscarPorId(long id) {
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM cliente WHERE id_cliente=?";
			Cliente cliente = null;
			
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
			
				if(rs.next()) {
					cliente = new Cliente();
					cliente.setRua(rs.getString("rua"));
					cliente.setBairro(rs.getString("bairro"));
					cliente.setNumero(rs.getString("numero"));
					cliente.setComplemento(rs.getString("complemento"));
					cliente.setIdCliente(rs.getInt("id_cliente"));
					cliente.setIdUsu(rs.getInt("id_usu"));
					
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return cliente;
		}
		
		
		public List<Cliente> buscarTodos() {
			this.conexao.abrirConexao();
			String sqlBuscarTodos = "SELECT * FROM cliente";
			List<Cliente> listaclientes = new ArrayList<Cliente>();
			Cliente cliente = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					cliente = new Cliente();
					cliente.setRua(rs.getString("rua"));
					cliente.setBairro(rs.getString("bairro"));
					cliente.setNumero(rs.getString("numero"));
					cliente.setComplemento(rs.getString("complemento"));
					cliente.setIdCliente(rs.getInt("id_cliente"));
					cliente.setIdUsu(rs.getInt("id_usu"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return listaclientes;
		}
		
		
		public Cliente buscarPorTudo(String rua, String bairro, String numero, String complemento, int idCliente, int idUsu) {
			this.conexao.abrirConexao();
			String sqlBuscarPorLoginSenha = "SELECT * FROM usuario WHERE rua=?, bairro=?, cidade=?, numero=?, complemento=?";
			Cliente cliente = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorLoginSenha);
				statement.setString(1, rua);
				statement.setString(2, bairro);
				statement.setString(3, numero);
				statement.setString(4, complemento);
				statement.setInt(5, idCliente);
				statement.setInt(6, idUsu);
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					cliente = new Cliente();
					cliente.setRua(rs.getString("rua"));
					cliente.setBairro(rs.getString("bairro"));
					cliente.setNumero(rs.getString("numero"));
					cliente.setComplemento(rs.getString("complemento"));
					cliente.setIdCliente(rs.getInt("id_cliente"));
					cliente.setIdUsu(rs.getInt("id_usu"));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return cliente;
		}	
	}

	
	
	

