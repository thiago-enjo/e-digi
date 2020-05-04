package br.com.alura.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BancoAutores {

	private static List<Autor> autores = new ArrayList<>();

	public static List<Autor> getAutores() {
		return Collections.unmodifiableList(autores);
	}

	public static void addAutor(Autor autor) {
		if (autores.contains(autor)) {
			throw new RuntimeException("Autor já cadastrado");
		}
		BancoAutores.autores.add(autor);
	}
}
