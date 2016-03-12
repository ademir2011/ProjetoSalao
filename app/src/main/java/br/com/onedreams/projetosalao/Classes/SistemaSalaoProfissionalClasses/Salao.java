package br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08/03/16.
 */
public class Salao {

    private String name;
    private String cnpj;
    private Avaliacao avaliacao;
    private List<Telefone> telefoneList;
    private Localizacao localizacao;
    private List<Profissional> profissionalList;

    public Salao(String name, String cnpj, Avaliacao avaliacao, List<Telefone> telefoneList, Localizacao localizacao, List<Profissional> profissionalList) {
        this.name = name;
        this.cnpj = cnpj;
        this.avaliacao = avaliacao;
        this.telefoneList = new ArrayList<>(telefoneList);
        this.localizacao = localizacao;
        this.profissionalList = new ArrayList<>(profissionalList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<Profissional> getProfissionalList() {
        return profissionalList;
    }

    public void setProfissionalList(List<Profissional> profissionalList) {
        this.profissionalList = profissionalList;
    }
}
