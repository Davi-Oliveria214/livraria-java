package views;

import controller.LivroController;
import excecoes.ExcecoesLivro;
import livro.Livro;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LivroView {
    private final LivroController livroControle;
    private final Scanner sc;
    private final DateTimeFormatter formato;
    private Livro livro;

    public LivroView() {
        this.livroControle = new LivroController();
        this.sc = new Scanner(System.in);
        this.formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public void start() {
        int opcao = -1;

        do {
            try {
                System.out.println("Escolha uma das opções \n 1-Adicionar livro \n 2-Remover livro \n 3-Buscar livro \n 4-Alterar livro \n 5-Ver todos os livros \n 0-Sair");
                opcao = sc.nextInt();

                sc.nextLine();
                switch (opcao) {
                    case 1:
                        this.addLivro();
                        break;
                    case 2:
                        livroControle.verificar();
                        this.delLivro();
                        break;
                    case 3:
                        livroControle.verificar();
                        this.buscarLivro();
                        break;
                    case 4:
                        livroControle.verificar();
                        this.altLivro();
                        break;
                    case 5:
                        this.todosLivros();
                        break;
                    case 0:
                        System.out.println("Fechando sistema");
                        break;
                    default:
                        System.out.println("Nenhuma opção encontrada");
                }
            } catch (ExcecoesLivro e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException in) {
                sc.next();
                System.out.println("Digite apenas números");
            }
        } while (opcao != 0);
    }

    private void addLivro() {
        try {
            System.out.println("==Adicionando um novo livro==");

            System.out.println("Digite o titulo do livro");
            String titulo = sc.nextLine();

            System.out.println("Digite o autor do livro");
            String autor = sc.nextLine();

            System.out.println("Digite a ISBN do livro");
            int isbn = sc.nextInt();

            sc.nextLine();
            System.out.println("Digite o preço do livro");
            double preco = sc.nextDouble();

            System.out.println("Digite a quantidade em estoque do livro");
            int estoque = sc.nextInt();

            sc.nextLine();
            System.out.println("Digite a data de lançamento do livro (dia/mês/ano)");
            String lancamento = sc.nextLine();
            LocalDate data = LocalDate.parse(lancamento, formato);

            livroControle.addLivro(titulo, autor, preco, isbn, estoque, data);
        } catch (ExcecoesLivro e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException in) {
            sc.next();
            System.out.println("Digite apenas números na ISBN, preço e estoque");
        } catch (DateTimeException date) {
            System.out.println("Formato de data incorreto");
        }
    }

    private void delLivro() {
        int opcao = 0;
        try {
            System.out.println("==Deletar livro==");
            System.out.println("Digite a ISBN do livro");
            int isbn = sc.nextInt();

            livro = livroControle.buscarISBN(isbn);

            if (livro != null) {
                sc.nextLine();
                System.out.println("Deseja deletar o livro: " + livro.toString() + "\n 1-Sim \n 2-Não");
                opcao = sc.nextInt();
            }

            if (opcao == 1) {
                livroControle.delLivro(isbn);
                System.out.println("Livro deletado com sucesso!!!");
            }
        } catch (ExcecoesLivro e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException in) {
            sc.next();
            System.out.println("Digite apenas números na opção de pergunta");
        }
    }

    private void buscarLivro() {
        int opcao = -1;
        do {
            try {
                System.out.println("==Buscar livro==");
                System.out.println("Escolha a maneira que deseja procura o livro \n 1-Titulo \n 2-Autor \n 3-ISBN \n 4-Preço \n 0-Menu");
                opcao = sc.nextInt();

                sc.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o titulo do livro");
                        String titulo = sc.nextLine();

                        System.out.println(livroControle.buscarTitulo(titulo).toString());
                        break;
                    case 2:
                        System.out.println("Digite o nome do autor que deseja encontrar");
                        String autor = sc.nextLine();

                        System.out.println(livroControle.buscarAutor(autor).toString());
                        break;
                    case 3:
                        System.out.println("Digite a ISBN do livro que deseja encontrar");
                        int isbn = sc.nextInt();

                        System.out.println(livroControle.buscarISBN(isbn).toString());
                        break;
                    case 4:
                        System.out.println("Digite o preço que deseja encontrar");
                        double preco = sc.nextDouble();

                        System.out.println(livroControle.buscarPreco(preco).toString());
                        break;
                    case 0:
                        System.out.println("Voltando ao menu inicial");
                        break;
                    default:
                        System.out.println("Nenhuma opção encontrada");
                }
            } catch (ExcecoesLivro e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException in) {
                sc.next();
                System.out.println("Digite apenas números");
            }
        } while (opcao != 0);
    }

    private void altLivro() {
        int opcao = -1;
        do {
            try {
                System.out.println("==Alterar livro==");
                System.out.println("Digite a ISBN do livro que deseja alterar");
                int isbn = sc.nextInt();

                livro = livroControle.buscarISBN(isbn);

                System.out.println("\n" + livro.toString() + "\n");

                sc.nextLine();
                System.out.println("Escolha o que deseja alterar do livro \n 1-Titulo \n 2-Autor \n 3-Preço \n 4-Estoque \n 5-ISBN \n 0-Menu principal");
                opcao = sc.nextInt();

                sc.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o novo titulo");
                        String titulo = sc.nextLine();

                        livroControle.altTitulo(isbn, titulo);
                        System.out.println("Titulo alterado com sucesso");
                        break;
                    case 2:
                        System.out.println("Digite o novo autor");
                        String autor = sc.nextLine();

                        livroControle.altAutor(isbn, autor);
                        System.out.println("Autor alterado com sucesso");
                        break;
                    case 3:
                        System.out.println("Digite o novo preço");
                        double preco = sc.nextDouble();

                        livroControle.altPreco(isbn, preco);
                        System.out.println("Preço alterado com sucesso");
                        break;
                    case 4:
                        System.out.println("Digite o novo Estoque");
                        int estoque = sc.nextInt();

                        livroControle.altEstoque(isbn, estoque);
                        System.out.println("Estoque alterado com sucesso");
                        break;
                    case 5:
                        System.out.println("Digite a nova ISBN");
                        int isbnNova = sc.nextInt();

                        livroControle.altISBN(isbn, isbnNova);
                        System.out.println("ISBN alterado com sucesso");
                        break;
                    case 0:
                        System.out.println("Voltando ao menu inicial");
                        break;
                    default:
                        System.out.println("Nenhuma opção encontrada");
                }

                System.out.println("Novas informações: " + livro.toString());

                System.out.println("Deseja fazer alguma outra alteração?");
                opcao = sc.nextInt();
            } catch (ExcecoesLivro e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException in) {
                sc.next();
                System.out.println("Digite apenas números");
            }
        } while (opcao != 0);
    }

    private void todosLivros() {
        try {
            System.out.println(livroControle.getLivros().toString());
        } catch (ExcecoesLivro e) {
            System.out.println(e.getMessage());
        }
    }
}