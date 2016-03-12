package br.com.onedreams.projetosalao.Classes;

/**
 * Created by root on 09/03/16.
 * Classe responsável para identificar quais são os componentes que serão exibidos na tela
 */
public class CardFeed {

    private String urlImage;
    private int qtdLike;
    private int qtdComment;

    public CardFeed(String urlImage, int qtdLike, int qtdComment) {
        this.urlImage = urlImage;
        this.qtdLike = qtdLike;
        this.qtdComment = qtdComment;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getQtdComment() {
        return qtdComment;
    }

    public void setQtdComment(int qtdComment) {
        this.qtdComment = qtdComment;
    }

    public int getQtdLike() {
        return qtdLike;
    }

    public void setQtdLike(int qtdLike) {
        this.qtdLike = qtdLike;
    }
}
