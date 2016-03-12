package br.com.onedreams.projetosalao.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Avaliacao;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Comentario;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Localizacao;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Profissional;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Salao;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Telefone;

/**
 * Created by root on 08/03/16.
 */
public class DaoSalao {

    private List<Salao> salaoList;

    public DaoSalao() {

        salaoList = new ArrayList<>();
        populateSaloes();

    }

    /**
     * Responsavel por alimentar o banco, adiciona os saloes
     */

    private void populateSaloes() {

        List<Comentario> comentarioList = new ArrayList<>();
        comentarioList.add(new Comentario("","",""));

        List<Telefone> telefoneList = new ArrayList<>();
        telefoneList.add(new Telefone(84,"99999-9999"));

        List<Profissional> profissionalList = new ArrayList<>();
        profissionalList.add(   new Profissional("Joao",
                                new Localizacao("brasil","RN","parnamirim","nova parnamirim","robert122reire","21","ap42301s",-5.109498,-35.214280),
                                12,
                                telefoneList));



        salaoList.add(new Salao("Salao cole kie",
                                "231.312.312/12312",
                                new Avaliacao(123,3213,5, comentarioList),
                                telefoneList,
                                new Localizacao("kkk","RN","natal","ponta negra","roberto freire","31","ap401s",-5.880498,-35.284280),
                                profissionalList));

        salaoList.add(new Salao("Salao cole kie2",
                "231.312.312/12312",
                new Avaliacao(123,3213,5, comentarioList),
                telefoneList,
                new Localizacao("kkk","RN","natal","ponta negra","roberto freire","31","ap401s",-5.890498,-35.283280),
                profissionalList));


    }

    public List<Salao> getSalaoList() {
        return salaoList;
    }

    public void setSalaoList(List<Salao> salaoList) {
        this.salaoList = salaoList;
    }
}
