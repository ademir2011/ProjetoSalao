package br.com.onedreams.projetosalao.Classes;

/**
 * Created by root on 09/03/16.
 * Classe responsável para identificar quais são os componentes que serão exibidos na tela
 */
public class CardFeed {

    private String mNomeDoComentarista;
    private String mMensagemDoComentarista;

    public CardFeed(String mNomeDoComentarista, String mMensagemDoComentarista) {
        this.mNomeDoComentarista = mNomeDoComentarista;
        this.mMensagemDoComentarista = mMensagemDoComentarista;
    }

    public String getmNomeDoComentarista() {
        return mNomeDoComentarista;
    }

    public void setmNomeDoComentarista(String mNomeDoComentarista) {
        this.mNomeDoComentarista = mNomeDoComentarista;
    }

    public String getmMensagemDoComentarista() {
        return mMensagemDoComentarista;
    }

    public void setmMensagemDoComentarista(String mMensagemDoComentarista) {
        this.mMensagemDoComentarista = mMensagemDoComentarista;
    }
}
