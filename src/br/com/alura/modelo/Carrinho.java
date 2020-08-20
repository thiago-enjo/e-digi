package br.com.alura.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<ItemCarrinho> itens = new ArrayList<>();
	private BigDecimal total = new BigDecimal(0);
	private LocalDate dataVenda;
	private BancoLivros livros;

	public Carrinho(BancoLivros livros) {
		this.livros = livros;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void adiciona(ItemCarrinho item) {
		this.itens.add(item);
		this.total = this.total.add(item.livro.getPreco().multiply(BigDecimal.valueOf(item.quantidade)));
		System.out.println(
				item.quantidade + " unidade(s) de \"" + item.livro.getTitulo() + "\" adicionado(s) ao carrinho!");
	}

	public void checkout() {
		this.dataVenda = LocalDate.now();

		System.out.println("\nCompra realizada com sucesso!");
		this.itens.forEach(item -> System.out.println(item.getDados()));
		System.out.printf("Total: %.2f", this.total);
	}

	public class ItemCarrinho {
		private Livro livro;
		private int quantidade;

		public ItemCarrinho(Livro livro, Integer quantidade) {
			if (livros.buscaPorTitulo(livro.getTitulo()).isEmpty()) {
				throw new RuntimeException("Livro inexistente no catálogo");
			}
			if (quantidade < 1) {
				throw new IllegalArgumentException("Quantidade de livros deve ser de pelo menos 1");
			}
			this.livro = livro;
			this.quantidade = quantidade;
		}

		public String getDados() {
			return String.format("%nTítulo: %s%n Preço: %.2f%n Qtd: %d%n Subtotal: %.2f%n", livro.getTitulo(),
					livro.getPreco(), quantidade, livro.getPreco().multiply(new BigDecimal(this.quantidade)));
		}
	}
}