package br.com.alura.edigi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import br.com.alura.edigi.dao.AutorDao;
import br.com.alura.edigi.modelo.Autor;

public class AutorDao {

	private Connection connection;

	public AutorDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Autor autor) {

		var sql = "INSERT INTO autores (nome, biografia, email) VALUES (?, ?, ?)";

		try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, autor.getNome());
			statement.setString(2, autor.getBiografia());
			statement.setString(3, autor.getEmail());
			statement.execute();

			var resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				autor.setId(resultSet.getLong(1));
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new RuntimeException("Autor já cadastrado");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Autor buscaPorEmail(String email) {

		var sql = "SELECT * FROM autores WHERE email = ?";

		try (var statement = connection.prepareStatement(sql)) {

			statement.setString(1, email);

			var resultSet = statement.executeQuery();

			if (resultSet.next()) {
				var autor = new Autor(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("biografia"), resultSet.getString("email"),
						resultSet.getTimestamp("data_cadastro").toLocalDateTime());
				return autor;
			}

			return null;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
