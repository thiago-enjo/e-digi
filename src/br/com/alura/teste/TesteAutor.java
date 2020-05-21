package br.com.alura.teste;

import br.com.alura.modelo.BancoAutores;
import br.com.alura.modelo.Autor;

public class TesteAutor {

	public static void main(String[] args) {
		
		BancoAutores autores = new BancoAutores();

		autores.addAutor(new Autor("Fabiano", "fabiano.closure@forever.com"));
		autores.addAutor(new Autor("Jose", "jose.silva@caelum.com.br"));
		autores.addAutor(new Autor("Maria", "maria.carmo@gmail.com"));
//		autores.addAutor(new Autor("Maria", "maria.carmo@gmail.com"));
//		autores.addAutor(new Autor(null, "maria.carmo@gmail.com"));
//		autores.addAutor(new Autor("Maria", null));
//		autores.addAutor(new Autor("", "maria.carmo@gmail.com"));
//		autores.addAutor(new Autor("Maria", ""));

		System.out.println(autores.getAutores());
	}
}
