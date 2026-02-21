package repository;

import entity.LivroEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LivroRepository {
    private final List<LivroEntity> listaLivroEntities;

    public LivroRepository() {
        this.listaLivroEntities = new ArrayList<>();
    }

    public void newLivro(LivroEntity livroEntity) {
        listaLivroEntities.add(livroEntity);
    }

    public void delLivro(LivroEntity livroEntity) {
        listaLivroEntities.remove(livroEntity);
    }

    public void updateLivro(LivroEntity livroEntity) {
        for (int i = 0; i < listaLivroEntities.size(); i++) {
            if (Objects.equals(listaLivroEntities.get(i).getId(), livroEntity.getId())) {
                listaLivroEntities.set(i, livroEntity);
            }
        }
    }

    public List<LivroEntity> buscarTitulo(String titulo) {
        List<LivroEntity> livroEntities = new ArrayList<>();

        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getTitulo().equals(titulo)) {
                livroEntities.add(livroEntity);
            }
        }

        return livroEntities;
    }

    public List<LivroEntity> buscarAutor(String autor) {
        List<LivroEntity> livroEntities = new ArrayList<>();

        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getAutor().equals(autor)) {
                livroEntities.add(livroEntity);
            }
        }

        return livroEntities;
    }

    public LivroEntity buscarISBN(int isbn) {
        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getIsbn() == isbn) {
                return livroEntity;
            }
        }

        return null;
    }

    public List<LivroEntity> buscarPreco(double preco) {
        List<LivroEntity> livroEntities = new ArrayList<>();

        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getPreco() == preco) {
                livroEntities.add(livroEntity);
            }
        }

        return livroEntities;
    }

    public List<LivroEntity> todosLivros() {
        return listaLivroEntities;
    }
}