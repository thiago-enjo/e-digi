package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AutorTest {

	@Test
	public void deveCriarAutorComNomeEEmailValido() {

		assertDoesNotThrow(() -> new Autor("Thiago", "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoNomeDoAutorEstejaVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("", "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoNomeDoAutorSejaNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Autor(null, "thiago.yuji@caelum.com.br"));
	}

	@Test
	public void lancaExcecaoCasoEmailDoAutorEstejaVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("Thiago", ""));
	}

	@Test
	public void lancaExcecaoCasoEmailDoAutorSejaNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Autor("Thiago", null));
	}

	@Test
	public void lancaExcecaoCasoEmailDoAutorTenhaFormatoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Autor("Thiago", ".yuji@caelum.com");
			new Autor("Thiago", "thiago.yujicaelum.com");
			new Autor("Thiago", "thiago.yuji@.com");
			new Autor("Thiago", "thiago.yuji@caelumcom");
			new Autor("Thiago", "thiago.yuji@caelum.");
		});
	}
}
