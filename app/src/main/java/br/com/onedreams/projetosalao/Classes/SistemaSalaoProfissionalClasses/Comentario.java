package br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses;

/**
 * Created by root on 08/03/16.
 */
public class Comentario {

    private String nomeDoComentarista;
    private String emailDoComentarista;
    private String comentario;

    public Comentario(String nomeDoComentarista, String comentario, String emailDoComentarista) {
        this.nomeDoComentarista = nomeDoComentarista;
        this.comentario = comentario;
        this.emailDoComentarista = emailDoComentarista;
    }

    public String getNomeDoComentarista() {
        return nomeDoComentarista;
    }

    public void setNomeDoComentarista(String nomeDoComentarista) {
        this.nomeDoComentarista = nomeDoComentarista;
    }

    public String getEmailDoComentarista() {
        return emailDoComentarista;
    }

    public void setEmailDoComentarista(String emailDoComentarista) {
        this.emailDoComentarista = emailDoComentarista;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
