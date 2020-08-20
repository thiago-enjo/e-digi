package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarrinhoTest {

	private BancoLivros livros;
	private Carrinho carrinho;
	private static Livro livroProgramadorApaixonado;
	private static Livro livroArteDaGuerra;
	private static Livro livroGuerraDosTronos;

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

		livroGuerraDosTronos = new Livro("A Guerra dos Tronos", "O frio está de volta", "A Fúria dos Reis", 154,
				"978-85-44102-92-3", new Autor("George R. R. Martin", "george.wont@finishthebook.com"),
				new Categoria("Business"), 1, new BigDecimal("59.90"));
	}

	@Test
	public void deveAdicionarItemComSucesso() {

		livros.adiciona(livroProgramadorApaixonado);
		carrinho = new Carrinho(livros);

		assertDoesNotThrow(() -> carrinho.adiciona(carrinho.new ItemCarrinho(livroProgramadorApaixonado, 1)));
	}

	@Test
	public void deveConterOPrecoTotalCorreto() {

		carrinho = new Carrinho(livros);
		livros.adiciona(livroProgramadorApaixonado);
		livros.adiciona(livroArteDaGuerra);
		livros.adiciona(livroGuerraDosTronos);

		carrinho.adiciona(carrinho.new ItemCarrinho(livroProgramadorApaixonado, 1));
		carrinho.adiciona(carrinho.new ItemCarrinho(livroArteDaGuerra, 1));
		carrinho.adiciona(carrinho.new ItemCarrinho(livroGuerraDosTronos, 1));

		BigDecimal precoTotal = livroProgramadorApaixonado.getPreco().add(livroArteDaGuerra.getPreco())
				.add(livroGuerraDosTronos.getPreco());

		assertEquals(precoTotal, carrinho.getTotal());
	}

}
