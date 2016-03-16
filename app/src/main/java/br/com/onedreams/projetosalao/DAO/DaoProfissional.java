package br.com.onedreams.projetosalao.DAO;

import android.app.LocalActivityManager;

import java.util.ArrayList;
import java.util.List;

import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Localizacao;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Profissional;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Telefone;

/**
 * Created by root on 14/03/16.
 */
public class DaoProfissional {

    private List<Profissional> profissionalList;

    public DaoProfissional() {
        profissionalList = new ArrayList<>();
        populatelist();
    }

    private void populatelist() {

        List<Telefone> telefoneList = new ArrayList<>();
        telefoneList.add(new Telefone(31,""));
        Profissional p = new Profissional("Joao", new Localizacao("Brazil", "Bahia", "Salvador", "nova p", "sem rua", "293", "", -5.820312, -35.201584), 19, telefoneList);

        profissionalList.add( p );

    }
}
