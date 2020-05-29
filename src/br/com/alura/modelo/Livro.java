package br.com.alura.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Livro {

	private String titulo;
	private String resumo;
	private String sumario;
	private int numeroDePaginas;
	private String isbn;
	private Autor autor;
	private Categoria categoria;
	private int edicao;
	private BigDecimal preco;
	private LocalDate dataCadastro;

	public Livro(String titulo, String resumo, String sumario, int numeroDePaginas, String isbn, Autor autor,
			Categoria categoria, int edicao, BigDecimal preco, LocalDate dataCadastro) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.numeroDePaginas = numeroDePaginas;
		this.isbn = isbn;
		this.autor = autor;
		this.categoria = categoria;
		this.edicao = edicao;
		this.preco = preco;
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "\nTítulo: " + this.titulo + "\nResumo: " + this.resumo + "\nSumário: " + this.sumario
				+ "\nNúmero de páginas: " + this.numeroDePaginas + "\nISBN: " + this.isbn + "\nAutor: "
				+ this.autor.getNome() + "\nCategoria: " + this.categoria.getNome() + "\nEdição: " + this.edicao
                + "\nPreço: " + this.preco + "\nData de Cadastro: " + this.dataCadastro
                + "\n-----------------------------------------------";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Livro outroLivro = (Livro) obj;
		if (this.isbn.equals(outroLivro.isbn) || this.titulo.equals(outroLivro.titulo)) {
			return true;
		}
		return false;
	}

}
