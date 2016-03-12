package br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses;

/**
 * Created by root on 08/03/16.
 */
public class Telefone {

    private int operadora;
    private String numero;

    public Telefone(int operadora, String numero) {
        this.operadora = operadora;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getOperadora() {
        return operadora;
    }

    public void setOperadora(int operadora) {
        this.operadora = operadora;
    }
}
