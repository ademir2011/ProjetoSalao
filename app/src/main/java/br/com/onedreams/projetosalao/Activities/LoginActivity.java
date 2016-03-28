package br.com.onedreams.projetosalao.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import br.com.onedreams.projetosalao.Classes.LibraryClass;
import br.com.onedreams.projetosalao.Classes.User;
import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private Firebase firebase;

    @Bind(R.id.etEmailLogin) EditText etEmailLogin;
    @Bind(R.id.etSenhaLogin) EditText etSenhaLogin;
    @Bind(R.id.pbLogin) ProgressBar pbLogin;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        firebase = LibraryClass.getFirebase();

        //Verifica se o usuário já está cadastrado
        verifyUserLogged();

        //Deixar a tela full screen
        fullScreenMode();

    }

    /**
     * Faz com que a tela fique full screen até ser tocado
     */

    private void fullScreenMode() {
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * Botão de cadastrar. Caso clicado muda a activity para a de cadastro
     * @param v
     */

    @OnClick(R.id.btCadastrarLogin)
    public void clickBtCadastrarLogin(View v){
        startActivity(new Intent(LoginActivity.this, SingUpActivity.class));
    }

    /**
     * Botão de logar. Caso clicado abre o progress bar, iniciar o usuário pegando os valores digitados
     * e verificar o login
     * @param v
     */

    @OnClick(R.id.btLoginLogin)
    public void clickBtLoginLogin(View v){
        openProgressBar();

        initUser();

        verifyLogin();

    }

    /**
     * Inicia o usuário pegando dos edittexts o email e a senha
     */

    private void initUser() {
        user = new User();
        user.setEmail(etEmailLogin.getText().toString());
        user.setSenha(etSenhaLogin.getText().toString());
    }

    /**
     * Ativa a visualização do progressbar
     */

    protected void openProgressBar(){
        pbLogin.setVisibility( View.VISIBLE );
    }

    /**
     * Tira a visualização do progressbar
     */

    protected void closeProgressBar(){
        pbLogin.setVisibility( View.GONE );
    }

    /**
     * Verifica se o usuário já tinha logado. Primeiro faz uma verificação se o usuário já tinha autenticado.
     * Se sim, automaticamente a tela do main principal será iniciada. Caso não, é feita a verificação por
     * Token
     */

    private void verifyUserLogged(){
        if (firebase.getAuth() != null) callMainAcitivty();
        else {
            initUser();
            if ( !user.getTokenSP(this).isEmpty() ){
                firebase.authWithPassword(
                        "password",
                        user.getTokenSP(this),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                user.saveTokenSP( LoginActivity.this, authData.getToken() );
                                callMainAcitivty();
                                Toast.makeText(getApplicationContext(), "Logado com sucesso", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {}
                        }
                );
            }
        }
    }

    /**
     * Verificar o login do usuário dadas as informações digitadas no edittex
     */

    private void verifyLogin(){
        firebase.authWithPassword(
                user.getEmail(),
                user.getSenha(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        user.saveTokenSP( LoginActivity.this, authData.getToken() );
                        callMainAcitivty();
                        Toast.makeText(getApplicationContext(), "Logado com sucesso", Toast.LENGTH_SHORT).show();
                        closeProgressBar();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        closeProgressBar();
                        Toast.makeText(getApplicationContext(), "Erro ao logar"+firebaseError, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    /**
     * Método para chamar a acitivty principal
     */

    private void callMainAcitivty(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}
