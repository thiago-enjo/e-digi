package br.com.alura.modelo;

import java.math.BigDecimal;

public class ItemCarrinho {

	private Livro livro;
	private int quantidade;
	
	public ItemCarrinho(Livro livro, int quantidade) {
		if (quantidade < 1) {
			throw new IllegalArgumentException("Quantidade de livros deve ser de pelo menos 1");
		}
		this.livro = livro;
		this.quantidade = quantidade;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public BigDecimal getSubtotal() {
		return getLivro().getPreco().multiply(new BigDecimal(this.getQuantidade()));
	}
	
	@Override
	public String toString() {
		return "\nTítulo: " + getLivro().getTitulo()
				+ "\nPreço: " + getLivro().getPreco()
				+ "\nQtd: " + getQuantidade()
				+ "\nSubtotal: " + getSubtotal();
	}
}