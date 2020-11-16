package br.com.alura.edigi.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.dao.AutorDao;
import br.com.alura.edigi.dao.CategoriaDao;
import br.com.alura.edigi.dao.LivroDao;
import br.com.alura.edigi.factory.ConnectionFactory;

class ItemCarrinhoTest {

	private static Autor autor;
	private static Categoria categoria;
	private static Livro livro;
	private Carrinho carrinho;

	private static Connection conexao;

	private AutorDao autorDao;
	private CategoriaDao categoriaDao;
	private LivroDao livroDao;

	public ItemCarrinhoTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		conexao.setAutoCommit(false);
		autorDao = new AutorDao(conexao);
		categoriaDao = new CategoriaDao(conexao);
		livroDao = new LivroDao(conexao);
		carrinho = new Carrinho(livroDao);
	}

	@BeforeAll
	public static void CriaLivro() {
		autor = new Autor("Thiago", "Pato de borracha", "thiago@gmail.com");
		categoria = new Categoria("Programação");
		livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));
	}

	@BeforeEach
	public void adicionaRegistros() {
		autorDao.adiciona(autor);
		categoriaDao.adiciona(categoria);
		livroDao.adiciona(livro);
	}

	@AfterEach
	public void fechaConexao() throws SQLException {
		conexao.rollback();
		conexao.close();
	}

	@Test
	public void deveCriarItemComLivroEQuantidadeValida() {

		assertDoesNotThrow(() -> carrinho.new ItemCarrinho(livro, 1));
	}

	@Test
	public void lancaExcecaoCasoLivroNaoExistaNoBanco() {

		var livroInexistente = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate",
				154, "978-85-42805-09-3", autor, categoria, 2, new BigDecimal("29.90"));

		Throwable exception = assertThrows(RuntimeException.class,
				() -> carrinho.new ItemCarrinho(livroInexistente, 0));
		assertEquals("Livro inexistente no catálogo", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoQuantidadeDeLivrosSejaMenorQueUm() {

		Throwable exception = assertThrows(RuntimeException.class,
				() -> carrinho.new ItemCarrinho(livro, 0));
		assertEquals("Quantidade de livros deve ser de pelo menos 1", exception.getMessage());
	}
}
