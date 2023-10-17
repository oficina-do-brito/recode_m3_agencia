package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import DAO.HospedagemDAO;
import models.utils.ICRUD;

public class Hospedagem implements ICRUD {
	private Integer id;
	private String nome;
	private String imagem;
	private Integer diaria;
	private double preco;
	private Integer idOrigemDestino;
	private Integer idFornecedor;
	
	private HospedagemDAO hospedagemDAO =  new HospedagemDAO();

	public Hospedagem(Integer id, String nome, String imagem, Integer diaria, double preco, Integer idOrigemDestino,
			Integer idFornecedor) {
		this.id = id;
		this.nome = nome;
		this.imagem = imagem;
		this.diaria = diaria;
		this.preco = preco;
		this.idOrigemDestino = idOrigemDestino;
		this.idFornecedor = idFornecedor;
	}

	public Hospedagem(String nome, String imagem, Integer diaria, double preco, Integer idOrigemDestino,
			Integer idFornecedor) {
		super();
		this.nome = nome;
		this.imagem = imagem;
		this.diaria = diaria;
		this.preco = preco;
		this.idOrigemDestino = idOrigemDestino;
		this.idFornecedor = idFornecedor;
	}

	public Hospedagem() {
		super();
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getDiaria() {
		return diaria;
	}

	public void setDiaria(Integer diaria) {
		this.diaria = diaria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getIdOrigemDestino() {
		return idOrigemDestino;
	}

	public void setIdOrigemDestino(Integer idOrigemDestino) {
		this.idOrigemDestino = idOrigemDestino;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
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
		Hospedagem other = (Hospedagem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Hospedagem [nome=" + nome + ", imagem=" + imagem + ", diaria=" + diaria + ", preco=" + preco
				+ ", idOrigemDestino=" + idOrigemDestino + ", idFornecedor=" + idFornecedor + "]";
	}

	public void create() {
		this.id = this.hospedagemDAO.save(new Hospedagem(this.nome,this.imagem,this.diaria,this.preco,this.idOrigemDestino,this.idFornecedor));
	}

	public void update() {
		if(this.id!=null) {
			this.hospedagemDAO.update(new Hospedagem(this.id,this.nome,this.imagem,this.diaria,this.preco,this.idOrigemDestino,this.idFornecedor));
		}
	}

	public void delete() {
		if (this.id != null) {
			this.hospedagemDAO.deleteById(this.id);
		} else {
			System.out.println("Essa Hospedagem \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		List<Hospedagem>  hlist = new ArrayList<>();
		for (Hospedagem u : hlist) {
			System.out.println(u.toString());
		}

	}
	public static Hospedagem preencherHospedagem(Scanner sc) {
		//Integer id, String nome, String imagem, Integer diaria, double preco, Integer idOrigemDestino,Integer idFornecedor
		String nome,imagem;Integer diaria;double preco;
		System.out.print("Informe um nome para a Hospedagem: ");
		sc.nextLine();
		nome = sc.nextLine();
		System.out.print("Informe a url de uma imagem da Hospedagem: ");
		sc.nextLine();
		imagem = sc.nextLine();
		System.out.print("Informe o numero de dias disponiveis correspondente ao periodo de Hospedagem: ");
		diaria = sc.nextInt();
		System.out.print("Informe o valor da Hospedagem: ");
		preco = sc.nextDouble();
		
		return new Hospedagem(nome,imagem,diaria,preco,0,0);
	}

}
