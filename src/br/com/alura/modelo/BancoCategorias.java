package br.com.alura.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BancoCategorias {

	private Set<Categoria> categorias = new HashSet<>();

	public Set<Categoria> getCategorias() {
		return Collections.unmodifiableSet(categorias);
	}

	public void adiciona(Categoria categoria) {
		if (categorias.contains(categoria)) {
			throw new RuntimeException("Categoria já cadastrada");
		}
		categorias.add(categoria);
		System.out.println("Categoria " + categoria.getNome() + " cadastrada com sucesso!");
	}
}
