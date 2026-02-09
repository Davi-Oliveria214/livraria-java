import excecoes.ExcecoesLivro;
import gerenciamento.LivroControle;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LivroControle lc = new LivroControle();

        try {
            lc.addLivro("Teste", "Teste", 200, 1945, 50, LocalDate.now());

            lc.altTitulo(1945, "Teste2");
            lc.altAutor(1945, "Teste2");
            lc.altPreco(1945, 300);
            lc.altEstoque(1945, 4000);
            lc.altISBN(1945, 1978);

            System.out.println(lc.buscarPreco(300));
            lc.delLivro(1978);
        } catch (ExcecoesLivro e) {
            System.out.println(e.getMessage());
        }

        System.out.println(lc.getLivros());
    }
}