package br.com.onedreams.projetosalao.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.onedreams.projetosalao.Classes.Filtro;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Servicos;
import br.com.onedreams.projetosalao.R;

public class ServicosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    RequestQueue requestQueue;
    Spinner spLocais;
    RadioGroup rgSexo;
    RadioButton rbSexoMasculino;
    RadioButton rbSexoFeminino;
    Filtro filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spLocais = (Spinner) findViewById(R.id.spLocais);
        filtro = new Filtro();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinnerinit();
        try { alertdialog(); } catch (IOException e) { e.printStackTrace(); }
        floatingactionbuttoninit();

        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rbSexoMasculino = (RadioButton) findViewById(R.id.rbSexoMasculino);
        rbSexoFeminino = (RadioButton) findViewById(R.id.rbSexoFeminino);

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rbSexoMasculino.isChecked()) filtro.setMasculino(true);
                else filtro.setMasculino(false);
                if (rbSexoFeminino.isChecked()) filtro.setFeminino(true);
                else filtro.setFeminino(false);
            }
        });

    }

    private void alertdialog() throws IOException {

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://www.geonames.org/childrenJSON?geonameId=3469034",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("geonames");
                            JSONObject geonames = null;

                            List<String> estados = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                geonames = jsonArray.getJSONObject(i);
                                String state = geonames.getString("toponymName");
                                estados.add(state);
                            }

                            populateSpinner(spLocais, estados);

                        } catch ( JSONException e ){}
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

        requestQueue.add(jsonObjectRequest);

    }

    private void floatingactionbuttoninit() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ServicosActivity.this, ListaSaloesActivity.class);
                    intent.putExtra("Filtro", filtro);
                    startActivity(intent);
                }
            });
        }
    }

    private void populateSpinner(Spinner spinner, List<String> itens){

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itens);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    private void spinnerinit() {

        spLocais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtro.setEstado(""+parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
