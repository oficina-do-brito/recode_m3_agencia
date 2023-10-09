package models;

import java.util.ArrayList;
import java.util.Objects;

import DAO.AdministradorDAO;

public class Administrador extends Usuario{
	private Integer id;
	private Integer numeroViagemRevisadas;
	private Integer idUsuario;

	private AdministradorDAO administradorDAO = new  AdministradorDAO();

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
		for(Administrador a : administradores){
			System.out.println(a.toString());
		}
	}

	@Override
	public void update() {
		this.administradorDAO.update(new Administrador(this.numeroViagemRevisadas,this.idUsuario));
	}
	
}
