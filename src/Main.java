import cli.LivroCLI;
import connection.LivroConnection;
import controller.LivroController;
import repository.LivroRepository;
import services.LivroService;

public class Main {
    public static void main(String[] args) {
//        LivroController livroController = new LivroController(new LivroService(new LivroRepository()));
//        LivroCLI view = new LivroCLI(livroController);
//
//        view.start();

        LivroConnection.con();
    }
}