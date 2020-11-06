package br.com.alura.edigi.modelo;

import java.time.LocalDateTime;

public class Categoria {

	private Long id;
	private String nome;
	private LocalDateTime dataCadastro;

	public Categoria(String nome) {
		setNome(nome);
	}

	public Categoria(Long id, String nome, LocalDateTime dataCadastro) {
		this.id = id;
		setNome(nome);
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome não pode estar vazio");
		}
		this.nome = nome.trim();
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", dataCadastro=" + dataCadastro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Categoria outraCategoria = (Categoria) obj;
		return this.nome.equals(outraCategoria.nome);
	}
}
