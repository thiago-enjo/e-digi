package br.com.alura.edigi.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import br.com.alura.edigi.dao.CategoriaDao;
import br.com.alura.edigi.modelo.Categoria;

public class CategoriaDaoImpl implements CategoriaDao {

	private Connection connection;

	public CategoriaDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean adiciona(Categoria categoria) {

		var sql = "INSERT INTO categorias (nome) VALUES (?)";

		try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, categoria.getNome());
			var foiInserido = !statement.execute();

			var resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				categoria.setId(resultSet.getLong(1));
			}

			return foiInserido;

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new RuntimeException("Categoria já cadastrada");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Categoria buscaPorId(Long id) {

		var sql = "SELECT * FROM categorias WHERE id = ?";

		try (var statement = connection.prepareStatement(sql)) {

			statement.setLong(1, id);

			var resultSet = statement.executeQuery();

			if (resultSet.next()) {
				var categoria = new Categoria(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getTimestamp("data_cadastro").toLocalDateTime());
				return categoria;
			}

			return null;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
