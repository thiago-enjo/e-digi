package br.com.alura.teste;

import br.com.alura.modelo.BancoCategorias;
import br.com.alura.modelo.Categoria;

public class TesteCategoria {

	public static void main(String[] args) {
		
		BancoCategorias categorias = new BancoCategorias();
		
		categorias.addCategoria(new Categoria("Programação"));
		categorias.addCategoria(new Categoria("Mobile"));
		categorias.addCategoria(new Categoria("Front-end"));
		categorias.addCategoria(new Categoria("Infraestrutura"));
		categorias.addCategoria(new Categoria("Business"));
		categorias.addCategoria(new Categoria("Design & UX"));
//		categorias.addCategoria(new Categoria("Programação"));
//		categorias.addCategoria(new Categoria(null));
//		categorias.addCategoria(new Categoria(""));
		
		System.out.println(categorias.getCategorias());
	}
}
