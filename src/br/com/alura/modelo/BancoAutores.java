package br.com.alura.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BancoAutores {

	private static Set<Autor> autores = new HashSet<>();

	public static Set<Autor> getAutores() {
		return Collections.unmodifiableSet(autores);
	}

	public static void addAutor(Autor autor) {
		if (autores.contains(autor)) {
			throw new RuntimeException("Autor já cadastrado");
		}
		BancoAutores.autores.add(autor);
	}
}
