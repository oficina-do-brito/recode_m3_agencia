package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import DAO.PassagemDAO;
import models.utils.ICRUD;
import models.utils.Verificadora;

public class Passagem implements ICRUD{
	private Integer id;
	private String titulo;
	private double preco;
	private Integer tipo;
	private Integer idFornecedor;
	private Integer idPacoteViagem;
	
	private PassagemDAO passagemDAO = new PassagemDAO();

	public Passagem(Integer id, String titulo, double preco, Integer tipo, Integer idFornecedor,
			Integer idPacoteViagem) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.tipo = tipo;
		this.idFornecedor = idFornecedor;
		this.idPacoteViagem = idPacoteViagem;
	}

	public Passagem(String titulo, double preco, Integer tipo, Integer idFornecedor, Integer idPacoteViagem) {
		super();
		this.titulo = titulo;
		this.preco = preco;
		this.tipo = tipo;
		this.idFornecedor = idFornecedor;
		this.idPacoteViagem = idPacoteViagem;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdPacoteViagem() {
		return idPacoteViagem;
	}

	public void setIdPacoteViagem(Integer idPacoteViagem) {
		this.idPacoteViagem = idPacoteViagem;
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
		Passagem other = (Passagem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Passagem [titulo=" + titulo + ", preco=" + preco + ", tipo=" + tipo + ", idFornecedor=" + idFornecedor
				+ ", idPacoteViagem=" + idPacoteViagem + "]";
	}

	@Override
	public void create() {
		this.id = this.passagemDAO.save(new Passagem(this.titulo, this.preco, this.tipo, this.idFornecedor, this.idPacoteViagem));
	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.passagemDAO.deleteById(this.id);
		} else {
			System.out.println("Essa passagem \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		List<Passagem> passagens = new ArrayList<>();
		for (Passagem u : passagens) {
			System.out.println(u.toString());
		}
	}

	@Override
	public void update() {
		if(this.id!=null) {
			this.passagemDAO.update(new Passagem(this.id, this.titulo, this.preco, this.tipo, this.idFornecedor,this.idPacoteViagem));
		}
		
	}
	public static Passagem preencherPassagem(Scanner sc) {
		String titulo;
		double preco;
		Integer tipo;
		do{
			System.out.println("Informe um nome para esta passagem: ");
			titulo = sc.nextLine();
			sc.nextLine();
		}while(titulo.length()>150);
		

		System.out.println("Informe o preco da passagem: ");
		preco = sc.nextDouble();

		do{
			System.out.println("Informe 1 - para do tipo  aerea, 2 -  para do tipo  onibus");
			tipo = sc.nextInt();
		}while(!Verificadora.verificaTipoServicoExists(tipo));
		return new Passagem(titulo, preco, tipo, null, null);
	}
}
