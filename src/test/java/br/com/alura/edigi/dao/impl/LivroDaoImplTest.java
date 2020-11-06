package br.com.alura.edigi.dao.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.factory.ConnectionFactory;
import br.com.alura.edigi.modelo.Autor;
import br.com.alura.edigi.modelo.Categoria;
import br.com.alura.edigi.modelo.Livro;

class LivroDaoImplTest {

	private static Connection conexao;
	private LivroDaoImpl livroDao;

	public LivroDaoImplTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		livroDao = new LivroDaoImpl(conexao);
	}

	@BeforeEach
	public void recriaTabelas() throws FileNotFoundException {
		ScriptRunner sr = new ScriptRunner(conexao);
		Reader reader = new BufferedReader(new FileReader("src/test/resources/db-reset.sql"));
		sr.runScript(reader);

		AutorDaoImpl autorDao = new AutorDaoImpl(conexao);
		autorDao.adiciona(new Autor("Thiago", "Pato de borracha", "thiago@gmail.com"));
		CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl(conexao);
		categoriaDao.adiciona(new Categoria("Programação"));
	}

	@AfterEach
	public void fechaConexao() throws SQLException {
		conexao.close();
	}

	@Test
	public void deveCadastrarLivroInexistente() throws SQLException {

		var autor = new AutorDaoImpl(conexao).buscaPorId(1L);
		var categoria = new CategoriaDaoImpl(conexao).buscaPorId(1L);

		var livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));

		assertDoesNotThrow(() -> livroDao.adiciona(livro));
	}

	@Test
	public void lancaExcecaoCasoLivroComMesmoTituloEstejaCadastrado() {

		var autor = new AutorDaoImpl(conexao).buscaPorId(1L);
		var categoria = new CategoriaDaoImpl(conexao).buscaPorId(1L);

		var livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));

		var livroComMesmoTitulo = new Livro("O Programador Apaixonado", "Suas habilidades são um produto",
				"Liderar ou sangrar", 287, "978-85-66250-44-1", autor, categoria, 1, new BigDecimal("39.90"));

		livroDao.adiciona(livro);

		Throwable exception = assertThrows(RuntimeException.class, () -> livroDao.adiciona(livroComMesmoTitulo));
		assertEquals("Livro já cadastrado", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoLivroComMesmoIsbnEstejaCadastrado() {

		var autor = new AutorDaoImpl(conexao).buscaPorId(1L);
		var categoria = new CategoriaDaoImpl(conexao).buscaPorId(1L);

		var livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));

		var livroComMesmoIsbn = new Livro("O Programador Xonado", "Suas habilidades são um produto",
				"Liderar ou sangrar", 287, "978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));

		livroDao.adiciona(livro);

		Throwable exception = assertThrows(RuntimeException.class, () -> livroDao.adiciona(livroComMesmoIsbn));
		assertEquals("Livro já cadastrado", exception.getMessage());
	}

	@Test
	public void deveBuscarERetornarLivrosCorrespondentesAoTrechoDoTitulo() {

		var autor = new AutorDaoImpl(conexao).buscaPorId(1L);
		var categoria = new CategoriaDaoImpl(conexao).buscaPorId(1L);

		var livro = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate", 154,
				"978-85-42805-09-3", autor, categoria, 2, new BigDecimal("29.90"));

		var outroLivro = new Livro("A Guerra dos Tronos", "O frio está de volta", "A Fúria dos Reis", 154,
				"978-85-44102-92-3", autor, categoria, 1, new BigDecimal("59.90"));

		livroDao.adiciona(livro);
		livroDao.adiciona(outroLivro);

		var listaDeLivros = livroDao.buscaPorTitulo("Guerra");

		assertEquals(2, listaDeLivros.size());
		assertIterableEquals(Arrays.asList(livro, outroLivro), listaDeLivros);
	}
	
	@Test
	public void deveRetornarUmaListaVaziaCasoNaoExistamLivrosCorrespondentesAoTrechoDoTitulo() {

		var autor = new AutorDaoImpl(conexao).buscaPorId(1L);
		var categoria = new CategoriaDaoImpl(conexao).buscaPorId(1L);

		var livro = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate", 154,
				"978-85-42805-09-3", autor, categoria, 2, new BigDecimal("29.90"));

		var outroLivro = new Livro("A Guerra dos Tronos", "O frio está de volta", "A Fúria dos Reis", 154,
				"978-85-44102-92-3", autor, categoria, 1, new BigDecimal("59.90"));

		livroDao.adiciona(livro);
		livroDao.adiciona(outroLivro);

		var listaDeLivros = livroDao.buscaPorTitulo("Programador");

		assertTrue(listaDeLivros.isEmpty());
	}
	
}
