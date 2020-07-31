package br.com.alura.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<ItemCarrinho> itens = new ArrayList<>();
	private BigDecimal total = new BigDecimal(0);
	private LocalDate dataVenda;

	public BigDecimal getTotal() {
		return total;
	}

	public void adiciona(ItemCarrinho item) {
		itens.add(item);
		this.total = this.total.add(item.getSubtotal());
	}

	public void checkout() {
		this.dataVenda = LocalDate.now();
		System.out.println("\nCompra realizada com sucesso!");

		itens.forEach(item -> System.out.println(item.getDados()));

		System.out.println("\nTotal: " + this.getTotal());
	}
}