package classes;

public class Produto {
	private int idProduto;
	private String nome;
	private double valor;
	private int qtdEstoque;
	private Categoria categoria;
	

	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria Categoria) {
		this.categoria = Categoria;
	}
}
