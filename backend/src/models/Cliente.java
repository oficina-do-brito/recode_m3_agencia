package models;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import DAO.ClienteDAO;
import pricipal.utils.Colors;
import pricipal.utils.Utils;

public class Cliente extends Usuario {
	private Integer id;
	private String RG;
	private String CPF;
	private Integer numeroViagens;
	private String cartaoCredito;
	private Integer idUsuario;

	private CarrinhoCompra carrinho = new CarrinhoCompra();
	private ClienteDAO clienteDAO = new ClienteDAO();

	public Cliente() {
		super();
	}

	public Cliente(String rG, String cPF, Integer numeroViagens, String cartaoCredito, Integer idUsuario) {
		super();
		this.RG = rG;
		this.CPF = cPF;
		this.numeroViagens = numeroViagens;
		this.cartaoCredito = cartaoCredito;
		this.idUsuario = idUsuario;
	}

	public Cliente(String rG, String cPF, Integer numeroViagens, String cartaoCredito) {
		super();
		this.RG = rG;
		this.CPF = cPF;
		this.numeroViagens = numeroViagens;
		this.cartaoCredito = cartaoCredito;
	}

	public Cliente(Integer id, String rG, String cPF, Integer numeroViagens, String cartaoCredito, Integer idUsuario) {
		super();
		this.id = id;
		this.RG = rG;
		this.CPF = cPF;
		this.numeroViagens = numeroViagens;
		this.cartaoCredito = cartaoCredito;
		this.idUsuario = idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Integer getNumeroViagens() {
		return numeroViagens;
	}

	public void setNumeroViagens(Integer numeroViagens) {
		this.numeroViagens = numeroViagens;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setcartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setidUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "é um Cliente com -> id=" + id + ", RG=" + RG + ", CPF=" + CPF + ", numeroViagens=" + numeroViagens
				+ ", cartaoCredito=" + cartaoCredito + ", idUsuario=" + idUsuario + ", Tambem," + super.toString()
				+ " ";
	}

	@Override
	public void create() {
		this.id = this.clienteDAO
				.save(new Cliente(this.RG, this.CPF, this.numeroViagens, this.cartaoCredito, this.idUsuario));
	}

	@Override
	public void update() {
		if (this.id != null) {
			this.clienteDAO.update(
					new Cliente(this.id, this.RG, this.CPF, this.numeroViagens, this.cartaoCredito, this.idUsuario));
		} else {
			System.out.println(Utils.escreverColorido(Colors.VERMELHO, "impossivel atualizar esse Cliente..."));
		}
	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.clienteDAO.deleteById(this.id);
		} else {
			System.out.println(Utils.escreverColorido(Colors.VERMELHO,
					"Esse cliente \"Não foi encontrado\", logo não houve deleção."));
		}
	}

	@Override
	public void readAll() {
		ArrayList<Cliente> clientes = this.clienteDAO.findAll();
		for (Cliente cliente : clientes) {
			System.out.println(cliente.toString());
		}
	}

	public void adquerirPacote(PacoteViagem pv) {
		this.carrinho.addInPacotes(pv);
		if (this.carrinho == null) {
			System.out.println("Carrinho vazio");
		} else {
			System.out.printf("Adicionado carrinho agora contem %d", this.carrinho.getPacotes().size());
		}
	}

	public void efetuarPagamentoPacote() {
		Scanner scs = new Scanner(System.in);
		String ids;
		double valor;

		System.out.printf("Informe os id(s) de ambos os pacotes que vc quer pagar separados por /, : ");
		ids = scs.next();
		System.out.printf("Informe o valor do pagamento : ");
		valor = scs.nextDouble();
		String[] axilar = ids.split(",");
		int[] idsNumericos = new int[50];
		int i = 0;
		for (String num : axilar) {
			idsNumericos[i] = Integer.parseInt(num);
		}
		this.numeroViagens += this.carrinho.realizarPagamento(valor, idsNumericos);

		scs.close();
	}

	public static Cliente preencherCliente(Scanner sc) {
		String RG, CPF, cartaoCredito;
		do {
			System.out.print("Digite seu o seu RG: ");
			RG = sc.next();
			if (RG.length() > 7) {
				System.out.println("Informe um rg com 7 caracteres pfv...");
			}
		} while (RG.length() > 8);

		do {
			System.out.print("Digite seu o seu CPF: ");
			CPF = sc.next();
			if (CPF.length() > 7) {
				System.out.println("Informe um cpf com 11 caracteres pfv...");
			}
		} while (CPF.length() > 12);

		do {
			System.out.print("Digite seu cartão de credito: ");
			cartaoCredito = sc.next();
			if (cartaoCredito.length() > 7) {
				System.out.println("Informe um caratao com 16 caracteres pfv...");
			}
		} while (cartaoCredito.length() > 17);

		return new Cliente(RG, CPF, 0, cartaoCredito);
	}

	public void buscarPorId() {
		Cliente c = this.clienteDAO.findById(this.id);
		System.out.println(c.toString());
		this.id = c.getId();
		this.setNome(c.getNome());
		this.setEmail(c.getEmail());
		this.setPassword(c.getPassword());
		this.setTelefone(c.getTelefone());
		this.setImagem(c.getImagem());
		this.setDataLogin(c.getDataLogin());
		this.setTipoUsuario(c.getTipoUsuario());
		this.setIdEndereco(c.getIdEndereco());
		this.RG = c.getRG();
		this.setCPF(c.getCPF());
		this.numeroViagens = c.getNumeroViagens();
		this.cartaoCredito = c.getCartaoCredito();
		this.idUsuario = c.getIdUsuario();
		super.setEndereco(c.getEndereco());
	}
}
