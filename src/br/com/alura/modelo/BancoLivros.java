package br.com.alura.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BancoLivros {

	private Set<Livro> livros = new HashSet<>();

	public Set<Livro> getLivros() {
		return Collections.unmodifiableSet(livros);
	}

	public void adiciona(Livro livro) {
		if (livros.contains(livro)) {
			throw new RuntimeException("Livro já cadastrado");
		}
		livros.add(livro);
		System.out.println("Livro " + livro.getTitulo() + " cadastrado com sucesso!");
	}

	public Set<Livro> buscaPorTitulo(String titulo) {
		Set<Livro> resultado = new HashSet<>();
		if (titulo == null) {
			throw new IllegalArgumentException("Título não pode estar vazio");
		} else if (titulo.length() < 2) {
			throw new IllegalArgumentException("Número de caracteres insuficiente para realizar a busca");
		}
		for (Livro livro : livros) {
			if (livro.getTitulo().toUpperCase().contains(titulo.toUpperCase())) {
				resultado.add(livro);
			}
		}
		if (resultado.isEmpty()) {
			throw new RuntimeException("Título não encontrado");
		}
		return Collections.unmodifiableSet(resultado);
	}
}
