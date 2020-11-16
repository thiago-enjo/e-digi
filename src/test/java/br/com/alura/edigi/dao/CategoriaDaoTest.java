package br.com.alura.edigi.dao;

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

class CategoriaDaoTest {

	private static Categoria categoria;
	private static Connection conexao;
	private CategoriaDao categoriaDao;

	public CategoriaDaoTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		conexao.setAutoCommit(false);
		categoriaDao = new CategoriaDao(conexao);
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
