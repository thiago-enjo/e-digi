package br.com.alura.teste;

import br.com.alura.modelo.BancoCategorias;
import br.com.alura.modelo.Categoria;

public class TesteCategoria {

	public static void main(String[] args) {

		BancoCategorias categorias = new BancoCategorias();

		categorias.adiciona(new Categoria("Programação"));
		categorias.adiciona(new Categoria("Mobile"));
		categorias.adiciona(new Categoria("Front-end"));
		categorias.adiciona(new Categoria("Infraestrutura"));
		categorias.adiciona(new Categoria("Business"));
		categorias.adiciona(new Categoria("Design & UX"));
//		categorias.adiciona(new Categoria("Programação"));
//		categorias.adiciona(new Categoria(null));
//		categorias.adiciona(new Categoria(""));

		System.out.println(categorias.getCategorias());
	}
}
