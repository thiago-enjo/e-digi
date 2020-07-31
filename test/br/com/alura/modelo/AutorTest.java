package br.com.alura.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutorTest {
	
	private Autor autor;
	
	@BeforeEach
	public void criaAutor() {
		this.autor = new Autor("Thiago", "thiago.yuji@caelum.com.br");
	}
	
	public void criaAutorComParametros(String nome, String email) {
		this.autor = new Autor(nome, email);
	}

	@Test
	public void nomeDoAutorNaoPodeEstarVazio() {
		
		assertEquals(false, autor.getNome().isEmpty());
	}
	
	@Test
	public void lancaExcecaoCasoNomeDoAutorEstejaVazio() {
		
		assertThrows(IllegalArgumentException.class, () -> criaAutorComParametros("", "thiago.yuji@caelum.com.br"));
	}
	
	@Test
	public void nomeDoAutorNaoPodeSerNulo() {
		
		assertEquals(false, autor.getNome().equals(null));
	}
	
	@Test
	public void lancaExcecaoCasoNomeDoAutorSejaNulo() {
		
		assertThrows(IllegalArgumentException.class, () -> criaAutorComParametros(null, "thiago.yuji@caelum.com.br"));
	}
	
	@Test
	public void emailDoAutorNaoPodeEstarVazio() {
		
		assertEquals(false, autor.getEmail().isEmpty());
	}
	
	@Test
	public void lancaExcecaoCasoEmailDoAutorEstejaVazio() {
		
		assertThrows(IllegalArgumentException.class, () -> criaAutorComParametros("Thiago", ""));
	}
	
	@Test
	public void emailDoAutorNaoPodeSerNulo() {
		
		assertEquals(false, autor.getEmail().equals(null));
	}
	
	@Test
	public void lancaExcecaoCasoEmailDoAutorSejaNulo() {
		
		assertThrows(IllegalArgumentException.class, () -> criaAutorComParametros("Thiago", null));
	}
	
	@Test
	public void emailDoAutorDeveTerFormatoValido() {
		
		assertEquals(true, autor.getEmail().matches("^([\\w][\\-]?\\.?)+@(([\\w][\\-]?)+\\.)+([A-Za-z]{2,4})+$"));
	}
	
	@Test
	public void lancaExcecaoCasoEmailDoAutorTenhaFormatoInvalido() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			criaAutorComParametros("Thiago", ".yuji@caelum.com");
			criaAutorComParametros("Thiago", "thiago.yujicaelum.com");
			criaAutorComParametros("Thiago", "thiago.yuji@.com");
			criaAutorComParametros("Thiago", "thiago.yuji@caelumcom");
			criaAutorComParametros("Thiago", "thiago.yuji@caelum.");
		});	
	}
}
