package br.com.alura.edigi.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.factory.ConnectionFactory;
import br.com.alura.edigi.modelo.Autor;
import br.com.alura.edigi.modelo.Categoria;
import br.com.alura.edigi.modelo.Livro;

class LivroDaoTest {

	private static Autor autor;
	private static Categoria categoria;

	private static Connection conexao;

	private AutorDao autorDao;
	private static CategoriaDao categoriaDao;
	private LivroDao livroDao;

	public LivroDaoTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		conexao.setAutoCommit(false);
		autorDao = new AutorDao(conexao);
		categoriaDao = new CategoriaDao(conexao);
		livroDao = new LivroDao(conexao);
	}

	@BeforeAll
	public static void CriaAutoresECategorias() throws FileNotFoundException {
		autor = new Autor("Thiago", "Pato de borracha", "thiago@gmail.com");
		categoria = new Categoria("Programação");
	}

	@BeforeEach
	public void adicionaRegistros() {
		autorDao.adiciona(autor);
		categoriaDao.adiciona(categoria);
	}

	@AfterEach
	public void fechaConexao() throws SQLException {
		conexao.rollback();
		conexao.close();
	}

	@Test
	public void deveCadastrarLivroInexistente() throws SQLException {

		var livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));

		assertDoesNotThrow(() -> livroDao.adiciona(livro));
	}

	@Test
	public void lancaExcecaoCasoLivroComMesmoTituloEstejaCadastrado() {

		var livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));

		var livroComMesmoTitulo = new Livro("O Programador Apaixonado", "Suas habilidades são um produto",
				"Liderar ou sangrar", 287, "978-58-05266-44-1", autor, categoria, 1, new BigDecimal("39.90"));

		livroDao.adiciona(livro);

		Throwable exception = assertThrows(RuntimeException.class, () -> livroDao.adiciona(livroComMesmoTitulo));
		assertEquals("Livro já cadastrado", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoLivroComMesmoIsbnEstejaCadastrado() {

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
