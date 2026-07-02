--create database bd_livraria;

create type tipo_generos as enum (
'indefinido',
'acao',
'romance',
'aventura',
'terror',
'comedia',
'drama',
'misterio',
'suspense');

create table generos(
                        codigo varchar(5) primary key,
                        nome tipo_generos unique
);

insert
into
    generos(codigo, nome)
values
    ('ptI10',
     'indefinido'),
    ('ptA04',
     'acao'),
    ('ptR07',
     'romance'),
    ('ptA08',
     'aventura'),
    ('ptT06',
     'terror'),
    ('ptC07',
     'comedia'),
    ('ptD05',
     'drama'),
    ('ptM08',
     'misterio'),
    ('ptS08',
     'suspense');

create table tb_livros (
                           id bigint generated always as identity unique,
                           titulo varchar(255),
                           autor varchar(150),
                           preco decimal(10, 2),
                           isbn varchar(15) unique,
                           estoque integer,
                           sinopse varchar(350),
                           genero varchar(5) default 'ptI10',
                           lancamento timestamp,
                           criado_em timestamptz default now(),
                           foreign key (genero) references generos(codigo),
                           unique(titulo, autor)
);

insert
into
    tb_livros(titulo, autor, preco, isbn, estoque, sinopse, genero)
values
    ('Livro1',
     'Autor1',
     50.55,
     '534789',
     200,
     'Apenas um livro',
     'ptA04'),
    ('Livro2',
     'Autor1',
     95.55,
     '934282',
     350,
     'Apenas um livro',
     'ptA08'),
    ('Livro1',
     'Autor2',
     75.55,
     '521389',
     155,
     'Apenas um livro',
     'ptT06'),
    ('Livro2',
     'Autor2',
     97.55,
     '544829',
     200,
     'Apenas um livro',
     'ptR07'),
    ('Livro1',
     'Autor3',
     125.55,
     '3474723',
     200,
     'Apenas um livro',
     'ptI10');