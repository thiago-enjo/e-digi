package br.com.alura.teste;

import java.math.BigDecimal;

import br.com.alura.modelo.Autor;
import br.com.alura.modelo.BancoLivros;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Livro;

public class TesteLivro {

	public static void main(String[] args) {

		BancoLivros livros = new BancoLivros();

		Livro livro1 = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar",
				287, "978-85-66250-44-2", new Autor("Chad Fowler", "chadhai@chadfowler.com"),
				new Categoria("Programação"), 1, new BigDecimal("39.90"));

		Livro livro2 = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate", 154,
				"978-85-42805-09-3", new Autor("Sun Tzu", "suntzu@toodeadforemail.com"),
				new Categoria("Infraestrutura"), 2, new BigDecimal("29.90"));

		livros.adiciona(livro1);
		livros.adiciona(livro2);

		System.out.println(livros.getLivros());

	}
}
