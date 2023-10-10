package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import DAO.FornecedorDAO;

public class Fornecedor extends Usuario {
	private Integer id;
	private String CNPJ;
	private Integer tipoServico;
	private Integer idUsuario;
	
	private ArrayList<Passagem> passagens = new ArrayList<Passagem>();
	private FornecedorDAO fornecedorDAO = new FornecedorDAO();

	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer id, String CNPJ, Integer tipoServico, Integer idUsuario) {
		super();
		this.id = id;
		this.CNPJ = CNPJ;
		this.tipoServico = tipoServico;
		this.idUsuario = idUsuario;
	}
	
	public Fornecedor(Integer id, String nome, String email, String password, String telefone, String imagem, Date dataLogin,
			Integer tipoUsuario, Integer idEndereco, String CNPJ, Integer tipoServico,Integer idUsuario) {
		super(idUsuario,nome,email,password,telefone,imagem,dataLogin,tipoUsuario,idEndereco);
		
		this.id = id;
		this.CNPJ = CNPJ;
		this.tipoServico = tipoServico;
		this.idUsuario = idUsuario;
	}

	public Fornecedor(String cNPJ, Integer tipoServico, Integer idUsuario) {
		super();
		CNPJ = cNPJ;
		this.tipoServico = tipoServico;
		this.idUsuario = idUsuario;
	}

	public Fornecedor(String cNPJ, Integer tipoServico) {
		super();
		CNPJ = cNPJ;
		this.tipoServico = tipoServico;
	}
	
	public void fornecer(Passagem ps) {
		ps.setIdFornecedor(this.id);
		ps.create();
	}
	public void fornecer(Hospedagem h) {
		h.create();
	}

	public ArrayList<Passagem> getPassagens() {
		return passagens;
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
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public Integer getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(Integer tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "é um Fornecedor com -> CNPJ=" + CNPJ + ", tipoServico=" + tipoServico + ", idUsuario=" + idUsuario
				+ ", Tambem," + super.toString() + " ";
	}

	public static Fornecedor preencherFornecedor(Scanner sc) {
		String CNPJ;
		Integer tipoServico;
		System.out.print("Digite seu o seu CNPJ: ");
		CNPJ = sc.next();
		System.out.print(
				"Digite seu tipo de Serviço que você oferece 1- para Fornecimento dePassagens  2- Para Hospedagem : ");
		tipoServico = sc.nextInt();

		return new Fornecedor(CNPJ, tipoServico);
	}

	@Override
	public void create() {
		this.id = this.fornecedorDAO.save(new Fornecedor(this.CNPJ, this.tipoServico, this.idUsuario));
	}

	@Override
	public void update() {
		this.fornecedorDAO.update(new Fornecedor(this.id, this.CNPJ, this.tipoServico, this.idUsuario));

	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.fornecedorDAO.deleteById(this.id);
		} else {
			System.out.println("Esse fornecedor \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		ArrayList<Fornecedor> fornecedores = this.fornecedorDAO.findAll();
		for (Fornecedor f : fornecedores) {
			System.out.println(f.toString());
		}
	}
}
