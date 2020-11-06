package br.com.alura.edigi.modelo;

import java.time.LocalDateTime;

public class Autor {

	private Long id;
	private String nome;
	private String email;
	private String biografia;
	private LocalDateTime dataCadastro;

	public Autor(String nome, String biografia, String email) {
		setNome(nome);
		setBiografia(biografia);
		setEmail(email);
	}

	public Autor(Long id, String nome, String biografia, String email, LocalDateTime dataCadastro) {
		this.id = id;
		setNome(nome);
		setBiografia(biografia);
		setEmail(email);
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome não pode estar vazio");
		}
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email não pode estar vazio");
		} else if (!email.matches("^([\\w][\\-]?\\.?)+@(([\\w][\\-]?)+\\.)+([A-Za-z]{2,4})+$")) {
			throw new IllegalArgumentException("Email inválido");
		}
		this.email = email;
	}

	public String getBiografia() {
		return biografia;
	}

	private void setBiografia(String biografia) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Biografia não pode estar vazia");
		}
		this.biografia = biografia;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", dataCadastro=" + dataCadastro + "]";
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
