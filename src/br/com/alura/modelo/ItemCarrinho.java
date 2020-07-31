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

	public String getDados() {
		return "\nTítulo: " + livro.getTitulo()
		+ "\nPreço: " + livro.getPreco()
		+ "\nQtd: " + getQuantidade()
		+ "\nSubtotal: " + getSubtotal();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getSubtotal() {
		return livro.getPreco().multiply(new BigDecimal(this.getQuantidade()));
	}
}