CREATE DATABASE bd_livraria;

USE bd_livraria;

CREATE TABLE livro (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(50) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn INTEGER NOT NULL UNIQUE,
    preco DOUBLE(5 , 2 ) NOT NULL,
    estoque INTEGER NOT NULL,
    lancamento DATE NOT NULL
);