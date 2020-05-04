package br.com.alura.teste;

import br.com.alura.modelo.BancoAutores;
import br.com.alura.modelo.Autor;

public class TesteAutor {

	public static void main(String[] args) {

		BancoAutores.addAutor(new Autor("Fabiano", "fabiano.closure@forever.com"));
		BancoAutores.addAutor(new Autor("Jose", "jose.silva@caelum.com.br"));
		BancoAutores.addAutor(new Autor("Maria", "maria.carmo@gmail.com"));
//		BancoAutores.addAutor(new Autor("Maria", "maria.carmo@gmail.com"));

		System.out.println(BancoAutores.getAutores());
	}
}
