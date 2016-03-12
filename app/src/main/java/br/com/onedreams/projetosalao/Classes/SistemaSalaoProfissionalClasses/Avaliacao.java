package br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08/03/16.
 */
public class Avaliacao {

    private int curtidas;
    private int descurtidas;
    private int estrelas;
    private List<Comentario> comentarioList;

    public Avaliacao(int curtidas, int descurtidas, int estrelas, List<Comentario> comentarioList) {
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.estrelas = estrelas;
        this.comentarioList = new ArrayList<>(comentarioList);
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public int getDescurtidas() {
        return descurtidas;
    }

    public void setDescurtidas(int descurtidas) {
        this.descurtidas = descurtidas;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }
}
