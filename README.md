# 📚 Projeto Livraria - API REST com Spring Boot & PostgreSQL

Este é um projeto de **gerenciamento de livros**, desenvolvido como parte de um processo de aprendizado e consolidação de conceitos de **Arquitetura em Camadas**, **APIs RESTful** e persistência de dados.

Originalmente uma aplicação Java simples, o sistema evoluiu para uma API robusta utilizando **Spring Boot**, com persistência em banco de dados **PostgreSQL** via JDBC, seguindo padrões profissionais de desenvolvimento para portfólios técnicos.

---

## 🚀 Funcionalidades

A API disponibiliza diversos endpoints para o gerenciamento dos livros:

- **Cadastro de Livros:** Adição de novos livros com validação de unicidade (ISBN e combinação de Título + Autor).

- **Listagem Completa:** Recuperação de todos os livros cadastrados.

- **Buscas Específicas:**
    - Por ID.
    - Por Filtro Genérico: busca por título, autor, ISBN, preço, ano de lançamento ou gênero.
    - Histórico de livros (ordenado por data de criação, sendo possível alterar a ordem).

- **Atualizações Parciais (PATCH):** Modificação individual de atributos do livro (Título, Autor, ISBN, Preço, Estoque, Data de Lançamento ou Gênero).

- **Remoção:** Exclusão de livros do sistema pelo ID.

- **Tratamento Global de Exceções:** Implementação de um manipulador global de erros (`RestExceptionHandler`) que captura todas as exceções da aplicação e as converte em respostas HTTP padronizadas, garantindo uma experiência consistente para o cliente da API.

---

## 🏗️ Arquitetura do Projeto

O projeto utiliza o framework **Spring Boot** e segue uma arquitetura em camadas bem definida, garantindo separação de responsabilidades e facilidade de manutenção:

- **Controller (`com.livrariaJava.controller`):** Camada responsável por definir os endpoints REST da API e receber as requisições HTTP, passando o processamento para a camada de serviço e retornando as respostas adequadas por meio do `ResponseEntity`.

- **Service (`com.livrariaJava.services` e `com.livrariaJava.interfaces`):** Contém as regras de negócio da aplicação. Valida dados, verifica duplicidades e orquestra as chamadas ao repositório.

- **Repository (`com.livrariaJava.repository`):** Responsável pelo acesso de dados. Executa as operações de persistência diretamente no banco de dados utilizando `NamedParameterJdbcTemplate` do Spring, garantindo legibilidade e segurança na execução de queries SQL com parâmetros nomeados.

- **Entity & Enums (`com.livrariaJava.entity` e `com.livrariaJava.entity.enums`):** Representam os modelos de domínio e as constantes do sistema.
    - **Classe `Livro`:** Responsável por mapear os campos utilizando classes wrapper (`Long`, `Integer`, `Double`), permitindo melhor tratamento de valores nulos.
    - **Classe `Genero` e `GenerosEnum`:** Estruturas criadas para padronizar, validar e categorizar os tipos de obras literárias aceitas pelo sistema de forma tipada.

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

| Atributo       | Tipo        | Descrição                                       |
|----------------|-------------|-------------------------------------------------|
| `id`           | `Long`      | Identificador único do livro no banco de dados. |
| `titulo`       | `String`    | Título da obra.                                 |
| `autor`        | `String`    | Nome do autor do livro.                         |
| `preco`        | `Double`    | Valor de venda do livro.                        |
| `isbn`         | `String`    | Código de identificação único do livro.         |
| `sinopse`      | `String`    | Sinopse do livro.                               |
| `genero`       | `String`    | Gênero do livro                                 |
| `estoque`      | `Integer`   | Quantidade de exemplares disponíveis.           |
| `lancamento`   | `LocalDate` | Data de lançamento da obra.                     |
| `criado_em`    | `Timestamp` | Data e hora de criação do registro do livro.    |

---

## 🔌 Endpoints da API

A API base está mapeada em `/livraria`. Abaixo estão os principais endpoints disponíveis:

### Criar e Listar

- `POST /livraria` - Adiciona um novo livro.
- `GET /livraria` - Retorna os livros cadastrados de forma paginada.
    - **Parâmetros opcionais de URL (Query Params):**
        - `limit` (padrão: `10`): Define a quantidade máxima de registros retornados (ideal para controle de rolagem/scroll).
        - `off` (padrão: `0`): Define o deslocamento (offset) inicial da busca na base de dados.

### Buscas

- `GET /livraria/{id}` - Busca um livro pelo ID.
- `GET /livraria/historico` - Retorna o histórico de criação dos livros com paginação e ordenação flexível.
    - **Parâmetros opcionais de URL (Query Params):**
        - `ordem` (padrão: `true`): Se `true`, ordena de forma decrescente (registros mais recentes primeiro); se `false`, ordena de forma crescente (mais antigos primeiro).
        - `limit` (padrão: `5`): Quantidade de registros por página de histórico.
        - `off` (padrão: `0`): Deslocamento inicial da paginação do histórico.
- `GET /livraria/filtro/{filtro}?valor={valor}` - Busca livros por título, autor, ISBN, preço, ano de lançamento ou gênero.

### Atualizações Parciais

- `PATCH /livraria/{id}/{tabela}?valor={novoValor}` - Atualiza a tabela escolhida.

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

4. A API estará disponível em `http://localhost:8080/livraria`.

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
- [x] Adicionar paginação e ordenação nas listagens.
- [ ] Implementar segurança com Spring Security e JWT.
- [ ] Aprofundar os estudos em Docker, Render e Neon para otimizar o deploy e a infraestrutura da aplicação.

---

*Desenvolvido com dedicação para aprender e melhorar habilidades em Java e Spring Boot.*
