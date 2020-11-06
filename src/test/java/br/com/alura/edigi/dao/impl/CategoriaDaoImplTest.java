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
import br.com.alura.edigi.modelo.Categoria;

class CategoriaDaoImplTest {

	private static Categoria categoria;
	private static Connection conexao;
	private CategoriaDaoImpl categoriaDao;

	public CategoriaDaoImplTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		conexao.setAutoCommit(false);
		categoriaDao = new CategoriaDaoImpl(conexao);
	}

	@BeforeAll
	public static void criaCategoria() {
		categoria = new Categoria("Programação");
	}

	@AfterEach
	public void fechaConexao() throws SQLException {
		conexao.rollback();
		conexao.close();
	}

	@Test
	public void deveCadastrarCategoriaInexistente() {

		assertDoesNotThrow(() -> categoriaDao.adiciona(categoria));
	}

	@Test
	public void lancaExcecaoCasoCategoriaComMesmoNomeEstejaCadastrada() {

		categoriaDao.adiciona(categoria);

		Throwable exception = assertThrows(RuntimeException.class, () -> categoriaDao.adiciona(categoria));
		assertEquals("Categoria já cadastrada", exception.getMessage());
	}
}
