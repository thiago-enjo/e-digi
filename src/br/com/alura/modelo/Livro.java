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
			Categoria categoria, int edicao, BigDecimal preco) {
		if (titulo == null || titulo.isEmpty()) {
			throw new IllegalArgumentException("Título não pode estar vazio");
		}
		this.titulo = titulo;

		if (resumo == null || resumo.isEmpty()) {
			throw new IllegalArgumentException("Resumo não pode estar vazio");
		} else if (resumo.length() > 500) {
			throw new IllegalArgumentException("Resumo deve ter no máximo 500 caracteres");
		}
		this.resumo = resumo;

		if (sumario == null || sumario.isEmpty()) {
			throw new IllegalArgumentException("Sumário não pode estar vazio");
		}
		this.sumario = sumario;

		if (numeroDePaginas < 1) {
			throw new IllegalArgumentException("Número de páginas deve ser maior que zero");
		}
		this.numeroDePaginas = numeroDePaginas;

		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("ISBN não pode estar vazio");
		} else if (!isbn.startsWith("978")) {
			throw new IllegalArgumentException("Formato de ISBN inválido(978-xx-xxxxx-xx-x)");
		} else if (!isbn.matches("\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d{1}")) {
			throw new IllegalArgumentException("Formato de ISBN inválido(978-xx-xxxxx-xx-x)");
		}
		this.isbn = isbn;

		this.autor = autor;

		this.categoria = categoria;

		if (edicao < 1) {
			throw new IllegalArgumentException("Edição do livro inválida");
		}
		this.edicao = edicao;

		if (preco.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Preço inválido");
		}
		this.preco = preco;

		this.dataCadastro = LocalDate.now();
	}

	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public String toString() {
		return "\nTítulo: " + this.titulo + "\nResumo: " + this.resumo + "\nSumário: " + this.sumario
				+ "\nNúmero de páginas: " + this.numeroDePaginas + "\nISBN: " + this.isbn + "\nAutor: "
				+ this.autor.getNome() + "\nCategoria: " + this.categoria.getNome() + "\nEdição: " + this.edicao
				+ "\nPreço: " + this.preco + "\n-----------------------------------------------";
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