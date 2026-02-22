# 📚 Projeto Livraria - Treino de POO, CRUD e MySQL

Este é um projeto de **estoque de livraria**, criado para consolidar conceitos avançados de **Programação Orientada a Objetos (POO)**, padrões de projetos e organização de sistemas em camadas.

Atualmente, os dados são armazenados em memória local via `ArrayList`. Em versões futuras, será integrada a persistência com banco de dados MySQL.

---

## 🏛 Arquitetura do Sistema

O projeto adota uma **Arquitetura em Camadas (N-Tier Architecture)**. Essa escolha permite que o sistema seja modular, facilitando a troca de uma interface de terminal (CLI) por uma gráfica (Swing) ou a troca da lista na memória por um banco de dados, sem afetar as regras de negócio.



### Divisão de Responsabilidades:

1. **Camada de Apresentação (`cli`)**: Representa a interface com o usuário. Captura entradas e exibe resultados/erros.
2. **Camada de Controle (`controller`)**: Atua como mediadora, recebendo chamadas da interface e direcionando-as para os serviços correspondentes.
3. **Camada de Serviço/Negócio (`services`)**: O "cérebro" do sistema. Contém todas as regras de validação e lógica de negócio.
4. **Camada de Persistência (`repository`)**: Única camada que manipula a fonte de dados (atualmente a lista de objetos).
5. **Domínio (`entity`)**: Contém as classes fundamentais que representam os objetos do mundo real (Livro).

---

## 🚀 Funcionalidades (CRUD)

- **Create**: Adicionar novos livros com validação de ISBN e duplicidade.
- **Read**: Buscar livros por Título, Autor, ISBN ou Preço; Listagem completa.
- **Update**: Alterar informações específicas de livros existentes.
- **Delete**: Remover livros do sistema via ISBN.

---

## 🗂 Estrutura de Pacotes

O projeto está organizado para garantir o baixo acoplamento:

- **`entity`**: Classe `LivroEntity` com os atributos e estrutura do dado.
- **`interfaces`**: Contém a `LivroInterface`, que define o contrato de métodos do sistema.
- **`services`**: Implementação da interface, contendo os `if` de validação e regras.
- **`controller`**: Gerencia o fluxo entre a CLI e o Service.
- **`repository`**: Gerencia o armazenamento e busca bruta dos dados.
- **`cli`**: Interface de linha de comando para interação com o usuário.
- **`excecoes`**: Exceções personalizadas (`ExcecoesLivro`, `BuscaLivros`) para um tratamento de erro centralizado.

---

## 🛠 Tecnologias Utilizadas

- **Java** (Versão 17+)
- **Scanner / DateTimeFormatter** (Interação e formatação de datas)
- **Injeção de Dependência Manual** (Para ligar as camadas no `Main`)

---

## 📌 Próximos Passos

- [ ] Implementar persistência com **MySQL** (Substituindo a lógica do Repository).
- [ ] Migrar interface para **Java Swing** (Adicionando uma nova View).
- [ ] Criar testes automatizados para a camada de Service.

---

## 👨‍💻 Davi de Jesus

Projeto desenvolvido para estudos de arquitetura e boas práticas de desenvolvimento Java.
Aberto a melhorias e sugestões!