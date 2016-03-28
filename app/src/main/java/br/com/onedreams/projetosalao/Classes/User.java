package br.com.onedreams.projetosalao.Classes;

import android.content.Context;

import com.firebase.client.Firebase;

import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Postagem;

/**
 * Created by root on 21/03/16.
 */
public class User {

    public static String TOKEN = "br.com.onedreams.projetosalao.Classes.User.TOKEN";

    private String id;
    private String email;
    private String senha;
    private Postagem postagem;

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void saveDB(){
        Firebase firebase = new Firebase("https://bookbeauty.firebaseio.com/");
        firebase = firebase.child("user").child(getId());

        setSenha(null);
        setId(null);

        firebase.setValue(this);
    }

    public void saveTokenSP(Context context, String token ){
        LibraryClass.saveSP( context, TOKEN, token );
    }

    public String getTokenSP(Context context ){
        String token = LibraryClass.getSP( context, TOKEN );
        return( token );
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }
}
