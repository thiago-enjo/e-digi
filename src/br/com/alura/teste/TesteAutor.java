package br.com.alura.teste;

import br.com.alura.modelo.BancoAutores;
import br.com.alura.modelo.Autor;

public class TesteAutor {

	public static void main(String[] args) {

		BancoAutores autores = new BancoAutores();

		autores.adiciona(new Autor("Fabiano", "fabiano.closure@forever.com"));
		autores.adiciona(new Autor("Jose", "jose.silva@caelum.com.br"));
		autores.adiciona(new Autor("Maria", "maria.carmo@gmail.com"));
//		autores.adiciona(new Autor("Maria", "maria.carmo@gmail.com"));
//		autores.adiciona(new Autor(null, "maria.carmo@gmail.com"));
//		autores.adiciona(new Autor("Maria", null));
//		autores.adiciona(new Autor("", "maria.carmo@gmail.com"));
//		autores.adiciona(new Autor("Maria", ""));

		System.out.println(autores.getAutores());
	}
}
