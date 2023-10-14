package pricipal;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import db.Db;
import models.Administrador;
import models.Cliente;
import models.Fornecedor;
import models.Hospedagem;
import models.OrigemDestino;
import models.PacoteViagem;
import models.Passagem;
import models.Usuario;
import pricipal.utils.Colors;
import pricipal.utils.Utils;

public class Inicial {

	public static void main(String[] args) throws IOException {
		Utils uts = new Utils();
		Connection conexao = Db.getConnection();
		System.out.println("Espere um momento Major, criando as tabelas ...");
		Db.criarDatabase();
		System.out.printf(Utils.escreverColorido(Colors.AZUL, "Se não existiam foram criadas") + " %n %n");

		Scanner sc = new Scanner(System.in);
		int opcao, opcaoTwo, idUser;
		String email, password;
		Usuario userAuth = new Usuario();
		boolean authenticado;

		Administrador a = null;
		Cliente c = null;
		Fornecedor f = null;
		Usuario u = null;
		PacoteViagem pv = null;
		Hospedagem h = null;
		Passagem ps = null;
		OrigemDestino od = null;

		do {
			uts.exibirMenuPrincipal();
			opcao = sc.nextInt();
			System.out.printf("%n %n");
			switch (opcao) {
				case 0:
					System.out.println("Fechando programa...");
					break;
				case 1: // Menu Usuario
					u = new Usuario();
					do {
						uts.exibirMenuUsuario();
						opcaoTwo = sc.nextInt();
						System.out.printf("%n %n");
						switch (opcaoTwo) {
							case 0:
								System.out.println("Você ira voltar ao menu principal...");
								break;
							case 1:
								u = Usuario.preencherUsuario(sc);
								u.create();

								if (u.getTipoUsuario() == 1) {
									a = new Administrador(0, u.getId());
									a.create();
								}

								else if (u.getTipoUsuario() == 2) {
									f = Fornecedor.preencherFornecedor(sc);
									f.setIdUsuario(u.getId());
									f.create();
								}

								else if (u.getTipoUsuario() == 3) {
									c = Cliente.preencherCliente(sc);
									c.setidUsuario(u.getId());
									c.create();
								} else {
									System.out.println("Opção de usuario inexistenete...");
								}
								break;
							case 2:
								System.out.println("Informe o id do Usuario que vc quer atualizar: ");
								idUser = sc.nextInt();
								u = Usuario.preencherUsuario(sc);
								u.setId(idUser);
								u.update();
								break;
							case 3:
								u.delete();
								break;
							case 4:
								u.readAll();
								break;
							case 5:
								int idBuscar;
								System.out.println("Informe o id do usuario que vc quer buscar:");
								idBuscar = sc.nextInt();
								u.buscarPorID(idBuscar);
								break;
							default:
								System.out.println("opção invalida");
						}

					} while (opcaoTwo != 0);
					break;
				case 2: // Logar
					userAuth = new Usuario();
					System.out.println("Insira email do usuario: ");
					email = sc.next();
					System.out.println("Insira senha: ");
					password = sc.next();
					userAuth.setEmail(email);
					userAuth.setPassword(password);
					authenticado = userAuth.authenticar();
					if (authenticado) {
						if (userAuth.getTipoUsuario() == 3) {
							c = new Cliente();
							// *Passando id do usuario logado para cliente poder fazer abusca apartir do
							// buscarPorId() dele, pelo id e trazer o cliente */
							c.setId(userAuth.getId());
							c.buscarPorId();
							do {
								uts.exibirMenuCliente();
								opcaoTwo = sc.nextInt();
								System.out.printf("%n %n %n %n");
								switch (opcaoTwo) {
									case 0:
										System.out.println("Você ira voltar ao menu principal...");
										break;
									case 1:
										String cartaoCredito;
										int numeroViagens;
										System.out.println("Informe qual o novo cratão para esse Cliente: ");
										cartaoCredito = sc.next();
										System.out.println("Corrija o numero de viagem para esse  Cliente: ");
										numeroViagens = sc.nextInt();
										c.setcartaoCredito(cartaoCredito);
										c.setNumeroViagens(numeroViagens);
										c.update();
										break;
									case 2:
										c.delete();
										break;
									case 3:
										c.readAll();
										break;
									case 4:
										System.out.printf("Insira o id do pacote que vc quer adquerir: ");
										int pacoteId = sc.nextInt();
										pv = new PacoteViagem();
										pv.recuperarPacotevIagemById(pacoteId);
										c.adquerirPacote(pv);
										break;
									case 5:
										c.efetuarPagamentoPacote();
										break;
									default:
										System.out.println("opção invalida");
								}
							} while (opcaoTwo != 0);
						} else if (userAuth.getTipoUsuario() == 2) {
							f = new Fornecedor();
							f.setId(userAuth.getId());
							f.buscarPorId();
							do {
								uts.exibirMenuFornecedor();
								opcaoTwo = sc.nextInt();
								System.out.printf("%n %n");
								switch (opcaoTwo) {
									case 0:
										System.out.println("Você ira voltar ao menu principal...");
										break;
									case 1:
										int tipoServico;
										System.out.print(
												"Digite seu tipo de Serviço que você oferece no momento \"1\" - par Fornecimento de Passagens \"2\" - Para Hospedagem : ");
										tipoServico = sc.nextInt();
										f.setTipoServico(tipoServico);
										break;
									case 2:
										ps = Passagem.preencherPassagem(sc);
										if (f.getId() != null) {
											ps.setIdFornecedor(f.getId());
											f.fornecer(ps);
										} else {
											System.out.println("impossivel criar passagemsem fornecedor...");
										}
										break;
									case 3: // fornecer hospedagem, cria-se uma hospedagem,pega-se um endereço para
											// destino
										// coloca na hospedagem, toda hospedagem é um destino para o cliente
										int idEndereco;
										h = Hospedagem.preencherHospedagem(sc);
										h.setIdFornecedor(f.getId());
										System.out.println(
												"Dos endereços cadastrados no sistema escolha 1 e diga o id de qual o endereço do seu destino: ");
										idEndereco = sc.nextInt();
										od = OrigemDestino.preencherOrigemDestino(sc);
										od.setTipo(2);
										od.setIdEndereco(idEndereco);
										od.create();
										h.setIdOrigemDestino(od.getId());
										f.fornecer(h);
										break;
									case 4:
										f.delete();
										break;
									case 5:
										f.readAll();
										break;
									default:
										System.out.println("opção invalida");
								}

							} while (opcaoTwo != 0);
						} else if (userAuth.getTipoUsuario() == 1) {
							a = new Administrador();
							a.setId(userAuth.getId());
							a.buscarPorId();
							do {
								uts.exibirMenuAdmin();
								opcaoTwo = sc.nextInt();
								System.out.printf("%n %n %n %n");
								switch (opcaoTwo) {
									case 0:
										System.out.println("Você ira voltar ao menu principal...");
										break;
									case 1:
										int n;
										System.out.print("Digite o numero ViagemRevisadas: ");
										n = sc.nextInt();
										a.setNumeroViagemRevisadas(n);
										break;
									case 2:
										a.delete();
										break;
									case 3:
										a.readAll();
										break;
									case 4:
										a.montarPacotes(sc);
										break;
									default:
										System.out.println("opção invalida");
								}

							} while (opcaoTwo != 0);
						}else{
							System.out.println("Não existe esse tipo de Usuariono sistema.");
						}
					} else {
						System.out.println("Usuario não consta no sistema não tente novamente! ");
					}
					break;
				default:
					System.out.println("Opção invalida...");
					break;
			}
		} while (opcao != 0);
		sc.close();
		Db.closeConnection(conexao);
		System.out.println(Utils.escreverColorido(Colors.YELLOW, "Até mais"));
	}
}
// case 4: // Menu Pacotes de Viagens
// pv = new PacoteViagem();
// do {
// uts.exibirMenuViagem();
// opcaoTwo = sc.nextInt();
// System.out.printf("%n %n %n %n");
// switch (opcaoTwo) {
// case 0:
// System.out.println("Você ira voltar ao menu principal...");
// break;
// case 1:
// pv.readAll();
// break;
// default:
// System.out.println("opção invalida");
// }
// } while (opcaoTwo != 0);
// break;