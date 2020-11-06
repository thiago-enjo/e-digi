package br.com.alura.edigi.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.edigi.dao.LivroDao;
import br.com.alura.edigi.modelo.Autor;
import br.com.alura.edigi.modelo.Categoria;
import br.com.alura.edigi.modelo.Livro;

public class LivroDaoImpl implements LivroDao {

	private Connection connection;

	public LivroDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Livro livro) {

		var sql = "INSERT INTO livros (titulo, resumo, sumario, numero_de_paginas, isbn, autor_id, categoria_id, edicao, preco)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getResumo());
			statement.setString(3, livro.getSumario());
			statement.setInt(4, livro.getNumeroDePaginas());
			statement.setString(5, livro.getIsbn());
			statement.setLong(6, livro.getAutor().getId());
			statement.setLong(7, livro.getCategoria().getId());
			statement.setInt(8, livro.getEdicao());
			statement.setBigDecimal(9, livro.getPreco());
			statement.execute();

			var resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				livro.setId(resultSet.getLong(1));
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new RuntimeException("Livro já cadastrado");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Livro> buscaPorTitulo(String titulo) {

		var sql = "SELECT livro.*, "
				+ "autor.id as autor_id, autor.nome as autor_nome, autor.biografia as autor_biografia, "
				+ "autor.email as autor_email, autor.data_cadastro as autor_data_cadastro, "
				+ "categoria.id as categoria_id, categoria.nome as categoria_nome, categoria.data_cadastro as categoria_data_cadastro "
				+ "FROM livros livro " + "LEFT JOIN autores autor ON livro.autor_id = autor.id "
				+ "LEFT JOIN categorias categoria ON livro.categoria_id = categoria.id " + "WHERE titulo LIKE ?";

		try (var statement = connection.prepareStatement(sql)) {

			statement.setString(1, String.format("%%%s%%", titulo));
			var resultSet = statement.executeQuery();

			List<Livro> livros = new ArrayList<>();

			while (resultSet.next()) {
				livros.add(new Livro(resultSet.getString("titulo"), resultSet.getString("resumo"),
						resultSet.getString("sumario"), resultSet.getInt("numero_de_paginas"),
						resultSet.getString("isbn"),
						new Autor(resultSet.getLong("autor_id"), resultSet.getString("autor_nome"),
								resultSet.getString("autor_biografia"), resultSet.getString("autor_email"),
								resultSet.getTimestamp("autor_data_cadastro").toLocalDateTime()),
						new Categoria(resultSet.getLong("categoria_id"), resultSet.getString("categoria_nome"),
								resultSet.getTimestamp("categoria_data_cadastro").toLocalDateTime()),
						resultSet.getInt("edicao"), resultSet.getBigDecimal("preco")));
			}
			return livros;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}