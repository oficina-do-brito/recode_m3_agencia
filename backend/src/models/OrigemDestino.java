package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import DAO.OrigemDestinoDAO;
import models.utils.ICRUD;

public class OrigemDestino implements ICRUD {
	private Integer id;
	private String nome;
	private String imagem;
	private String descricao;
	private Integer tipo;
	private Integer idEndereco;

	private Endereco endereco = new Endereco();
	private OrigemDestinoDAO origemDestinoDAO = new OrigemDestinoDAO();

	public OrigemDestino(Integer id, String nome, String imagem, String descricao, Integer tipo, Integer idEndereco) {
		this.id = id;
		this.nome = nome;
		this.imagem = imagem;
		this.descricao = descricao;
		this.tipo = tipo;
		this.idEndereco = idEndereco;
	}

	public OrigemDestino(String nome, String imagem, String descricao, Integer tipo, Integer idEndereco) {
		super();
		this.nome = nome;
		this.imagem = imagem;
		this.descricao = descricao;
		this.tipo = tipo;
		this.idEndereco = idEndereco;
	}

	public OrigemDestino() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
		OrigemDestino other = (OrigemDestino) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrigemDestino [nome=" + nome + ", imagem=" + imagem + ", descricao=" + descricao + ", tipo=" + tipo
				+ ", idEndereco=" + idEndereco + "]";
	}

	@Override
	public void create() {
		this.id = this.origemDestinoDAO.save(new OrigemDestino(this.nome,this.imagem,this.descricao,this.tipo,this.idEndereco));
	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.origemDestinoDAO.deleteById(this.id);
		} else {
			System.out.println("Esse Destino ou Origem \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		List<OrigemDestino>  origemDestino = new ArrayList<>();
		for (OrigemDestino u : origemDestino) {
			System.out.println(u.toString());
		}
	}

	@Override
	public void update() {
		if(this.id!=null) {
			this.origemDestinoDAO.update(new OrigemDestino(this.id,this.nome,this.imagem,this.descricao,this.tipo,this.idEndereco));
		}
	}
	
	public static OrigemDestino preencherOrigemDestino(Scanner sc) {
		String nome,imagem, descricao;Integer tipo;
		
		System.out.print("Informe um nome para a Origem ou Destino: ");
		nome = sc.next();
		System.out.print("Informe a url de uma imagem da Origem ou Destino: ");
		imagem = sc.next();
		System.out.print("Informe uma descricao para essa Origem ou Destino: ");
		descricao = sc.next();
		System.out.print("Informe o tipo 1 - origem 2 - Destino: ");
		tipo = sc.nextInt();
		
		return new OrigemDestino(nome,imagem,descricao,tipo,0);
	}

}
