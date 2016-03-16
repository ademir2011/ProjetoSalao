package br.com.onedreams.projetosalao.Classes;

import java.io.Serializable;

/**
 * Created by root on 13/03/16.
 */
public class Filtro implements Serializable {

    private boolean masculino;
    private boolean feminino;
    private String cidade;
    private double latitude;
    private double longitude;

    public Filtro(){}

    public Filtro(boolean masculino, boolean feminino, String cidade, double latitude, double longitude) {
        this.masculino = masculino;
        this.feminino = feminino;
        this.cidade = cidade;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
