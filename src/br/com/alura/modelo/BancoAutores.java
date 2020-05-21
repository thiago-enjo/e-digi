package br.com.alura.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BancoAutores {

	private Set<Autor> autores = new HashSet<>();

	public Set<Autor> getAutores() {
		return Collections.unmodifiableSet(autores);
	}

	public void addAutor(Autor autor) {
		if (autores.contains(autor)) {
			throw new RuntimeException("Autor já cadastrado");
		}
		autores.add(autor);
	}
}
