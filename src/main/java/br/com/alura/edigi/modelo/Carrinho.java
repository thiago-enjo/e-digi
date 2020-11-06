package br.com.alura.edigi.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.edigi.repositorio.BancoLivros;

public class Carrinho {

	private List<ItemCarrinho> itens = new ArrayList<>();
	private BigDecimal total = new BigDecimal(0);
	private LocalDateTime dataVenda;
	private BancoLivros livros;

	public Carrinho(BancoLivros livros) {
		this.livros = livros;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void adiciona(ItemCarrinho item) {
		this.itens.add(item);
		this.total = this.total.add(item.getSubtotal());
		System.out.println(
				item.quantidade + " unidade(s) de \"" + item.livro.getTitulo() + "\" adicionado(s) ao carrinho!");
	}

	public void checkout() {
		this.dataVenda = LocalDateTime.now();

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

		public BigDecimal getSubtotal() {
			return livro.getPreco().multiply(BigDecimal.valueOf(quantidade));
		}

		public String getDados() {
			return String.format("%nTítulo: %s%n Preço: %.2f%n Qtd: %d%n Subtotal: %.2f%n", livro.getTitulo(),
					livro.getPreco(), quantidade, livro.getPreco().multiply(new BigDecimal(this.quantidade)));
		}
	}
}
