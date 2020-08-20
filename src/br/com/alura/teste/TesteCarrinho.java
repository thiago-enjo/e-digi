package br.com.alura.teste;

import java.math.BigDecimal;

import br.com.alura.modelo.Autor;
import br.com.alura.modelo.BancoLivros;
import br.com.alura.modelo.Carrinho;
import br.com.alura.modelo.Carrinho.ItemCarrinho;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Livro;

public class TesteCarrinho {

	public static void main(String[] args) {

		BancoLivros livros = new BancoLivros();

		Livro livro1 = new Livro("O Programador Apaixonado", "Suas habilidades são um produto", "Liderar ou sangrar",
				287, "978-85-66250-44-2", new Autor("Chad Fowler", "chadhai@chadfowler.com"),
				new Categoria("Programação"), 1, new BigDecimal("39.90"));

		Livro livro2 = new Livro("A Arte da Guerra", "O mais antigo tratado militar do mundo", "Em combate", 154,
				"978-85-42805-09-3", new Autor("Sun Tzu", "suntzu@toodeadforemail.com"),
				new Categoria("Infraestrutura"), 2, new BigDecimal("29.90"));

		Livro livro3 = new Livro("A Guerra dos Tronos", "O frio está de volta", "A Fúria dos Reis", 154,
				"978-85-44102-92-3", new Autor("George R. R. Martin", "george.wont@finishthebook.com"),
				new Categoria("Business"), 1, new BigDecimal("59.90"));
		
		Livro livro4 = new Livro("A G dos Tronos", "O frio está de volta", "A Fúria dos Reis", 154,
				"978-85-44135-92-3", new Autor("George R. R. Martin", "george.wont@finishthebook.com"),
				new Categoria("Business"), 1, new BigDecimal("59.90"));

		livros.adiciona(livro1);
		livros.adiciona(livro2);
		livros.adiciona(livro3);

		Carrinho carrinho = new Carrinho(livros);

		carrinho.adiciona(carrinho.new ItemCarrinho(livro1, 1));
		carrinho.adiciona(carrinho.new ItemCarrinho(livro2, 3));
		carrinho.adiciona(carrinho.new ItemCarrinho(livro3, 5));
		
		carrinho.checkout();
	}
}