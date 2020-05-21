package br.com.alura.modelo;

import java.time.LocalDate;

public class Autor {

	private String nome;
	private String email;
	private LocalDate dataCadastro;

	public Autor(String nome, String email) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome não pode estar vazio");
		}
		this.nome = nome;

		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email não pode estar vazio");
		}
		if (!email.matches("^([\\w][\\-]?\\.?)+@(([\\w][\\-]?)+\\.)+([A-Za-z]{2,4})+$")) {
			throw new IllegalArgumentException("Email inválido");
		}
		this.email = email;

		this.dataCadastro = LocalDate.now();
	}

	@Override
	public String toString() {
		return "\nNome: " + this.nome + "\nEmail: " + this.email + "\nData de cadastro: " + this.dataCadastro
				+ "\n-----------------------------------------------";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Autor outroAutor = (Autor) obj;
		return this.email.equals(outroAutor.email);
	}

}
