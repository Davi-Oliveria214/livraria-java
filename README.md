# 📚 Projeto Livraria - API REST com Spring Boot & PostgreSQL

Este é um projeto de **gerenciamento de livros**, desenvolvido como parte de um processo de aprendizado e consolidação de conceitos de **Arquitetura em Camadas**, **APIs RESTful** e persistência de dados.

Originalmente uma aplicação Java simples, o sistema evoluiu para uma API robusta utilizando **Spring Boot**, com persistência em banco de dados **PostgreSQL** via JDBC, seguindo padrões profissionais de desenvolvimento para portfólios técnicos.

---

## 🚀 Funcionalidades

A API disponibiliza diversos endpoints para o gerenciamento do acervo:

- **Cadastro de Livros:** Adição de novos livros ao acervo com validação de unicidade (ISBN e combinação de Título + Autor).

- **Listagem Completa:** Recuperação de todos os livros cadastrados.

- **Buscas Específicas:**
    - Por ID.
    - Por Filtro Genérico: busca por título, autor, ISBN, preço ou ano de lançamento.
    - Histórico de livros (ordenado por data de criação).

- **Atualizações Parciais (PATCH):** Modificação individual de atributos do livro (Título, Autor, ISBN, Preço, Estoque e Data de Lançamento).

- **Remoção:** Exclusão de livros do sistema pelo ID.

- **Tratamento Global de Exceções:** Implementação de um manipulador global de erros (`RestExceptionHandler`) que captura todas as exceções da aplicação e as converte em respostas HTTP padronizadas, garantindo uma experiência consistente para o cliente da API.

---

## 🏗️ Arquitetura do Projeto

O projeto foi refatorado para utilizar o framework **Spring Boot** e segue uma arquitetura em camadas bem definida, garantindo separação de responsabilidades e facilidade de manutenção:

- **Controller (`com.livrariaJava.controller`):** Responsável por expor os endpoints REST, receber as requisições HTTP, delegar o processamento para a camada de serviço e retornar as respostas adequadas (ResponseEntity).

- **Service (`com.livrariaJava.services` e `com.livrariaJava.interfaces`):** Contém as regras de negócio da aplicação. Valida dados, verifica duplicidades e orquestra as chamadas ao repositório.

- **Repository (`com.livrariaJava.repository`):** Camada de acesso a dados. Executa as operações de persistência diretamente no banco de dados utilizando `NamedParameterJdbcTemplate` do Spring, o que melhora a legibilidade e segurança na execução de queries SQL com parâmetros nomeados.

- **Entity (`com.livrariaJava.entity`):** Representa o modelo de domínio. A entidade `Livro` foi atualizada para utilizar classes wrapper (`Long`, `Integer`, `Double`) em vez de tipos primitivos, permitindo melhor tratamento de valores nulos e integração com APIs.

- **Exceptions (`com.livrariaJava.exception`):** Classes customizadas (`LivroExcecao`, `BuscaVazia`) que estendem `RuntimeException` e carregam o status HTTP correspondente ao erro.

- **Infra (`com.livrariaJava.infra`):** Contém a infraestrutura de tratamento de erros, incluindo o `RestExceptionHandler` (anotado com `@RestControllerAdvice`) e o `BodyGlobalError`, que define o formato padrão das respostas de erro (timestamp, status, erro e mensagem).

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Framework principal)
- **Spring Web** (Criação da API REST)
- **Spring Boot DevTools** (Produtividade no desenvolvimento)
- **PostgreSQL** (Banco de Dados Relacional)
- **JDBC** (Persistência de dados nativa)
- **Spring Boot Starter JDBC** (Integração com JDBC e `NamedParameterJdbcTemplate`)
- **Spring Boot Configuration Properties** (Gerenciamento seguro de variáveis de ambiente)

---

## 📦 Estrutura da Entidade Livro

A entidade principal do sistema possui os seguintes atributos:

| Atributo | Tipo | Descrição |
| --- | --- | --- |
| `id` | `Long` | Identificador único do livro no banco de dados. |
| `titulo` | `String` | Título da obra. |
| `autor` | `String` | Nome do autor do livro. |
| `preco` | `Double` | Valor de venda do livro. |
| `isbn` | `Integer` | Código de identificação único do livro. |
| `estoque` | `Integer` | Quantidade de exemplares disponíveis. |
| `lancamento` | `LocalDate` | Data de lançamento da obra. |
| `criado_em` | `Timestamp` | Data e hora de criação do registro do livro. |

---

## 🔌 Endpoints da API

A API base está mapeada em `/livraria`. Abaixo estão os principais endpoints disponíveis:

### Criar e Listar

- `POST /livraria` - Adiciona um novo livro.
- `GET /livraria` - Retorna todos os livros.

### Buscas

- `GET /livraria/{id}` - Busca um livro pelo ID.
- `GET /livraria/historico` - Retorna o histórico de livros (ordenado por data de criação).
- `GET /livraria/filtro/{filtro}?valor={valor}` - Busca livros por título, autor, ISBN, preço ou ano de lançamento.

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
   spring.datasource.url=${DB_URL}
   spring.datasource.username=${DB_USER}
   spring.datasource.password=${DB_PASSWORD}
   ```

   Substitua `${DB_URL}`, `${DB_USER}` e `${DB_PASSWORD}` pelos valores correspondentes à sua configuração do PostgreSQL. Alternativamente, defina `DB_URL`, `DB_USER` e `DB_PASSWORD` diretamente no ambiente do sistema operacional.

3. A tabela `livro` deve ser criada no banco de dados com a estrutura compatível com a entidade. Um script de exemplo (`bd_livraria.sql`) pode ser encontrado no diretório `database/`.

### Executando a Aplicação

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute o comando Maven para iniciar a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080/livraria/`.

---

## 🐳 Docker e Containerização

O projeto conta com um `Dockerfile` configurado para facilitar o empacotamento e a execução da aplicação em qualquer ambiente. A containerização garante que a API rode de forma isolada e consistente, simplificando o processo de deploy e testes locais.

---

## ☁️ Deploy e Infraestrutura (Em Teste)

Como parte do aprendizado contínuo sobre infraestrutura moderna, o projeto está passando por testes de deploy na nuvem:

- **Render:** A aplicação está sendo testada para hospedagem na plataforma Render, explorando conceitos de PaaS (Platform as a Service) e deploy contínuo.
- **Neon:** A integração com o banco de dados Neon (PostgreSQL serverless) está sendo avaliada para entender os benefícios de bancos de dados gerenciados e escaláveis na nuvem.

---

## 🔮 Próximos Passos (Roadmap)

- [ ] Implementar Spring Data JPA para simplificar a camada de persistência.
- [ ] Adicionar documentação interativa com Swagger/OpenAPI.
- [ ] Adicionar paginação e ordenação nas listagens.
- [ ] Implementar segurança com Spring Security e JWT.
- [ ] Aprofundar os estudos em Docker, Render e Neon para otimizar o deploy e a infraestrutura da aplicação.

---

*Desenvolvido com dedicação para aprender e melhorar habilidades em Java e Spring Boot.*
