package br.com.alura.teste;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.builder.LivroBuilder;
import br.com.alura.modelo.Autor;
import br.com.alura.modelo.BancoLivros;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Livro;

public class TesteLivro {

	public static void main(String[] args) {
		
		BancoLivros livros = new BancoLivros();
		
        Livro livro1 = new LivroBuilder()
        .titulo("O Programador Apaixonado")
		.resumo("Suas habilidades são um produto")
		.sumario("Liderar ou sangrar")
		.numeroDePaginas(287)
		.isbn("978-85-66250-44-2")
		.autor(new Autor("Chad Fowler", "chadhai@chadfowler.com"))
		.categoria(new Categoria("Programação"))
		.edicao(1)
		.preco(new BigDecimal("39.90"))
		.dataCadastro(LocalDate.now())
		.build();
		
        Livro livro2 = new LivroBuilder()
        .titulo("A Arte da Guerra")
		.resumo("O mais antigo tratado militar do mundo")
		.sumario("Em combate")
		.numeroDePaginas(154)
		.isbn("978-85-42805-09-3")
		.autor(new Autor("Sun Tzu", "suntzu@toodeadforemail.com"))
		.categoria(new Categoria("Infraestrutura"))
		.edicao(2)
		.preco(new BigDecimal("29.90"))
		.dataCadastro(LocalDate.now())
		.build();
		
		livros.addLivro(livro1);
		livros.addLivro(livro2);
		
		System.out.println(livros.getLivros());

	}
}