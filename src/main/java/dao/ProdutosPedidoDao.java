package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Pedido;
import classes.Produto;
import classes.ProdutosPedido;
import jdbc.ConnectionFactory;

public class ProdutosPedidoDao {

	private Connection connection;

	public ProdutosPedidoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(ProdutosPedido produtosPedido) throws SQLException {
		String sql = "INSERT INTO produtos_pedido"
				+ " (id_produto,id_pedido,qtd_produto,valor_unidade,valor_total,obeservacao)" + " values (?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = this.connection.prepareStatement(sql);
		stmt.setInt(1, produtosPedido.getProduto().getIdProduto());
		stmt.setInt(2, produtosPedido.getPedido().getIdPedido());
		stmt.setInt(3, produtosPedido.getQuantidade());
		stmt.setDouble(4, produtosPedido.getValorUni());
		stmt.setDouble(5, produtosPedido.getValorTotal());
		stmt.setString(6, produtosPedido.getObeservacao());
		stmt.execute();
		stmt.close();
	}

	public void altera(ProdutosPedido produtosPedido) {
		String sql = "UPDATE produtos_pedido SET "
				+ " (id_produto,id_pedido,qtd_produto,valor_unidade,valor_total,obeservacao) = (?,?,?,?,?,?)"
				+ " WHERE id = ? ";

		try {
			PreparedStatement stmt = null;
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, produtosPedido.getProduto().getIdProduto());
			stmt.setInt(2, produtosPedido.getPedido().getIdPedido());
			stmt.setInt(3, produtosPedido.getQuantidade());
			stmt.setDouble(4, produtosPedido.getValorUni());
			stmt.setDouble(5, produtosPedido.getValorTotal());
			stmt.setString(6, produtosPedido.getObeservacao());
			stmt.setInt(7, produtosPedido.getIdProdutosPedido());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void remove(ProdutosPedido produtosPedido) {
		String sql = "DELETE FROM produtos_pedido " + " WHERE id = ? ";

		try {
			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, produtosPedido.getIdProdutosPedido());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public List<ProdutosPedido> getLista() {
		List<ProdutosPedido> produtosPedidos = new ArrayList<ProdutosPedido>();

		try {
			String sql = "SELECT id, id_produto, id_pedido, qtd_produto, valor_unidade, obeservacao"
					+ " FROM produtos_pedido " + " ORDER BY id";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				int id = resultado.getInt("id");
				Produto produto = new Produto();
				produto.setIdProduto(resultado.getInt("id_produto"));
				Pedido pedido = new Pedido();
				pedido.setIdPedido(resultado.getInt("id_pedido"));
				int quantidade = resultado.getInt("qtd_produto");
				double valorUnidade = resultado.getDouble("valor_unidade");
				String obeservacao = resultado.getString("obeservacao");

				ProdutosPedido produtosPedido = new ProdutosPedido();
				produtosPedido.setIdProdutosPedido(id);
				produtosPedido.setProduto(produto);
				produtosPedido.setPedido(pedido);
				produtosPedido.setQuantidade(quantidade);
				produtosPedido.setValorUni(valorUnidade);
				produtosPedido.setValorTotal();
				produtosPedido.setObeservacao(obeservacao);

				produtosPedidos.add(produtosPedido);
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return produtosPedidos;
	}

	public ProdutosPedido getById(Integer id) {
		ProdutosPedido produtosPedido = new ProdutosPedido();

		String sql = "SELECT id, id_produto, id_pedido, qtd_produto, valor_unidade, valor_total, obeservacao" + " FROM produtos_pedido " + " WHERE id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				produtosPedido.setIdProdutosPedido(resultado.getInt("id"));
				Produto produto = new Produto();
				produto.setIdProduto(resultado.getInt("id_produto"));
				Pedido pedido = new Pedido();
				pedido.setIdPedido(resultado.getInt("id_pedido"));
				produtosPedido.setProduto(produto);
				produtosPedido.setPedido(pedido);
				produtosPedido.setQuantidade(resultado.getInt("qtd_produto"));
				produtosPedido.setValorUni(resultado.getDouble("valor_unidade"));
				produtosPedido.setValorTotal();
				produtosPedido.setObeservacao(resultado.getString("obeservacao"));

			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return produtosPedido;
	}
}