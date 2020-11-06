package br.com.alura.edigi.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AutorTest {

	@Test
	public void deveCriarAutorComNomeEEmailValido() {

		assertDoesNotThrow(() -> new Autor("Thiago", "O jogo", "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoNomeDoAutorEstejaVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("", "O jogo", "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoNomeDoAutorSejaNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Autor(null, "O jogo", "thiago.yuji@caelum.com.br"));
	}
	
	@Test
	public void lancaExcecaoCasoBiografiaDoAutorEstejaVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("Thiago", "", "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoBiografiaDoAutorSejaNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("Thiago", null, "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoEmailDoAutorEstejaVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("Thiago", "O jogo", ""));
	}

	@Test
	public void lancaExcecaoCasoEmailDoAutorSejaNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("Thiago", "O jogo", null));
	}

	@Test
	public void lancaExcecaoCasoEmailDoAutorTenhaFormatoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Autor("Thiago", "O jogo", ".yuji@caelum.com");
			new Autor("Thiago", "O jogo", "thiago.yujicaelum.com");
			new Autor("Thiago", "O jogo", "thiago.yuji@.com");
			new Autor("Thiago", "O jogo", "thiago.yuji@caelumcom");
			new Autor("Thiago", "O jogo", "thiago.yuji@caelum.");
		});
	}
}
