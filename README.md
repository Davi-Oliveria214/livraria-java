# 📚 Projeto Livraria - Treino de POO, CRUD e MySQL

Este é um projeto de **estoque de livraria**, criado para treinar conceitos de **Programação Orientada a Objetos (POO)
**, **CRUD** e futuramente integração com **MySQL**.  
Atualmente, os dados são armazenados apenas em memória local (se fechar a aplicação, tudo é perdido). Em versões
futuras, será integrado a um banco de dados.

---

## 🚀 Funcionalidades

- Adicionar livros
- Remover livros
- Alterar informações (título, autor, preço, estoque, ISBN)
- Buscar livros
- Listar todos os livros cadastrados

---

## 🗂 Estrutura do Projeto

O projeto está dividido em **5 pacotes principais**:

### 1. `livro`

Contém a classe **Livro**, que define os atributos básicos:

- `id`
- `ISBN`
- `titulo`
- `autor`
- `preco`
- `estoque`
- `dataLancamento`

---

### 2. `services`

Contém a **LivroInterface**, responsável por definir os métodos que o controlador deve implementar:

- addLivro
- delLivro
- buscarTitulo
- buscarAutor
- buscarISBN
- buscarPreco
- altTitulo
- altAutor
- altPreco
- altEstoque
- altISBN

---

### 3. `controller`

Contém a classe **LivroController**, que implementa a `LivroInterface` e define a **lógica** dos métodos.

---

### 4. `views`

Contém a classe **LivroView**, responsável pela interação com o usuário.  
Atualmente funciona com `System.out.println()` e `Scanner`, mas será migrada para **Java Swing** em versões futuras.

---

### 5. `excecoes`

Contém as classes:

- **BuscarLivros**
- **ExcecoesLivro**

Essas classes organizam e tratam exceções relacionadas ao sistema.

---

## 🛠 Tecnologias utilizadas

- **Java** (POO, CRUD)
- **Scanner** (entrada de dados)
- **System.out.println** (saída de dados)
- Futuramente: **MySQL** + **Java Swing**

---

## 🎯 Objetivo

Esse projeto não é comercial, mas sim um **treino prático** para consolidar conceitos de:

- Programação Orientada a Objetos
- Estruturação de pacotes
- Implementação de CRUD
- Tratamento de exceções
- Integração com banco de dados

---

## 📌 Próximos passos

- [ ] Implementar persistência com **MySQL**
- [ ] Migrar interface para **Java Swing**
- [ ] Melhorar tratamento de exceções
- [ ] Criar testes automatizados

---

## 👨‍💻 Davi de Jesus

Projeto desenvolvido para estudos de **POO, CRUD e MySQL**.  
Aberto a melhorias e sugestões!
