# 📚 Projeto Livraria - API REST com Spring Boot & PostgreSQL

Este é um projeto de **gerenciamento de livros**, desenvolvido para consolidar conceitos de **Arquitetura em Camadas**, **APIs RESTful** e persistência de dados.

Originalmente projetado como uma aplicação simples, o sistema foi evoluído para uma API utilizando **Spring Boot**, com persistência real em banco de dados **PostgreSQL** via JDBC, seguindo padrões profissionais de desenvolvimento para portfólios técnicos.

---

## 🚀 Funcionalidades

A API disponibiliza diversos endpoints para o gerenciamento do acervo:

- **Cadastro de Livros:** Adição de novos livros ao acervo com validação de unicidade (ISBN e combinação de Título + Autor).
- **Listagem Completa:** Recuperação de todos os livros cadastrados.
- **Buscas Específicas:**
    - Por ID.
    - Por Título (busca parcial).
    - Por Autor (busca parcial).
    - Por ISBN.
    - Por faixa de Preço (com margem de 15% para mais ou para menos).
    - Histórico de livros (ordem de criação).
- **Atualizações Parciais (PATCH):** Modificação individual de atributos do livro (Título, Autor, ISBN, Preço, Estoque e Data de Lançamento).
- **Remoção:** Exclusão de livros do sistema pelo ID.
- **Tratamento de Exceções:** Respostas HTTP adequadas (ex: 404 Not Found, 409 Conflict, 400 Bad Request) para regras de negócio e validações.

---

## 🏗️ Arquitetura do Projeto

O projeto foi refatorado para utilizar o framework **Spring Boot** e segue uma arquitetura em camadas bem definida, garantindo separação de responsabilidades e facilidade de manutenção:

- **Controller (`com.livrariaJava.controller`):** Responsável por expor os endpoints REST, receber as requisições HTTP, delegar o processamento para a camada de serviço e retornar as respostas adequadas (ResponseEntity).
- **Service (`com.livrariaJava.services` e `com.livrariaJava.interfaces`):** Contém as regras de negócio da aplicação. Valida dados, verifica duplicidades e orquestra as chamadas ao repositório.
- **Repository (`com.livrariaJava.repository`):** Camada de acesso a dados. Executa as operações de persistência diretamente no banco de dados utilizando JDBC (Java Database Connectivity).
- **Entity (`com.livrariaJava.entity`):** Representa o modelo de domínio. A entidade `Livro` foi atualizada para utilizar classes wrapper (`Long`, `Integer`, `Double`) em vez de tipos primitivos, permitindo melhor tratamento de valores nulos e integração com APIs.
- **Connection (`com.livrariaJava.connection`):** Gerencia a conexão com o banco de dados PostgreSQL, obtendo as credenciais de forma segura utilizando uma classe de configuração (`AppConfig`) que lê de `application.properties` ou variáveis de ambiente do sistema.
- **Exceptions (`com.livrariaJava.exception`):** Classes customizadas para tratamento de erros específicos do domínio da aplicação.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Framework principal)
- **Spring Web** (Criação da API REST)
- **Spring Boot DevTools** (Produtividade no desenvolvimento)
- **PostgreSQL** (Banco de Dados Relacional)
- **JDBC** (Persistência de dados nativa)
- **Spring Boot Configuration Properties** (Gerenciamento seguro de variáveis de ambiente)

---

## 📦 Estrutura da Entidade Livro

A entidade principal do sistema possui os seguintes atributos:

| Atributo | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | `Long` | Identificador único do livro no banco de dados. |
| `titulo` | `String` | Título da obra. |
| `autor` | `String` | Nome do autor do livro. |
| `preco` | `Double` | Valor de venda do livro. |
| `isbn` | `Integer` | Código de identificação único do livro. |
| `estoque` | `Integer` | Quantidade de exemplares disponíveis. |
| `lancamento` | `LocalDate` | Data de lançamento da obra. |

---

## 🔌 Endpoints da API

A API base está mapeada em `/livraria`. Abaixo estão os principais endpoints disponíveis:

### Criar e Listar
- `POST /livraria/` - Adiciona um novo livro.
- `GET /livraria/` - Retorna todos os livros.

### Buscas
- `GET /livraria/{id}` - Busca um livro pelo ID.
- `GET /livraria/historico` - Retorna o histórico de livros (ordenado por data de criação).
- `GET /livraria/titulo?titulo={titulo}` - Busca livros por título.
- `GET /livraria/autor?autor={autor}` - Busca livros por autor.
- `GET /livraria/isbn?isbn={isbn}` - Busca livros por ISBN.
- `GET /livraria/preco?preco={preco}` - Busca livros por faixa de preço.

### Atualizações Parciais
- `PATCH /livraria/{id}/titulo?novoTitulo={novoTitulo}` - Atualiza o título.
- `PATCH /livraria/{id}/autor?novoAutor={novoAutor}` - Atualiza o autor.
- `PATCH /livraria/{id}/isbn?novaIsbn={novaIsbn}` - Atualiza o ISBN.
- `PATCH /livraria/{id}/preco?novoPreco={novoPreco}` - Atualiza o preço.
- `PATCH /livraria/{id}/estoque?novoEstoque={novoEstoque}` - Atualiza o estoque.
- `PATCH /livraria/{id}/data?novaData={novaData}` - Atualiza a data de lançamento.

### Remoção
- `DELETE /livraria/{id}` - Remove um livro pelo ID.

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- JDK 17 ou superior instalado.
- Maven instalado.
- Servidor PostgreSQL rodando localmente ou em nuvem.

### Configuração do Banco de Dados
1. Crie um banco de dados no PostgreSQL.
2. Configure as variáveis de ambiente do banco de dados no arquivo `src/main/resources/application.properties` ou como variáveis de ambiente do sistema:
   ```properties
   livraria.datasource.db_url=${DB_URL}
   livraria.datasource.db_user=${DB_USER}
   livraria.datasource.db_password=${DB_PASSWORD}
   ```
   Substitua `${DB_URL}`, `${DB_USER}` e `${DB_PASSWORD}` pelos valores correspondentes à sua configuração do PostgreSQL. Alternativamente, defina `DB_URL`, `DB_USER` e `DB_PASSWORD` diretamente no ambiente do sistema operacional.
3. A tabela `livro` deve ser criada no banco de dados com a estrutura compatível com a entidade.

### Executando a Aplicação
1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute o comando Maven para iniciar a aplicação:
   ```bash
   mvn spring-boot:run
   ```
4. A API estará disponível em `http://localhost:8080/livraria/`.

---

## 🔮 Próximos Passos (Roadmap)

- [ ] Implementar Spring Data JPA para simplificar a camada de persistência.
- [ ] Adicionar documentação interativa com Swagger/OpenAPI.
- [ ] Adicionar paginação e ordenação nas listagens.
- [ ] Implementar segurança com Spring Security e JWT.

---
*Desenvolvido com dedicação para aprender melhorar habilidades em Java e Spring Boot.*