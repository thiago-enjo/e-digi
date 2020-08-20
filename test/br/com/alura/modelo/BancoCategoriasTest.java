package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancoCategoriasTest {

	private BancoCategorias categorias;
	private Categoria categoria;
	
	@BeforeEach
	public void criaBancoCategorias() {
		categorias = new BancoCategorias();
	}
	
	@BeforeEach
	public void CriaCategoria() {
		categoria = new Categoria("Programação");
	}

	@Test
	public void deveCadastrarCategoriaNoBanco() {

		assertDoesNotThrow(() -> categorias.adiciona(this.categoria));
	}

	@Test
	public void lancaExcecaoCasoCategoriaJaEstejaCadastradaNoBanco() {
		
		categorias.adiciona(this.categoria);

		Throwable exception = assertThrows(RuntimeException.class,
				() -> categorias.adiciona(this.categoria));
		assertEquals("Categoria já cadastrada", exception.getMessage());
	}
}
