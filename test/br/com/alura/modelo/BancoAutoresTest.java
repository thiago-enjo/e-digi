package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancoAutoresTest {

	private BancoAutores autores;
	private Autor autor;

	@BeforeEach
	public void criaBancoAutores() {
		autores = new BancoAutores();
	}
	
	@BeforeEach
	public void CriaCategoria() {
		autor = new Autor("Thiago", "thiago.yuji@hotmail.com");
	}

	@Test
	public void deveCadastrarAutorNoBanco() {

		assertDoesNotThrow(() -> autores.adiciona(this.autor));
	}

	@Test
	public void lancaExcecaoCasoAutorJaEstejaCadastradoNoBanco() {
		
		autores.adiciona(this.autor);

		Throwable exception = assertThrows(RuntimeException.class,
				() -> autores.adiciona(this.autor));
		assertEquals("Autor já cadastrado", exception.getMessage());
	}
}
