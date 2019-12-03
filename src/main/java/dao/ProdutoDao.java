package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Categoria;
import classes.Produto;
import jdbc.ConnectionFactory;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Produto produto) throws SQLException {
		String sql = "INSERT INTO produtos" + " (nome,valor,qtd_estoque,id_categoria)" + " values (?,?,?,?)";

		PreparedStatement stmt;

		stmt = this.connection.prepareStatement(sql);
		stmt.setString(1, produto.getNome());
		stmt.setDouble(2, produto.getValor());
		stmt.setInt(3, produto.getQtdEstoque());
		stmt.setInt(4,produto.getCategoria().getIdCategoria());
		stmt.execute();
		stmt.close();
	}

	public void altera(Produto produto) {
		String sql = "UPDATE produtos SET " + " (nome,valor,qtd_estoque,id_categoria) = (?,?,?,?)" + " WHERE id = ? ";

		try {
			PreparedStatement stmt = null;
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getValor());
			stmt.setInt(3, produto.getQtdEstoque());
			stmt.setLong(4, produto.getCategoria().getIdCategoria());
			stmt.setLong(5, produto.getIdProduto());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void remove(Produto produto) {
		String sql = "DELETE FROM produtos " + " WHERE id = ? ";

		try {
			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, produto.getIdProduto());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public List<Produto> getLista() {
		List<Produto> produtos = new ArrayList<Produto>();

		try {
			String sql = "SELECT id, nome, valor, qtd_estoque, id_categoria" + " FROM produtos " + " ORDER BY id";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				int id = resultado.getInt("id");
				String nome = resultado.getString("nome");
				double valor = resultado.getDouble("valor");
				int qtdEstoque = resultado.getInt("qtd_estoque");
				
				Categoria c = new Categoria();
				c.setIdCategoria(resultado.getInt("id_categoria"));
				
				Produto produto = new Produto();
				produto.setIdProduto(id);
				produto.setNome(nome);
				produto.setValor(valor);
				produto.setQtdEstoque(qtdEstoque);
				produto.setCategoria(c);
				
				produtos.add(produto);
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return produtos;
	}

	public Produto getById(Integer id) {
		Produto produto = new Produto();

		String sql = "SELECT id, nome, valor, qtd_estoque, id_categoria" + " FROM produtos " + " WHERE id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				produto.setIdProduto(resultado.getInt("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setValor(resultado.getDouble("valor"));
				produto.setQtdEstoque(resultado.getInt("qtd_estoque"));
				Categoria c = new Categoria();
				c.setIdCategoria(resultado.getInt("id_categoria"));
				produto.setCategoria(c);
				
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return produto;
	}
}