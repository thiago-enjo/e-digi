package br.com.alura.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BancoLivros {

	private Set<Livro> livros = new HashSet<>();
	
	public Set<Livro> getLivros( ) {
		return Collections.unmodifiableSet(livros);
	}
	
	public void addLivro(Livro livro) {
		if (livros.contains(livro)) {
			throw new RuntimeException("Livro já cadastrado");
		}
		livros.add(livro);
	}
}
