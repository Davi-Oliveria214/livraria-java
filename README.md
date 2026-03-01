# 📚 Projeto Livraria - Persistência com MySQL & JDBC

Este é um projeto de **estoque de livraria**, criado para consolidar conceitos avançados de **Programação Orientada a Objetos (POO)**, padrões de projetos e organização de sistemas em camadas.

Originalmente projetado para operar em memória, o sistema agora conta com persistência real em banco de dados **MySQL**, utilizando as melhores práticas de segurança e performance.

---

## 🏛 Arquitetura do Sistema

O projeto adota uma **Arquitetura em Camadas (N-Tier Architecture)**. Essa escolha permite que o sistema seja modular e escalável. Graças ao baixo acoplamento, a transição da lógica de `ArrayList` para **MySQL** foi realizada sem impactar as regras de negócio ou a interface do usuário.



### Divisão de Responsabilidades:

1. **Camada de Apresentação (`cli`)**: Representa a interface com o usuário no terminal. Captura entradas e exibe resultados ou mensagens de erro.
2. **Camada de Controle (`controller`)**: Atua como mediadora, recebendo chamadas da interface e direcionando-as para os serviços correspondentes.
3. **Camada de Serviço/Negócio (`services`)**: O "cérebro" do sistema. Contém todas as regras de validação e lógica de negócio.
4. **Camada de Persistência (`repository`)**: Gerencia a comunicação direta com o banco de dados via JDBC, executando comandos SQL otimizados.
5. **Camada de Conexão (`connection`)**: Gerencia a abertura de conexões com o MySQL utilizando o padrão Singleton e variáveis de ambiente.
6. **Domínio (`entity`)**: Contém as classes fundamentais (`LivroEntity`) que representam os objetos do mundo real.
7. **Tratamento de Erros (`excecoes`)**: Exceções personalizadas para garantir que falhas técnicas do banco não cheguem "cruas" ao usuário final.

---

## 🚀 Funcionalidades (CRUD Otimizado)

- **Create**: Adição de novos livros utilizando `PreparedStatement` para segurança contra SQL Injection.
- **Read**:
    - Busca inteligente por Título, Autor, ISBN ou Preço.
    - Listagem completa de todos os registros.
    - Verificação ultra-rápida de banco vazio utilizando `SELECT EXISTS`.
- **Update**: Atualização completa de dados cadastrados no banco.
- **Delete**: Remoção segura de registros através do ID/ISBN.

---

## 🗂 Estrutura de Pacotes e Organização

O projeto está organizado para garantir o baixo acoplamento:

- **`cli`**: Interface de linha de comando para interação com o usuário.
- **`connection`**: Lógica de conexão JDBC e leitura de variáveis via `.env`.
- **`controller`**: Gerencia o fluxo entre a CLI e o Service.
- **`entity`**: Classe `LivroEntity` com os atributos e estrutura do dado.
- **`excecoes`**: Exceções personalizadas (`ExcecoesLivro`, `BuscaLivros`) para tratamento centralizado.
- **`interfaces`**: Contém a `LivroInterface`, que define o contrato de métodos do sistema.
- **`repository`**: Implementação das queries SQL e manipulação de `ResultSet`.
- **`services`**: Implementação da interface, contendo validações e regras de negócio.

---

## 🛠 Tecnologias Utilizadas

- **Java** (Versão 17+)
- **Maven**: Gerenciamento de dependências (MySQL Connector, Dotenv).
- **MySQL**: Banco de dados relacional para persistência.
- **Java-dotenv**: Proteção de credenciais de acesso ao banco via arquivo `.env`.
- **Scanner / DateTimeFormatter**: Interação e formatação de datas.

---

## 📌 Próximos Passos

- [x] Implementar persistência com **MySQL** (Substituindo a lógica de ArrayList no Repository).
- [ ] Migrar interface para **Java Swing** (Adicionando uma nova camada de View Gráfica).
- [ ] Criar testes automatizados para a camada de Service.

---

## 👨‍💻 Davi de Jesus

Projeto desenvolvido para estudos de arquitetura e boas práticas de desenvolvimento Java.
Aberto a melhorias e sugestões!