package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CategoriaTest {

	@Test
	public void lancaExcecaoCasoNomeDaCategoriaEstejaVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Categoria(""));
	}

	@Test
	public void lancaExcecaoCasoNomeDaCategoriaSejaNula() {

		assertThrows(IllegalArgumentException.class, () -> new Categoria(null));
	}

}
