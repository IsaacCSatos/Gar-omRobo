package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Mesa;
import jdbc.ConnectionFactory;

public class MesaDao {
	private Connection connection;

	public MesaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Mesa mesa) throws SQLException {
		String sql = "INSERT INTO mesas" + " (numero,disponibilidade)" + " values (?,?)";

		PreparedStatement stmt;

		stmt = this.connection.prepareStatement(sql);
		stmt.setInt(1, mesa.getNumero());
		stmt.setBoolean(2, mesa.getDisponibilidade());
		stmt.execute();
		stmt.close();
	}

	public void remove(Mesa mesa) {
		String sql = "DELETE FROM mesas " + " WHERE id = ? ";

		try {
			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, mesa.getIdMesa());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void altera(Mesa mesa) {
		String sql = "UPDATE Mesas SET " + " (numero,disponibilidade) = (?,?) " + " WHERE id = ? ";

		try {
			PreparedStatement stmt = null;
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, mesa.getNumero());
			stmt.setBoolean(2, mesa.getDisponibilidade());
			stmt.setInt(3, mesa.getIdMesa());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void excluir(Mesa mesa) {
		String sql = "UPDATE Mesas SET " + " (excluida) = (?) " + " WHERE id = ? ";

		try {
			PreparedStatement stmt = null;
			stmt = this.connection.prepareStatement(sql);
			stmt.setBoolean(1, mesa.getExcluida());
			stmt.setInt(2, mesa.getIdMesa());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public List<Mesa> getLista() {
		List<Mesa> mesas = new ArrayList<Mesa>();

		try {
			String sql = "SELECT id, numero, disponibilidade, excluida" + " FROM mesas " + " ORDER BY id";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				int idMesa = resultado.getInt("id");
				int numero = resultado.getInt("numero");
				boolean disponibilidade = resultado.getBoolean("disponibilidade");
				boolean excluida = resultado.getBoolean("excluida");

				Mesa mesa = new Mesa();
				mesa.setIdMesa(idMesa);
				mesa.setNumero(numero);
				mesa.setDisponibilidade(disponibilidade);
				mesa.setExcluida(excluida);
				;

				mesas.add(mesa);
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return mesas;
	}

	public Mesa getById(Integer id) {
		Mesa mesa = new Mesa();

		String sql = "SELECT id, numero, disponibilidade " + " FROM mesas " + " WHERE id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				mesa.setIdMesa(resultado.getInt("id"));
				mesa.setNumero(resultado.getInt("numero"));
				mesa.setDisponibilidade(resultado.getBoolean("disponibilidade"));
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return mesa;
	}
}
