package model.dominio;

public class Produto {
	private int idProduto;
	private String codigoProduto;
	private String tipo;
	private String modelo;
	private String tamanho;
	private int quantidade;
	private float precoPago;
	private float precoVenda;
	private int idFornecedor;
	private String observacao;
	

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public float getPrecoPago() {
		return precoPago;
	}
	
	public void setPrecoPago(float string) {
		this.precoPago = string;
	}
	
	public float getPrecoVenda() {
		return precoVenda;
	}
	
	public void setPrecoVenda(float d) {
		this.precoVenda = d;
	}
	
	

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return codigoProduto + ", " + tipo + ", " + modelo + ", " + tamanho + ", " + quantidade + ", " + precoVenda;
	}

	public String returnItem() {
		return codigoProduto + ", " + tipo + ", " + modelo + ", " + tamanho;
	}

	
	
}
