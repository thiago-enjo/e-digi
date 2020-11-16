package br.com.alura.edigi.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
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
import br.com.alura.edigi.modelo.Carrinho.ItemCarrinho;

class CarrinhoTest {

	private static Autor autor;
	private static Categoria categoria;
	private static Livro livro;
	private static Livro outroLivro;
	private static Carrinho carrinho;
	private static ItemCarrinho item;
	private static ItemCarrinho outroItem;

	private static Connection conexao;

	private AutorDao autorDao;
	private CategoriaDao categoriaDao;
	private LivroDao livroDao;

	public CarrinhoTest() throws SQLException {
		conexao = ConnectionFactory.getConexao();
		conexao.setAutoCommit(false);
		autorDao = new AutorDao(conexao);
		categoriaDao = new CategoriaDao(conexao);
		livroDao = new LivroDao(conexao);
		carrinho = new Carrinho(livroDao);
	}

	@BeforeAll
	public static void CriaLivros() throws FileNotFoundException {
		autor = new Autor("Thiago", "Pato de borracha", "thiago@gmail.com");
		categoria = new Categoria("Programação");
		livro = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
				"978-85-66250-44-2", autor, categoria, 1, new BigDecimal("39.90"));
		outroLivro = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate", 154,
				"978-85-42805-09-3", autor, categoria, 2, new BigDecimal("29.90"));
	}

	@BeforeEach
	public void adicionaRegistros() {
		autorDao.adiciona(autor);
		categoriaDao.adiciona(categoria);
		livroDao.adiciona(livro);
		livroDao.adiciona(outroLivro);
	}

	@AfterEach
	public void fechaConexao() throws SQLException {
		conexao.rollback();
		conexao.close();
	}

	@Test
	public void deveAdicionarItemInexistente() {

		item = carrinho.new ItemCarrinho(livro, 1);

		assertDoesNotThrow(() -> carrinho.adiciona(item));
	}

	@Test
	public void deveConterPrecoTotalCorreto() {

		item = carrinho.new ItemCarrinho(livro, 1);
		outroItem = carrinho.new ItemCarrinho(outroLivro, 1);

		carrinho.adiciona(item);
		carrinho.adiciona(outroItem);

		assertEquals(carrinho.getTotal(), new BigDecimal("69.80"));
	}

}
