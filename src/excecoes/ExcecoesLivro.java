package excecoes;

public class ExcecoesLivro extends RuntimeException {
    public ExcecoesLivro(String message) {
        super(message);
    }

    public ExcecoesLivro(String message, Throwable causa){
        super(message, causa);
    }
}