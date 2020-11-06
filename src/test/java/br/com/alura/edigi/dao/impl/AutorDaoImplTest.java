package br.com.alura.edigi.dao.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.factory.ConnectionFactory;
import br.com.alura.edigi.modelo.Autor;

class AutorDaoImplTest {

	private static Autor autor;
	private static Connection conexao;
	private AutorDaoImpl autorDao;

	public AutorDaoImplTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		conexao.setAutoCommit(false);
		autorDao = new AutorDaoImpl(conexao);
	}

	@BeforeAll
	public static void criaAutor() {
		autor = new Autor("Thiago", "O jogo", "thiago.yuji@alura.com.br");
	}

	@AfterEach
	public void fechaConexao() throws SQLException {
		conexao.rollback();
		conexao.close();
	}

	@Test
	public void deveCadastrarAutorInexistente() {

		assertDoesNotThrow(() -> autorDao.adiciona(autor));
	}

	@Test
	public void lancaExcecaoCasoAutorComMesmoEmailEstejaCadastrado() {

		autorDao.adiciona(autor);

		Throwable exception = assertThrows(RuntimeException.class, () -> autorDao.adiciona(autor));
		assertEquals("Autor já cadastrado", exception.getMessage());
	}
}
