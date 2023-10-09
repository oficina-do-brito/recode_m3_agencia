package models;

import java.util.Objects;
import java.util.Scanner;

import models.utils.ICRUD;

public class Hospedagem implements ICRUD {
	private Integer id;
	private String nome;
	private String imagem;
	private Integer diaria;
	private double preco;
	private Integer idOrigemDestino;
	private Integer idFornecedor;

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
	}

	public void reader() {
	}

	public void update() {

	}

	public void delete() {
	}

	@Override
	public void readAll() {

	}
	public static Hospedagem preencherHospedagem(Scanner sc) {
		//Integer id, String nome, String imagem, Integer diaria, double preco, Integer idOrigemDestino,Integer idFornecedor
		String nome,imagem;Integer diaria;double preco;
		System.out.print("Informe um nome para a Hospedagem: ");
		nome = sc.next();
		System.out.print("Informe a url de uma imagem da Hospedagem: ");
		imagem = sc.next();
		System.out.print("Informe o numero de dias disponiveis correspondente ao periodo de Hospedagem: ");
		diaria = sc.nextInt();
		System.out.print("Informe o valor da Hospedagem: ");
		preco = sc.nextDouble();
		
		return new Hospedagem(nome,imagem,diaria,preco,null,null);
	}

}
