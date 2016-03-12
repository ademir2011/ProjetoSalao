package br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses;

/**
 * Created by root on 08/03/16.
 */
public class Servicos {

    private boolean manicure;
    private boolean pedicure;
    private boolean maquiador;
    private boolean cabeleireiro;
    private boolean esteticista;

    public Servicos(boolean manicure, boolean pedicure, boolean maquiador, boolean esteticista, boolean cabeleireiro) {
        this.manicure = manicure;
        this.pedicure = pedicure;
        this.maquiador = maquiador;
        this.esteticista = esteticista;
        this.cabeleireiro = cabeleireiro;
    }

    public boolean isManicure() {
        return manicure;
    }

    public void setManicure(boolean manicure) {
        this.manicure = manicure;
    }

    public boolean isPedicure() {
        return pedicure;
    }

    public void setPedicure(boolean pedicure) {
        this.pedicure = pedicure;
    }

    public boolean isCabeleireiro() {
        return cabeleireiro;
    }

    public void setCabeleireiro(boolean cabeleireiro) {
        this.cabeleireiro = cabeleireiro;
    }

    public boolean isMaquiador() {
        return maquiador;
    }

    public void setMaquiador(boolean maquiador) {
        this.maquiador = maquiador;
    }

    public boolean isEsteticista() {
        return esteticista;
    }

    public void setEsteticista(boolean esteticista) {
        this.esteticista = esteticista;
    }
}
