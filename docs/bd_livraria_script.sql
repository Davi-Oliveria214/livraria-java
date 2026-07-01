--create database bd_livraria;

create type tipo_generos as enum (
'indefinido',
'acao',
'romance',
'aventura',
'terror',
'comedia',
'drama',
'misterio');

create table generos(
id integer generated always as identity unique,
nome tipo_generos unique 
);

create table tb_livro (
id bigint generated always as identity unique,
titulo varchar(255),
autor varchar(150),
preco decimal(10, 2),
isbn varchar(15) unique,
estoque integer,
sinopse varchar(350),
genero integer default 1,
foreign key (genero) references generos(id),
unique(titulo, autor)
);

insert
	into
	generos(nome)
values
('indefinido'),
('acao'),
('romance'),
('aventura'),
('terror'),
('comedia'),
('drama'),
('misterio');

insert
	into
	tb_livro(titulo, autor, preco, isbn, estoque, sinopse, genero)
values 
('Livro1',
'Autor1',
50.55,
'534789',
200,
'Apenas um livro',
2),
('Livro2',
'Autor1',
95.55,
'934282',
350,
'Apenas um livro',
4),
('Livro1',
'Autor2',
75.55,
'521389',
155,
'Apenas um livro',
5),
('Livro2',
'Autor2',
97.55,
'544829',
200,
'Apenas um livro',
3),
('Livro1',
'Autor3',
125.55,
'3474723',
200,
'Apenas um livro',
1);