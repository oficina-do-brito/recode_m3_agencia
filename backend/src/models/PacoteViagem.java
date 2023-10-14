package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import DAO.PacoteViagemDAO;
import models.utils.ICRUD;

public class PacoteViagem implements ICRUD {
	private Integer id;
	private String titulo;
	private Integer valorDesconto;
	private double precoTotal;
	private String possuiHospedagem;
	private String status;
	private String meioTransporte;
	private String imagem;
	private Date prazoCancelamento;
	private Date dataViagem;
	private Integer idOrigemDestino;
	private Integer idHospedagem;
	private Integer idCarrinhoCompra;

	private ArrayList<Passagem> passagens = new ArrayList<Passagem>();
	private Hospedagem hospedadgem = new Hospedagem();
	private OrigemDestino origem = new OrigemDestino();
	private OrigemDestino destino = new OrigemDestino();
	private PacoteViagemDAO pacoteViagemDAO = new PacoteViagemDAO();

	public PacoteViagem(String titulo, Integer valorDesconto, double precoTotal, String possuiHospedagem, String status,
			String meioTransporte, String imagem, Date prazoCancelamento, Date dataViagem, Integer idOrigemDestino,
			Integer idHospedagem, Integer idCarrinhoCompra) {
		super();
		this.titulo = titulo;
		this.valorDesconto = valorDesconto;
		this.precoTotal = precoTotal;
		this.possuiHospedagem = possuiHospedagem;
		this.status = status;
		this.meioTransporte = meioTransporte;
		this.imagem = imagem;
		this.prazoCancelamento = prazoCancelamento;
		this.dataViagem = dataViagem;
		this.idOrigemDestino = idOrigemDestino;
		this.idHospedagem = idHospedagem;
		this.idCarrinhoCompra = idCarrinhoCompra;
	}

	public PacoteViagem(Integer id, String titulo, Integer valorDesconto, double precoTotal, String possuiHospedagem,
			String status, String meioTransporte, String imagem, Date prazoCancelamento, Date dataViagem,
			Integer idOrigemDestino, Integer idHospedagem, Integer idCarrinhoCompra) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.valorDesconto = valorDesconto;
		this.precoTotal = precoTotal;
		this.possuiHospedagem = possuiHospedagem;
		this.status = status;
		this.meioTransporte = meioTransporte;
		this.imagem = imagem;
		this.prazoCancelamento = prazoCancelamento;
		this.dataViagem = dataViagem;
		this.idOrigemDestino = idOrigemDestino;
		this.idHospedagem = idHospedagem;
		this.idCarrinhoCompra = idCarrinhoCompra;
	}

	public PacoteViagem() {
		super();
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

	public Integer getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Integer valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getPossuiHospedagem() {
		return possuiHospedagem;
	}

	public void setPossuiHospedagem(String possuiHospedagem) {
		this.possuiHospedagem = possuiHospedagem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Date getPrazoCancelamento() {
		return prazoCancelamento;
	}

	public void setPrazoCancelamento(Date prazoCancelamento) {
		this.prazoCancelamento = prazoCancelamento;
	}

	public Date getDataViagem() {
		return dataViagem;
	}

	public void setDataViagem(Date dataViagem) {
		this.dataViagem = dataViagem;
	}

	public Integer getIdOrigemDestino() {
		return idOrigemDestino;
	}

	public void setIdOrigemDestino(Integer idOrigemDestino) {
		this.idOrigemDestino = idOrigemDestino;
	}

	public Integer getIdHospedagem() {
		return idHospedagem;
	}

	public void setIdHospedagem(Integer idHospedagem) {
		this.idHospedagem = idHospedagem;
	}

	public Integer getIdCarrinhoCompra() {
		return idCarrinhoCompra;
	}

	public void setIdCarrinhoCompra(Integer idCarrinhoCompra) {
		this.idCarrinhoCompra = idCarrinhoCompra;
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
		PacoteViagem other = (PacoteViagem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PacoteViagem [titulo=" + titulo + ", valorDesconto=" + valorDesconto + ", precoTotal=" + precoTotal
				+ ", possuiHospedagem=" + possuiHospedagem + ", status=" + status + ", meioTransporte=" + meioTransporte
				+ ", imagem=" + imagem + ", prazoCancelamento=" + prazoCancelamento + ", dataViagem=" + dataViagem
				+ ", idOrigemDestino=" + idOrigemDestino + ", idHospedagem=" + idHospedagem + ", idCarrinhoCompra="
				+ idCarrinhoCompra + "]";
	}

	public void recuperarPacotevIagemById(int id) {
		PacoteViagem pr = this.pacoteViagemDAO.findById(id);
		this.id = pr.id;
		this.titulo = pr.titulo;
		this.valorDesconto = pr.valorDesconto;
		this.precoTotal = pr.precoTotal;
		this.possuiHospedagem = pr.possuiHospedagem;
		this.status = pr.status;
		this.meioTransporte = pr.meioTransporte;
		this.imagem = pr.imagem;
		this.prazoCancelamento = pr.prazoCancelamento;
		this.dataViagem = pr.dataViagem;
		this.idOrigemDestino = pr.idOrigemDestino;
		this.idHospedagem = pr.idHospedagem;
		this.idCarrinhoCompra = pr.idCarrinhoCompra;
	}

	@Override
	public void create() {
		this.id = this.pacoteViagemDAO.save(new PacoteViagem(this.titulo, this.valorDesconto, this.precoTotal,
				this.possuiHospedagem, this.status, this.meioTransporte, this.imagem, this.prazoCancelamento,
				this.dataViagem, this.idOrigemDestino, this.idHospedagem, this.idCarrinhoCompra));
	}

	@Override
	public void delete() {
		if (this.id != null) {
			this.pacoteViagemDAO.deleteById(this.id);
		} else {
			System.out.println("Esse pacote de viagem \"Não foi encontrado\", logo não houve deleção.");
		}
	}

	@Override
	public void readAll() {
		ArrayList<PacoteViagem> pacotes = this.pacoteViagemDAO.findAll();
		for (PacoteViagem p : pacotes) {
			System.out.println(p.toString());
		}
	}

	@Override
	public void update() {
		if (this.id != null) {
			this.pacoteViagemDAO.update(new PacoteViagem(this.titulo, this.valorDesconto, this.precoTotal,
					this.possuiHospedagem, this.status, this.meioTransporte, this.imagem, this.prazoCancelamento,
					this.dataViagem, this.idOrigemDestino, this.idHospedagem, this.idCarrinhoCompra));
		}
	}

}
