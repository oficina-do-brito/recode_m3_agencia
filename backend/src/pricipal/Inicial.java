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

public class Inicial {

	public static void main(String[] args) throws IOException {
		Connection conexao = Db.getConnection();
		System.out.println("Espere um momento Major, criando as tabelas ...");
		Db.criarDatabase();
		System.out.printf("Se não existiam foram criadas %n %n");

		Scanner sc = new Scanner(System.in);
		int opcao, opcaoTwo, idUser;
		Utils uts = new Utils();

		Administrador a = null;Cliente c = null;Fornecedor f = null;Usuario u = null;
		PacoteViagem pv = null;Hospedagem h = null;Passagem ps = null;OrigemDestino od = null;

		do {
			uts.exibirMenuPrincipal();
			opcao = sc.nextInt();
			System.out.printf("%n %n");
			switch (opcao) {
			case 0:
				System.out.println("Você ira sair... espere fechando programa...");
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
							// a.setIdUsuario(u.getId());
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
					default:
						System.out.println("opção invalida");
					}

				} while (opcaoTwo != 0);
				break;

			case 2: // Menu Cliente
				c = new Cliente();
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
				break;
			case 3: // Menu Fornecedores
				f = new Fornecedor();
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
								"Digite seu tipo de Serviço que você oferece no momento \"1\" - para Fornecimento de Passagens  \"2\" - Para Hospedagem : ");
						tipoServico = sc.nextInt();
						f.setTipoServico(tipoServico);
						break;
					case 2:
						ps = Passagem.preencherPassagem(sc);
						f.fornecer(ps);
						break;
					case 3: // fornecer hospedagem, cria-se uma hospedagem,pega-se um endereço para destino coloca na hospedagem, toda hospedagem é um destino para o cliente
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
				break;
			case 4: // Menu Pacotes de Viagens
				pv = new PacoteViagem();
				do {
					uts.exibirMenuViagem();
					opcaoTwo = sc.nextInt();
					System.out.printf("%n %n %n %n");
					switch (opcaoTwo) {
					case 0:
						System.out.println("Você ira voltar ao menu principal...");
						break;
					case 1:
						pv.readAll();
						break;
					default:
						System.out.println("opção invalida");
					}

				} while (opcaoTwo != 0);
				break;
			case 5: // Menu Admin
				a = new Administrador();
				do {
					uts.exibirMenuFornecedor();
					opcaoTwo = sc.nextInt();
					System.out.printf("%n %n %n %n");
					switch (opcaoTwo) {
					case 0:
						System.out.println("Você ira voltar ao menu principal...");
						break;
					case 1:
						int n;
						System.out.print("Digite o  numeroViagemRevisadas: ");
						n = sc.nextInt();
						a.setNumeroViagemRevisadas(n);
						break;
					case 2:
						a.delete();
						break;
					case 3:
						a.readAll();
						break;
					default:
						System.out.println("opção invalida");
					}

				} while (opcaoTwo != 0);
				break;
			default:
				System.out.println("opção invalida");
			}
		} while (opcao != 0);
		sc.close();
		Db.closeConnection(conexao);
		System.out.println("Fim !");
	}

}