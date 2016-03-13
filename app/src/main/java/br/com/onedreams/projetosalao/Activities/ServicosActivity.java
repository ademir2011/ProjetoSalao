package br.com.onedreams.projetosalao.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.onedreams.projetosalao.R;

public class ServicosActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicosActivity.this, ListaSaloesActivity.class));
            }
        });

        Spinner spLocais = (Spinner) findViewById(R.id.spLocais);
        List<String> listLocais = new ArrayList<>();
        listLocais.add("Brazil");
        listLocais.add("EUA");
        listLocais.add("China");
        ArrayAdapter<String> adpt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listLocais);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocais.setAdapter(adpt);

        spLocais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ServicosActivity.this, ""+ parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
