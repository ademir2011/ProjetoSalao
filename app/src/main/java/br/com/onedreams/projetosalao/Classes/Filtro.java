package br.com.onedreams.projetosalao.Classes;

import java.io.Serializable;

/**
 * Created by root on 13/03/16.
 */
public class Filtro implements Serializable {

    private boolean masculino;
    private boolean feminino;
    private String estado;

    public Filtro(){}

    public Filtro(boolean masculino, boolean feminino, String estado) {
        this.masculino = masculino;
        this.feminino = feminino;
        this.estado = estado;
    }

    public boolean isMasculino() {
        return masculino;
    }

    public void setMasculino(boolean masculino) {
        this.masculino = masculino;
    }

    public boolean isFeminino() {
        return feminino;
    }

    public void setFeminino(boolean feminino) {
        this.feminino = feminino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
