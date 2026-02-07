import excecoes.ExcecoesLivro;
import gerenciamento.LivroConfig;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LivroConfig lc = new LivroConfig();

        try {
            lc.addLivro("Teste", "Teste", 200, 1842, 19, LocalDate.now());
            System.out.println(lc.buscarLivro("Teste"));

            lc.delLivro(1842);
        } catch (ExcecoesLivro e) {
            System.out.println(e.getMessage());
        }
    }
}