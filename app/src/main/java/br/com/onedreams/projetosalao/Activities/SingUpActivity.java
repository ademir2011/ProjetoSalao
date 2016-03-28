package br.com.onedreams.projetosalao.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import br.com.onedreams.projetosalao.Classes.LibraryClass;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Postagem;
import br.com.onedreams.projetosalao.Classes.User;
import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingUpActivity extends AppCompatActivity {

    @Bind(R.id.btCadastrarSingUp) Button btCadastrarSingUp;
    @Bind(R.id.etEmailSingUp) EditText etEmailSingUp;
    @Bind(R.id.etSenhaSingUp) EditText etSenhaSingUp;
    @Bind(R.id.pbSingUp) ProgressBar pbSingUp;

    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        ButterKnife.bind(this);

        firebase = LibraryClass.getFirebase();

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
     * Botão para voltar a tela de login. Caso clicado muda a activity para a de login
     * @param v
     */

    @OnClick(R.id.btVoltarSingUp)
    public void clickBtVoltarSingUp(View v){
        startActivity(new Intent(SingUpActivity.this, LoginActivity.class));
    }

    /**
     * Botão para cadastro. Ao clicar o usuário é registrado 
     * @param v
     */

    @OnClick(R.id.btCadastrarSingUp)
    public void clickBtCadastrarSingUp(View v){

        openProgressBar();

        final User user = new User();
        user.setEmail(etEmailSingUp.getText().toString());
        user.setSenha(etSenhaSingUp.getText().toString());

        firebase.createUser(
                user.getEmail(),
                user.getSenha(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        user.setId(stringObjectMap.get("uid").toString());
                        user.saveDB();
                        firebase.unauth();
                        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        closeProgressBar();
                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        closeProgressBar();
                        Toast.makeText(getApplicationContext(), "Error"+firebaseError, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    /**
     * Ativa a visualização do progressbar
     */

    protected void openProgressBar(){
        pbSingUp.setVisibility( View.VISIBLE );
    }

    /**
     * Tira a visualização do progressbar
     */

    protected void closeProgressBar(){
        pbSingUp.setVisibility( View.GONE );
    }

}
