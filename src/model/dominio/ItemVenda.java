package model.dominio;

public class ItemVenda {
	private int idItemVenda;
	private Venda Venda;
	private Produto Produto;
	private float valorUnitario;
	private int quantidadeComprada;
	private float valorTotal = 0;
	
	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}
	public void setValorUnitario(float valorUnitario) {
       this.valorUnitario = valorUnitario;
    }
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}	
	public void setVenda(Venda venda) {
		Venda = venda;
	}
	public void setProduto(Produto produto) {
		Produto = produto;
		setValorUnitario(this.Produto.getPrecoVenda());
	}
	
	
	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}
	public float getValorUnitario() {
		return valorUnitario;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public Venda getVenda() {
		return Venda;
	}
	public Produto getProduto() {
		return Produto;
	}
	
	
	

	@Override
	public String toString() {
		return Produto.returnItem() + ", " + valorUnitario + ", " + quantidadeComprada + ", " + valorTotal;
	}
	public int getIdItemVenda() {
		return idItemVenda;
	}
	public void setIdItemVenda(int idItemVenda) {
		this.idItemVenda = idItemVenda;
	}
	
	
}
