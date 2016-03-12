package br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08/03/16.
 */
public class Profissional {

    private String nome;
    private int idade;
    private List<Telefone> telefoneList;
    private Localizacao localizacao;

    public Profissional(String nome, Localizacao localizacao, int idade, List<Telefone> telefoneList) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.idade = idade;
        this.telefoneList = new ArrayList<>(telefoneList);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }
}
