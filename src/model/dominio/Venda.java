package model.dominio;

import java.util.ArrayList;

public class Venda {
	private int idVenda;
	private String dataVenda;
    private String formaPagamento;
    private float valorVenda;
    private int parcelas;
    private float descontoReais;
    private int descontoPorcentagem;
    private float valorFinal;    
    private ArrayList<ItemVenda> itensVenda;

	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public int getParcelas() {
		return parcelas;
	}
	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public float getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}
	public ArrayList<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(ArrayList<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	public float getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(float valorFinal) {
		this.valorFinal = valorFinal;
	}
	public float getDescontoReais() {
		return descontoReais;
	}
	public void setDescontoReais(float descontoReais) {
		this.descontoReais = descontoReais;
	}
	public int getDescontoPorcentagem() {
		return descontoPorcentagem;
	}
	public void setDescontoPorcentagem(int descontoPorcentagem) {
		this.descontoPorcentagem = descontoPorcentagem;
	}
    
}
