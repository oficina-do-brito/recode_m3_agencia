package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import DAO.AdministradorDAO;

public class Administrador extends Usuario {
	private Integer id;
	private Integer numeroViagemRevisadas;
	private Integer idUsuario;
	
	private PacoteViagem pv = new PacoteViagem();
	private Passagem passagem = new Passagem();
	private Hospedagem hospedagem = new Hospedagem();
	private ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
	private AdministradorDAO administradorDAO = new AdministradorDAO();

	public Administrador() {
		super();
	}

	public Administrador(Integer numeroViagemRevisadas, Integer idUsuario) {
		super();
		this.numeroViagemRevisadas = numeroViagemRevisadas;
		this.idUsuario = idUsuario;
	}

	public Administrador(Integer id, Integer numeroViagemRevisadas, Integer idUsuario) {
		super();
		this.id = id;
		this.numeroViagemRevisadas = numeroViagemRevisadas;
		this.idUsuario = idUsuario;
	}

	public Administrador(String nome, String email, String password, String telefone, String imagem,
			Integer tipoUsuario, Integer idEndereco, Integer numeroViagemRevisadas, Integer idUsuario) {
		super(nome, email, password, telefone, imagem, tipoUsuario, idEndereco);
		this.numeroViagemRevisadas = numeroViagemRevisadas;
		this.idUsuario = idUsuario;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroViagemRevisadas() {
		return numeroViagemRevisadas;
	}

	public void setNumeroViagemRevisadas(Integer numeroViagemRevisadas) {
		this.numeroViagemRevisadas = numeroViagemRevisadas;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public PacoteViagem getPv() {
		return pv;
	}

	public void setPv(PacoteViagem pv) {
		this.pv = pv;
	}

	public Passagem getPassagem() {
		return passagem;
	}

	public void setPassagem(Passagem passagem) {
		this.passagem = passagem;
	}

	public Hospedagem getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(Hospedagem hospedagem) {
		this.hospedagem = hospedagem;
	}

	public ArrayList<Revisao> getRevisoes() {
		return revisoes;
	}

	public void setRevisoes(ArrayList<Revisao> revisoes) {
		this.revisoes = revisoes;
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
		Administrador other = (Administrador) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "é um Administrador com ->id=" + id + ", numeroViagemRevisadas=" + numeroViagemRevisadas + ", idUsuario="
				+ idUsuario + ", Tambem," + super.toString() + " ";
	}

	@Override
	public void create() {
		this.id = this.administradorDAO.save(new Administrador(0, this.idUsuario));
	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.administradorDAO.deleteById(this.id);
		} else {
			System.out.println("Esse administrador \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		ArrayList<Administrador> administradores = this.administradorDAO.findAll();
		for (Administrador a : administradores) {
			System.out.println(a.toString());
		}
	}

	@Override
	public void update() {
		if (this.id != null) {
			this.administradorDAO.update(new Administrador(this.numeroViagemRevisadas, this.idUsuario));
		}
	}
	
	public void montarPacotes(Scanner sc){
		int idPassagem,idHospedagem,valorDesconto;
		double valorTotal;
		String nome,possuiHospedagem,meioTransporte,imagem;
		
		this.passagem.readAll();
		System.out.println("Ecolha o id de uma passagem para montar seu pacote viagem: ");
		idPassagem = sc.nextInt();
		
		this.hospedagem.readAll();
		System.out.println("Ecolha o id de uma hospedagm para montar seu pacote viagem: ");
		idHospedagem = sc.nextInt();
		
		System.out.println("Digite o nome do pacote: ");
		nome = sc.nextLine();
		pv.setTitulo(nome);
		
		System.out.println("Digite o valor de desconto a ser aplicado encima do seu pacote de 0 a 40 %: ");
		valorDesconto = sc.nextInt();
		pv.setValorDesconto(valorDesconto);
		
		System.out.println("Digite o valor total bruto equivalente ao pacote: ");
		valorTotal= sc.nextDouble();
		pv.setPrecoTotal(valorTotal);
		
		System.out.println("Seu  pacote possui Hospedagem: ");
		possuiHospedagem = sc.nextLine();
		pv.setPossuiHospedagem(possuiHospedagem);
		sc.nextLine();
		
		pv.setStatus("criado");
		
		System.out.println("Meio de Transporte: ");
		meioTransporte = sc.nextLine();
		pv.setMeioTransporte(meioTransporte);
		sc.nextLine();
		
		System.out.println("O caminho de uma imagem para associar ao pacote: ");
		imagem = sc.nextLine();
		pv.setImagem(imagem);
		sc.nextLine();
		
		pv.setPrazoCancelamento(new Date());
		pv.setDataViagem(new Date());
		
		pv.setIdOrigemDestino(1);
		
		pv.setIdHospedagem(idPassagem);
		
		this.pv.create();
	}
	
	public void buscarPorId() {
		Administrador adm = this.administradorDAO.findById(this.id);
		this.id = adm.getId();
		this.setNome(adm.getNome());
		this.setEmail(adm.getEmail());
		this.setPassword(adm.getPassword());
		this.setTelefone(adm.getTelefone());
		this.setImagem(adm.getImagem());
		this.setDataLogin(adm.getDataLogin());
		this.setTipoUsuario(adm.getTipoUsuario());
		this.setIdEndereco(adm.getIdEndereco());
		
		this.numeroViagemRevisadas = adm.getNumeroViagemRevisadas();
		this.idUsuario = adm.getIdUsuario();
		super.setEndereco(adm.getEndereco());
	}
}


