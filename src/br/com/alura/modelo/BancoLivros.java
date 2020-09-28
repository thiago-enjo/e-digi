package br.com.alura.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BancoLivros {

	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		return Collections.unmodifiableList(livros);
	}

	public void adiciona(Livro livro) {
		if (livros.contains(livro)) {
			throw new RuntimeException("Livro já cadastrado");
		}
		livros.add(livro);
		System.out.println("Livro " + livro.getTitulo() + " cadastrado com sucesso!");
	}

	public List<Livro> buscaPorTitulo(String titulo) {
		if (titulo == null) {
			throw new IllegalArgumentException("Título não pode estar vazio");
		} else if (titulo.length() < 2) {
			throw new IllegalArgumentException("Número de caracteres insuficiente para realizar a busca");
		}
		return livros.stream().filter(livro -> livro.getTitulo().toUpperCase().contains(titulo.toUpperCase()))
				.collect(Collectors.toList());
	}
}