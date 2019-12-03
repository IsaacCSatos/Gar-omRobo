package classes;

public class ProdutosPedido {
	private int idProdutosPedido;
	private Pedido pedido;
	private Produto produto;
	private int quantidade;
	private double valorUni;
	private double valorTotal;
	private String obeservacao;

	public ProdutosPedido() {
	}

	public int getIdProdutosPedido() {
		return idProdutosPedido;
	}

	public void setIdProdutosPedido(int idProdutosPedido) {
		this.idProdutosPedido = idProdutosPedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorUni() {
		return valorUni;
	}

	public void setValorUni(double valorUni) {
		this.valorUni = valorUni;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal() {
		this.valorTotal = valorUni * quantidade;
	}

	public String getObeservacao() {
		return obeservacao;
	}

	public void setObeservacao(String obeservacao) {
		this.obeservacao = obeservacao;
	}

}
