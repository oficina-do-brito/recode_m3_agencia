package models;

import java.util.Objects;

import DAO.EnderecoDAO;
import models.utils.ICRUD;

public class Endereco implements ICRUD{
	private Integer id;
	private String CEP;
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private Integer numero;

	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	public Endereco() {
		super();
	}
	public Endereco(String cEP, String estado, String cidade, String bairro, String rua, Integer numero) {
		super();
		this.CEP = cEP;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	public Endereco(Integer id,String CEP, String estado, String cidade, String bairro, String rua, Integer numero) {
		super();
		this.id = id;
		this.CEP = CEP;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		this.CEP = cEP;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", CEP=" + CEP + ", estado=" + estado + ", cidade=" + cidade + ", bairro="
				+ bairro + ", rua=" + rua + ", numero=" + numero + "]";
	}
	@Override
	public void create() {
		this.id = this.enderecoDAO.save(new Endereco(this.CEP,this.estado,this.cidade,this.bairro,this.rua,this.numero));
	}
	@Override
	public void update() {
		
	}
	@Override
	public void delete() {
		
	}
	@Override
	public void readAll() {
		
	}
	
}
