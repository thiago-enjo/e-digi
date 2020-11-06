package br.com.alura.edigi.dao;

import java.util.List;

import br.com.alura.edigi.modelo.Livro;

public interface LivroDao {

	public void adiciona(Livro livro);

	public List<Livro> buscaPorTitulo(String titulo);

}
