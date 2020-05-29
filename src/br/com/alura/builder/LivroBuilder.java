package br.com.alura.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.modelo.Autor;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Livro;

public class LivroBuilder {

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

	public LivroBuilder titulo(String titulo) {
		if (titulo == null || titulo.isEmpty()) {
			throw new IllegalArgumentException("Título não pode estar vazio");
		}
		this.titulo = titulo;
		return this;
	}

	public LivroBuilder resumo(String resumo) {
		if (resumo == null || resumo.isEmpty()) {
			throw new IllegalArgumentException("Resumo não pode estar vazio");
		} else if (resumo.length() > 500) {
			throw new IllegalArgumentException("Resumo deve ter no máximo 500 caracteres");
		}
		this.resumo = resumo;
		return this;
	}

	public LivroBuilder sumario(String sumario) {
		if (sumario == null || sumario.isEmpty()) {
			throw new IllegalArgumentException("Sumário não pode estar vazio");
		}
		this.sumario = sumario;
		return this;
	}

	public LivroBuilder numeroDePaginas(int numeroDePaginas) {
		if (numeroDePaginas < 1) {
			throw new IllegalArgumentException("Número de páginas deve ser maior que zero");
		}
		this.numeroDePaginas = numeroDePaginas;
		return this;
	}

	public LivroBuilder isbn(String isbn) {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("ISBN não pode estar vazio");
		} else if (!isbn.startsWith("978")) {
			throw new IllegalArgumentException("Formato de ISBN inválido(978-xx-xxxxx-xx-x)");
		} else if (!isbn.matches("\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d{1}")) {
			throw new IllegalArgumentException("Formato de ISBN inválido(978-xx-xxxxx-xx-x)");
		}
		this.isbn = isbn;
		return this;
	}

	public LivroBuilder autor(Autor autor) {
		this.autor = autor;
		return this;
	}

	public LivroBuilder categoria(Categoria categoria) {
		this.categoria = categoria;
		return this;
	}

	public LivroBuilder edicao(int edicao) {
		if (edicao < 1) {
			throw new IllegalArgumentException("Edição do livro inválida");
		}
		this.edicao = edicao;
		return this;
	}

	public LivroBuilder preco(BigDecimal preco) {
		if (preco.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Preço inválido");
		}
		this.preco = preco;
		return this;
	}

	public LivroBuilder dataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
		return this;
	}

	public Livro build() {
		return new Livro(titulo, resumo, sumario, numeroDePaginas, isbn, autor, categoria, edicao, preco, dataCadastro);
	}
}