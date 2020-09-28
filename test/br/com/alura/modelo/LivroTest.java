package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class LivroTest {

	@Test
	public void deveCriarLivroComDadosValidos() {

		assertDoesNotThrow(() -> {
			new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
					"978-85-66250-44-2", new Autor("Chad Fowler", "chadhai@chadfowler.com"),
					new Categoria("Programação"), 1, new BigDecimal("39.90"));
		});
	}

	@Test
	public void lancaExcecaoCasoTituloDoLivroEstejaVazio() {

		Throwable exception = assertThrows(IllegalArgumentException.class,
				() -> new Livro("", null, null, 0, null, null, null, 0, null));
		assertEquals("Título não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoTituloDoLivroSejaNulo() {

		Throwable exception = assertThrows(IllegalArgumentException.class,
				() -> new Livro(null, null, null, 0, null, null, null, 0, null));
		assertEquals("Título não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoResumoEstejaVazio() {

		Throwable exception = assertThrows(IllegalArgumentException.class,
				() -> new Livro("O Programador Apaixonado", "", null, 0, null, null, null, 0, null));
		assertEquals("Resumo não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoResumoSejaNulo() {

		Throwable exception = assertThrows(IllegalArgumentException.class,
				() -> new Livro("O Programador Apaixonado", null, null, 0, null, null, null, 0, null));
		assertEquals("Resumo não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoResumoUltrapasseQuinhentosCaracteres() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Livro("O Programador Apaixonado",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur a purus ac dui imperdiet viverra et pellentesque eros. Proin cursus egestas diam, vitae porta est blandit ut. Proin tempor molestie tellus, vel hendrerit felis aliquam vitae. Quisque lectus tortor, lobortis at quam in, laoreet scelerisque diam. Sed sit amet tortor consequat, vehicula mauris ac, vestibulum massa. Proin magna orci, cursus ac velit et, mattis rhoncus libero. Proin vitae scelerisque neque. Etiam malesuada sapien cras.",
				null, 0, null, null, null, 0, null));
		assertEquals("Resumo deve ter no máximo 500 caracteres", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoSumarioEstejaVazio() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Livro("O Programador Apaixonado",
				"Suas habilidades são um produto", "", 0, null, null, null, 0, null));
		assertEquals("Sumário não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoSumarioSejaNulo() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Livro("O Programador Apaixonado",
				"Suas habilidades são um produto", null, 0, null, null, null, 0, null));
		assertEquals("Sumário não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoNumeroDePaginasSejaMenorQueUm() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Livro("O Programador Apaixonado",
				"Suas habilidades são um produto", "Liderar ou sangrar", 0, null, null, null, 0, null));
		assertEquals("Número de páginas deve ser maior que zero", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoIsbnEstejaVazio() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287, "",
					null, null, 0, null);
		});
		assertEquals("ISBN não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoIsbnSejaNulo() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287, null,
					null, null, 0, null);
		});
		assertEquals("ISBN não pode estar vazio", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoIsbnTenhaFormatoInvalido() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
					"999-85-66250-44-2", null, null, 0, null);
			new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar", 287,
					"97885-66250-44-2", null, null, 0, null);
		});
		assertEquals("Formato de ISBN inválido(978-xx-xxxxx-xx-x)", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoNumeroDaEdicaoSejaMenorQueUm() {

		Throwable exception = assertThrows(IllegalArgumentException.class,
				() -> new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar",
						287, "978-85-66250-44-2", new Autor("Chad Fowler", "chadhai@chadfowler.com"),
						new Categoria("Programação"), 0, null));
		assertEquals("Edição do livro inválida", exception.getMessage());
	}

	@Test
	public void lancaExcecaoCasoPrecoDoLivroSejaUmNumeroNegativo() {

		Throwable exception = assertThrows(IllegalArgumentException.class,
				() -> new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar",
						287, "978-85-66250-44-2", new Autor("Chad Fowler", "chadhai@chadfowler.com"),
						new Categoria("Programação"), 1, new BigDecimal("-39.90")));
		assertEquals("Preço inválido", exception.getMessage());
	}
}
