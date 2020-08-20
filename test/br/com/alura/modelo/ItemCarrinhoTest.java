package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemCarrinhoTest {

	private BancoLivros livros;
	private Carrinho carrinho;
	private static Livro livroProgramadorApaixonado;
	private static Livro livroArteDaGuerra;

	@BeforeEach
	public void criaBancoLivros() {
		livros = new BancoLivros();
	}

	@BeforeAll
	public static void CriaLivro() {
		livroProgramadorApaixonado = new Livro("O Programador Apaixonado", "Suas habilidades são um produto",
				"Liderar ou sangrar", 287, "978-85-66250-44-2", new Autor("Chad Fowler", "chadhai@chadfowler.com"),
				new Categoria("Programação"), 1, new BigDecimal("39.90"));

		livroArteDaGuerra = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate", 154,
				"978-85-42805-09-3", new Autor("Sun Tzu", "suntzu@toodeadforemail.com"),
				new Categoria("Infraestrutura"), 2, new BigDecimal("29.90"));
	}

	@Test
	public void deveCriarItemComLivroEQuantidadeValida() {

		livros.adiciona(livroProgramadorApaixonado);
		carrinho = new Carrinho(livros);

		assertDoesNotThrow(() -> carrinho.new ItemCarrinho(livroProgramadorApaixonado, 1));
	}

	@Test
	public void lancaExcecaoCasoLivroNaoExistaNoBanco() {

		livros.adiciona(livroProgramadorApaixonado);
		carrinho = new Carrinho(livros);

		Throwable exception = assertThrows(RuntimeException.class,
				() -> carrinho.new ItemCarrinho(livroArteDaGuerra, 0));
		assertEquals("Livro inexistente no catálogo", exception.getMessage());
	}
	
	@Test
	public void lancaExcecaoCasoQuantidadeDeLivrosSejaMenorQueUm() {
		
		livros.adiciona(livroProgramadorApaixonado);
		carrinho = new Carrinho(livros);

		Throwable exception = assertThrows(RuntimeException.class,
				() -> carrinho.new ItemCarrinho(livroProgramadorApaixonado, 0));
		assertEquals("Quantidade de livros deve ser de pelo menos 1", exception.getMessage());
	}
}
