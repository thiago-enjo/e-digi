package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BancoLivrosTest {

	private BancoLivros livros;
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
	public void deveCadastrarLivroNoBancoCasoLivroJaNaoEstejaCadastrado() {

		assertDoesNotThrow(() -> livros.adiciona(livroProgramadorApaixonado));
	}

	@Test
	public void lancaExcecaoCasoLivroJaEstejaCadastradoNoBanco() {

		livros.adiciona(livroProgramadorApaixonado);

		Throwable exception = assertThrows(RuntimeException.class, () -> livros.adiciona(livroProgramadorApaixonado));
		assertEquals("Livro já cadastrado", exception.getMessage());
	}

	@Nested
	class BuscaDeLivrosNoBancoTest {

		@BeforeEach
		public void adicionaLivrosAoBanco() {
			livros.adiciona(livroProgramadorApaixonado);
			livros.adiciona(livroArteDaGuerra);
			livros.adiciona(livroGuerraDosTronos);
		}

		@Test
		public void deveRetornarLivroCorrespondenteAoTituloInformado() {

			assertEquals(Arrays.asList(livros.getLivros().get(0)), livros.buscaPorTitulo("O Programador Apaixonado"));
		}

		@Test
		public void deveRetornarLivrosCorrespondentesAoTrechoDoTituloInformado() {

			assertEquals(Arrays.asList(livros.getLivros().get(1), livros.getLivros().get(2)),
					livros.buscaPorTitulo("guerra"));
		}

		@Test
		public void naoDeveRetornarNadaCasoLivroNaoExistaNoBanco() {

			assertEquals(Collections.emptyList(), livros.buscaPorTitulo("O Senhor dos Anéis"));
		}

		@Test
		public void lancaExcecaoCasoTituloNaBuscaSejaNulo() {

			Throwable exception = assertThrows(IllegalArgumentException.class, () -> livros.buscaPorTitulo(null));
			assertEquals("Título não pode estar vazio", exception.getMessage());
		}

		@Test
		public void lancaExcecaoCasoTituloNaBuscaTenhaMenosQueDoisCaracteres() {

			Throwable exception = assertThrows(IllegalArgumentException.class, () -> livros.buscaPorTitulo("A"));
			assertEquals("Número de caracteres insuficiente para realizar a busca", exception.getMessage());
		}

	}
}
