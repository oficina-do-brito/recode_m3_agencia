package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import DAO.UsuarioDAO;
import models.utils.ICRUD;
import models.utils.Verificadora;

public class Usuario implements ICRUD {
	private Integer id;
	private String nome;
	private String email;
	private String password;
	private String telefone;
	private String imagem;
	private Date dataLogin;
	private Integer tipoUsuario;
	private Integer idEndereco;

	private Endereco endereco = new Endereco();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario() {
		super();
	}

	public Usuario(Integer id, String nome, String email, String password, String telefone, String imagem, Date data,
			Integer tipoUsuario, Integer idEndereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.telefone = telefone;
		this.imagem = imagem;
		this.dataLogin = data;
		this.tipoUsuario = tipoUsuario;
		this.idEndereco = idEndereco;
	}

	public Usuario(String nome, String email, String password, String telefone, String imagem, Integer tipoUsuario,
			Integer idEndereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.telefone = telefone;
		this.imagem = imagem;
		this.tipoUsuario = tipoUsuario;
		this.idEndereco = idEndereco;
	}

	public Usuario(String nome, String email, String password, String telefone, String imagem, Integer tipoUsuario,
			String cEP, String estado, String cidade, String bairro, String rua, Integer numero) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.telefone = telefone;
		this.imagem = imagem;
		this.tipoUsuario = tipoUsuario;
		this.endereco.setCEP(cEP);
		this.endereco.setEstado(estado);
		this.endereco.setCidade(cidade);
		this.endereco.setBairro(bairro);
		this.endereco.setRua(rua);
		this.endereco.setNumero(numero);
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setEndereco(Integer id, String cEP, String estado, String cidade, String bairro, String rua,
			Integer numero) {
		this.endereco.setId(id);
		this.endereco.setCEP(cEP);
		this.endereco.setEstado(estado);
		this.endereco.setCidade(cidade);
		this.endereco.setBairro(bairro);
		this.endereco.setRua(rua);
		this.endereco.setNumero(numero);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "É um Usuario com -> nome=" + nome + ", email=" + email + ", password=" + password + ", telefone="
				+ telefone + ", imagem=" + imagem + ", dataLogin=" + dataLogin + ", tipoUsuario=" + tipoUsuario
				+ ", idEndereco=" + idEndereco + ".";
	}

	public void buscarPorID(int id) {
		Usuario clone = this.usuarioDAO.findById(id);
		this.id = clone.getId();
		this.nome = clone.getNome();
		this.email = clone.getEmail();
		this.password = clone.getPassword();
		this.telefone = clone.getTelefone();
		this.imagem = clone.getImagem();
		this.dataLogin = clone.getDataLogin();
		this.tipoUsuario = clone.getTipoUsuario();
		this.idEndereco = clone.getIdEndereco();
	}

	@Override
	public void create() {
		this.endereco.create();
		this.id = this.usuarioDAO.save(new Usuario(this.nome, this.email, this.password, this.telefone, this.imagem,
				this.tipoUsuario, this.endereco.getId()));
	}

	@Override
	public void update() {
		if (this.id != null) {
			this.usuarioDAO.update(new Usuario(this.id, this.nome, this.email, this.password, this.telefone,
					this.imagem, this.dataLogin, this.tipoUsuario, this.idEndereco));
		}
	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.usuarioDAO.deleteById(this.id);
		} else {
			System.out.println("Esse usuario \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		ArrayList<Usuario> usuarios = this.usuarioDAO.findAll();
		for (Usuario u : usuarios) {
			System.out.println(u.toString());
		}
	}
	public boolean authenticar(){
		Usuario aut = this.usuarioDAO.findByEmailAndPassoword(this.email,this.password);
		if(aut != null){
			this.id = aut.getId();
			this.tipoUsuario = aut.getTipoUsuario();
			return true;
		}
		return false;
		
	}

	public static Usuario preencherUsuario(Scanner sc) {
		String nome, email, senha, telefone, imagem, cep, estado, cidade, bairro, rua;
		int tipoUsuario, numero;
		do {
			System.out.println("Digite seu nome abaixo:");
			sc.nextLine();
			nome = sc.nextLine();
		} while (nome.length() > 150);

		do {
			System.out.println("Digite seu email abaixo: ");
			email = sc.next();
		} while (email.length() > 150);

		do {
			System.out.println("Digite sua senha: ");
			senha = sc.next();
		} while (senha.length() > 50);

		do {
			System.out.println("Digite seu telefone: ");
			telefone = sc.next();
		} while (telefone.length() > 50);

		do {
			System.out.println("Digite o endereço de uma imagem  sua: ");
			sc.nextLine();
			imagem = sc.nextLine();
		} while (imagem.length() > 600);

		do {
			System.out.println(
					"Digite o tipo de usuario que  corresponde a você 1-Administrador 2-Fornecedor 3-Cliente : ");
			tipoUsuario = sc.nextInt();
		} while (!Verificadora.verificaTipoUsuarioExists(tipoUsuario));

		do {
			System.out.println("Informe o cep da sua cidade  onde reside: ");
			cep = sc.next();
		} while (cep.length() > 150);

		do {
			System.out.println("Informe a (Sigla) seu estado onde reside : ");
			estado = sc.next();
		} while (!Verificadora.verificaEstado(estado));

		do {
			System.out.println("Informe cidade : ");
			sc.nextLine();
			cidade = sc.nextLine();
		} while (cidade.length() > 150);

		do {
			System.out.println("Informe o bairro da sua cidade : ");
			bairro = sc.nextLine();
			sc.nextLine();
		} while (bairro.length() > 150);

		do {
			System.out.println("Informe a rua da sua cidade onde reside: ");
			rua = sc.nextLine();
			sc.nextLine();
		} while (rua.length() > 150);

		System.out.println("Informe o numero da sua casa: ");
		numero = sc.nextInt();
		return new Usuario(nome, email, senha, telefone, imagem, tipoUsuario, cep, estado, cidade, bairro, rua, numero);
	}
}