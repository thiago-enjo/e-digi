DROP TABLE autores, categorias, livros;

CREATE TABLE autores (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
    biografia TEXT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
	data_cadastro DATETIME NOT NULL DEFAULT NOW()
) ENGINE=InnoDB;

CREATE TABLE categorias (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL UNIQUE,
	data_cadastro DATETIME NOT NULL DEFAULT NOW()
) ENGINE=InnoDB;

CREATE TABLE livros (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    resumo TEXT NOT NULL,
    sumario TEXT NOT NULL,
    numero_de_paginas SMALLINT NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    autor_id INT NOT NULL,
    categoria_id INT NOT NULL,
    edicao SMALLINT NOT NULL,
    preco DECIMAL(6,2) NOT NULL,

    FOREIGN KEY (autor_id) REFERENCES autores(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
) ENGINE=InnoDB;