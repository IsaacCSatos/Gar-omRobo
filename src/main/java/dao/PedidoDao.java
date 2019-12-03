package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Mesa;
import classes.Pedido;
import classes.Status;
import jdbc.ConnectionFactory;

public class PedidoDao {

	private Connection connection;

	public PedidoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Pedido pedido) throws SQLException {
		String sql = "INSERT INTO pedidos" + " (valor_total,status,id_mesa)" + " values (?,CAST(? AS status),?)";

		PreparedStatement stmt;

		stmt = this.connection.prepareStatement(sql);

		stmt.setDouble(1, pedido.getValorTotal());
		stmt.setString(2, pedido.getStatus().toString());
		stmt.setInt(3, pedido.getMesa().getIdMesa());
		stmt.execute();
		stmt.close();
	}

	public void altera(Pedido pedido) {
		String sql = "UPDATE pedidos SET " + " (valor_total,status,id_mesa) = (?,CAST(? AS status),?)" + " WHERE id = ? ";

		try {
			PreparedStatement stmt = null;
			stmt = this.connection.prepareStatement(sql);
			stmt.setDouble(1, pedido.getValorTotal());
			stmt.setString(2, pedido.getStatus().toString());
			stmt.setLong(3, pedido.getMesa().getIdMesa());
			stmt.setLong(4, pedido.getIdPedido());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void remove(Pedido pedido) {
		String sql = "DELETE FROM pedidos" + " WHERE id = ? ";

		try {
			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, pedido.getIdPedido());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public List<Pedido> getLista() {
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {
			String sql = "SELECT id, valor_total, status, id_mesa" + " FROM pedidos " + " ORDER BY id";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				int id = resultado.getInt("id");
				double valorTotal = resultado.getDouble("valor_total");
				Status status = Status.valueOf(resultado.getString("status"));

				Mesa mesa = new Mesa();
				mesa.setIdMesa(resultado.getInt("id_mesa"));

				Pedido pedido = new Pedido();
				pedido.setIdPedido(id);
				pedido.setValorTotal(valorTotal);
				pedido.setMesa(mesa);
				pedido.setStatus(status);
				pedidos.add(pedido);
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return pedidos;
	}

	public Pedido getById(Integer id) {
		Pedido pedido = new Pedido();

		String sql = "SELECT id, valor_total, status, id_mesa" + " FROM pedidos " + " WHERE id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				pedido.setIdPedido(resultado.getInt("id"));
				pedido.setValorTotal(resultado.getDouble("valor_total"));
				pedido.setStatus(Status.valueOf(resultado.getString("status")));
				
				Mesa mesa = new Mesa();
				mesa.setIdMesa(resultado.getInt("id_mesa"));
				pedido.setMesa(mesa);
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return pedido;
	}
}