package br.com.alura.modelo;

import java.time.LocalDate;

public class Categoria {

	private String nome;
	private LocalDate dataCadastro;

	public Categoria(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome não pode estar vazio");
		}
		this.nome = nome;

		this.dataCadastro = LocalDate.now();
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "\nNome: " + this.nome
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
		return this.nome.equals(outraCategoria.getNome());
	}
}
