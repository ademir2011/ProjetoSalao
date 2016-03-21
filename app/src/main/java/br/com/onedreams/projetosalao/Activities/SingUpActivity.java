package br.com.onedreams.projetosalao.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SingUpActivity extends AppCompatActivity {

    @Bind(R.id.toolbarSingUp)
    Toolbar toolbarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
