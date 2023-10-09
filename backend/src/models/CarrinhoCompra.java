package models;

import java.util.ArrayList;
import java.util.Objects;

import models.utils.ICRUD;

public class CarrinhoCompra implements ICRUD{
	private Integer id;
	private double valorTotal;
	private Integer formaPagamento;
	private Integer quantItems;
	private Integer idCliente;

	private ArrayList<PacoteViagem> pacotes = new  ArrayList<PacoteViagem>();
	
	public CarrinhoCompra(Integer id, double valorTotal, Integer formaPagamento, Integer quantItems,
			Integer idCliente) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.formaPagamento = formaPagamento;
		this.quantItems = quantItems;
		this.idCliente = idCliente;
	}

	public CarrinhoCompra(double valorTotal, Integer formaPagamento, Integer quantItems, Integer idCliente) {
		super();
		this.valorTotal = valorTotal;
		this.formaPagamento = formaPagamento;
		this.quantItems = quantItems;
		this.idCliente = idCliente;
	}

	public CarrinhoCompra() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Integer formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getQuantItems() {
		return quantItems;
	}

	public void setQuantItems(Integer quantItems) {
		this.quantItems = quantItems;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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
		CarrinhoCompra other = (CarrinhoCompra) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "CarrinhoCompra [id=" + id + ", valorTotal=" + valorTotal + ", formaPagamento=" + formaPagamento
				+ ", quantItems=" + quantItems + ", idCliente=" + idCliente + "]";
	}

    public ArrayList<PacoteViagem> getPacotes() {
        return pacotes;
    }

	public void addInPacotes(PacoteViagem pacote) {
        this.pacotes.add(pacote);
    }
	public int realizarPagamento(double valorPagamento,int ...ids){
		for(int x:ids){
			System.out.printf("implementar logica de pegarcada todos ospacotes com os ids",x);
			System.out.println("somar");
			System.out.println("depois retirar do carrinho");
		}
		return 1;
	}

	@Override
	public void create() {
		
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
