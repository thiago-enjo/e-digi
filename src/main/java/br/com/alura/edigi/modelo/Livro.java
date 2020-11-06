package br.com.alura.edigi.modelo;

import java.math.BigDecimal;

public class Livro {

	private Long id;
	private String titulo;
	private String resumo;
	private String sumario;
	private Integer numeroDePaginas;
	private String isbn;
	private Autor autor;
	private Categoria categoria;
	private Integer edicao;
	private BigDecimal preco;

	public Livro(String titulo, String resumo, String sumario, Integer numeroDePaginas, String isbn, Autor autor,
			Categoria categoria, Integer edicao, BigDecimal preco) {
		setTitulo(titulo);
		setResumo(resumo);
		setSumario(sumario);
		setNumeroDePaginas(numeroDePaginas);
		setIsbn(isbn);
		setAutor(autor);
		setCategoria(categoria);
		setEdicao(edicao);
		setPreco(preco);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	private void setTitulo(String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("Título não pode estar vazio");
		}
		this.titulo = titulo.trim();
	}

	public String getIsbn() {
		return isbn;
	}

	private void setIsbn(String isbn) {
		if (isbn == null || isbn.trim().isEmpty()) {
			throw new IllegalArgumentException("ISBN não pode estar vazio");
		} else if (!isbn.startsWith("978")) {
			throw new IllegalArgumentException("Formato de ISBN inválido(978-xx-xxxxx-xx-x)");
		} else if (!isbn.matches("\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d{1}")) {
			throw new IllegalArgumentException("Formato de ISBN inválido(978-xx-xxxxx-xx-x)");
		}
		this.isbn = isbn.trim();
	}

	public String getResumo() {
		return resumo;
	}

	private void setResumo(String resumo) {
		if (resumo == null || resumo.trim().isEmpty()) {
			throw new IllegalArgumentException("Resumo não pode estar vazio");
		} else if (resumo.length() > 500) {
			throw new IllegalArgumentException("Resumo deve ter no máximo 500 caracteres");
		}
		this.resumo = resumo.trim();
	}

	public String getSumario() {
		return sumario;
	}

	private void setSumario(String sumario) {
		if (sumario == null || sumario.trim().isEmpty()) {
			throw new IllegalArgumentException("Sumário não pode estar vazio");
		}
		this.sumario = sumario.trim();
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	private void setNumeroDePaginas(Integer numeroDePaginas) {
		if (numeroDePaginas < 1) {
			throw new IllegalArgumentException("Número de páginas deve ser maior que zero");
		}
		this.numeroDePaginas = numeroDePaginas;
	}

	public Autor getAutor() {
		return autor;
	}

	private void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	private void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getEdicao() {
		return edicao;
	}

	private void setEdicao(Integer edicao) {
		if (edicao < 1) {
			throw new IllegalArgumentException("Edição do livro inválida");
		}
		this.edicao = edicao;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	private void setPreco(BigDecimal preco) {
		if (preco.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Preço inválido");
		}
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "\nTítulo: " + this.titulo + "\nResumo: " + this.resumo + "\nSumário: " + this.sumario
				+ "\nNúmero de páginas: " + this.numeroDePaginas + "\nISBN: " + this.isbn + "\nAutor: "
				+ this.autor.getNome() + "\nCategoria: " + this.categoria.getNome() + "\nEdição: " + this.edicao
				+ "\nPreço: " + this.preco + "\n-----------------------------------------------";
	}

	@Override
	public boolean equals(Object obj) {
		Livro outroLivro = (Livro) obj;
		return this.isbn.equals(outroLivro.isbn) || this.titulo.equals(outroLivro.titulo);
	}
}
