package com.integrador.apresentacao;

import java.util.Scanner;

import com.integrador.model.Cardapio;
import com.integrador.model.Cartao;
import com.integrador.model.Cliente;
import com.integrador.model.Restaurante;
import com.integrador.model.Usuario;
import com.integrador.persistencia.CardapioDAO;
import com.integrador.persistencia.CartaoDAO;
import com.integrador.persistencia.ClienteDAO;
import com.integrador.persistencia.RestauranteDAO;
import com.integrador.persistencia.UsuarioDAO;

public class Main {

	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		Cliente cliente = new Cliente();
		RestauranteDAO rDAO = new RestauranteDAO();
		UsuarioDAO uDAO = new UsuarioDAO();
		ClienteDAO cDAO = new ClienteDAO();
		CardapioDAO cpDAO = new CardapioDAO();
		CartaoDAO cdDAO = new CartaoDAO();
		Usuario usuario = new Usuario();
		System.out.println("Fa�a seu cadastro de usu�rio: ");
		System.out.println("Nome completo: ");
		usuario.setNome(t.nextLine());
		System.out.println("E-mail: ");
		usuario.setEmail(t.nextLine());
		System.out.println("CPF: ");
		String x1 = t.nextLine();
		usuario.setCpf(x1);
		System.out.print("Senha: ");
		String y = t.nextLine();
		usuario.setSenha(y);
		usuario = uDAO.salvar(usuario);

		System.out.println("Selecione o tipo de cadastro: Restaurante ou Cliente");
		String c1 = t.next();
		if (c1.equalsIgnoreCase("Restaurante")) {
			System.out.println("Voc� selecionou a op��o Restaurante, agora preencha os dados o restaurante");
			Restaurante restaurante = new Restaurante();
			System.out.println("Nome: ");
			String x = t.next();
			restaurante.setNome(x);
			System.out.println("Cidade: ");
			t.nextLine();
			restaurante.setCidade(t.nextLine());

			System.out.println("Bairro: ");
			restaurante.setBairro(t.nextLine());
			System.out.println("Rua: ");
			restaurante.setRua(t.nextLine());
			System.out.println("N�mero: ");
			restaurante.setNumero(t.nextLine());
			restaurante = rDAO.salvar(restaurante);

			System.out.println("Cadastre seu card�pio: ");
			Cardapio cardapio = new Cardapio();
			System.out.println("Quantas produtos deseja cadastrar?");
			int v11 = t.nextInt();
			for (int v0 = 0; v0 < v11; v0++) {
				System.out.println("Nome do prato/bebida:");
				t.nextLine();
				cardapio.setNome(t.nextLine());
				System.out.println("Descri��o: ");
				cardapio.setDescricao(t.nextLine());
				System.out.println("Pre�o: ");
				cardapio.setPreco(t.nextDouble());
				cardapio = cpDAO.salvar(cardapio);
			}

			System.out.println("Selecione uma op��o editar, excluir, buscar ou seguir: ");
			String c2 = t.nextLine();
			if (c2.equalsIgnoreCase("Editar")) {
				boolean editou = rDAO.editar(restaurante);
				if (editou) {
					System.out.println("Editado com sucesso");
				} else {
					System.out.println("N�o foi poss�vel editar");
				}

			} else {
				if (c2.equalsIgnoreCase("Excluir")) {
					long v5 = restaurante.getIdRes();
					boolean excluiu = rDAO.excluir(v5);
					if (excluiu) {
						System.out.println("Excluido com sucesso");
					} else {
						System.out.println("N�o foi poss�vel excluir");
					}

				} else {
					if (c2.equalsIgnoreCase("Buscar")) {
						System.out.println("Selecione buscar por: id, nome e cidade, ou buscar tudo ");
						String c3 = t.nextLine();
						if (c3.equalsIgnoreCase("id")) {

						} else {
							if (c3.equalsIgnoreCase("nome e cidade")) {
								rDAO.buscarPorNomeCidade(t.nextLine(), t.nextLine());
							} else {
								rDAO.buscarTodos();
							}
						}
					}

				}
			}

		} else {
			if (c1.equalsIgnoreCase("Cliente")) {
				System.out.println("Voc� selecionou a op��o Cliente");
				System.out.println("Nome: ");
				t.nextLine();
				cliente.setNome(t.nextLine());
				System.out.println("Cidade: ");
				cliente.setCidade(t.nextLine());
				System.out.println("Bairro: ");
				cliente.setBairro(t.nextLine());
				System.out.println("Rua: ");
				cliente.setRua(t.nextLine());
				System.out.println("N�mero: ");
				cliente.setNumero(t.nextLine());
				System.out.println("Complemento(se necess�rio): ");
				cliente.setComplemento(t.nextLine());
				cliente = cDAO.salvar(cliente);

				System.out.println("Selecione uma op��o editar, excluir, buscar ou seguir: ");
				String c4 = t.nextLine();
				if (c4.equalsIgnoreCase("Editar")) {
					boolean editou1 = cDAO.editar(cliente);
					if (editou1) {
						System.out.println("Editado com sucesso");
					} else {
						System.out.println("N�o foi poss�vel editar");
					}

				} else {
					if (c4.equalsIgnoreCase("Excluir")) {
						long v8 = cliente.getIdCliente();
						boolean excluiu1 = cDAO.excluir(v8);
						if (excluiu1) {
							System.out.println("Excluido com sucesso");
						} else {
							System.out.println("N�o foi poss�vel excluir");
						}
					} else {
						if (c4.equalsIgnoreCase("Buscar")) {
							System.out.println("Selecione buscar por: id ou buscar tudo ");
							String c5 = t.nextLine();
							if (c5.equalsIgnoreCase("id")) {
								cDAO.buscarPorId(t.nextLong());
								System.out.println();
							} else {
								if (c5.equalsIgnoreCase("buscar tudo")) {
									cDAO.buscarTodos();
								}
							}
						}

					}
				}

				Cartao cartao = new Cartao();
				System.out.println("Cadastre seu cart�o: ");
				System.out.println("N�mero: ");
				cartao.setNumero(t.nextLine());
				t.next();
				System.out.println("Nome: ");
				cartao.setNome(t.nextLine());
				System.out.println("C�digo de seguran�a: ");
				cartao.setCvv(t.nextLine());
				System.out.println("Validade(formato: mm/aa): ");
				cartao.setValidade(t.nextLine());
				cartao = cdDAO.salvar(cartao);

				System.out.println("Selecione uma op��o editar, excluir, buscar ou seguir: ");
				String c6 = t.nextLine();
				if (c6.equalsIgnoreCase("Editar")) {
					cdDAO.editar(cartao);

				} else {
					if (c6.equalsIgnoreCase("Excluir")) {
						cdDAO.excluir(t.nextLong());
					} else {
						if (c6.equalsIgnoreCase("Buscar")) {
							System.out.println("Selecione buscar por: id, email e senha, ou buscar tudo ");
							String c7 = t.nextLine();
							if (c7.equalsIgnoreCase("id")) {
								cdDAO.buscarPorId(t.nextLong());
							} else {
								if (c7.equalsIgnoreCase("email e senha")) {
									cdDAO.buscarPorEmailSenha(t.nextLine(), t.nextLine());
								} else {
									cdDAO.buscarTodos();
								}
							}
						}

					}
				}

			} else {
				System.out.println("Entrada inv�lida");
			}
		}

		t.close();
	}

}