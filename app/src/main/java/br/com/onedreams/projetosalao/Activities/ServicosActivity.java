package br.com.onedreams.projetosalao.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

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

public class ServicosActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Toolbar toolbar;
    RequestQueue requestQueue;
    Spinner spLocais;
    RadioGroup rgSexo;
    RadioButton rbSexoMasculino;
    RadioButton rbSexoFeminino;
    Filtro filtro;
    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spLocais = (Spinner) findViewById(R.id.spLocais);

        filtro = new Filtro();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        floatingactionbuttoninit();
        spinnerListener();

        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rbSexoMasculino = (RadioButton) findViewById(R.id.rbSexoMasculino);
        rbSexoFeminino = (RadioButton) findViewById(R.id.rbSexoFeminino);

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbSexoMasculino.isChecked()) filtro.setMasculino(true);
                else filtro.setMasculino(false);
                if (rbSexoFeminino.isChecked()) filtro.setFeminino(true);
                else filtro.setFeminino(false);
            }
        });

        buildGoogleApiClient();
        
        if (mGoogleApiClient != null) { mGoogleApiClient.connect(); }

    }

    /**
     * Conecta o google api client
     */

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    /**
     * Desconecta o google api client 
     */

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * Método que inicializa o Floating action button e recebe os eventos do mesmo
     */

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

    /**
     * Adiciona itens ao componente spinner
     * @param spinner
     * @param itens
     */

    private void populateSpinner(Spinner spinner, List<String> itens) {

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itens);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    /**
     * Método que espera os eventos do componente spinner
     */

    private void spinnerListener() {

        spLocais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtro.setCidade("" + parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

    }

    /**
     * Quando o google api cliente é conectado o método abaixo é chamado
     * @param bundle
     */

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //checa compatibilidade
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //pega informações sobre a última localização
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        //armazena no objeto address informações de localização dada latitude e longitude
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        List<Address> address = null;
        try { address = geoCoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1); } catch (Exception ignored) {}

        //seta latitude e longitude no filtro
        filtro.setLatitude(mLastLocation.getLatitude());
        filtro.setLongitude(mLastLocation.getLongitude());

        //pega a cidade atual do objeto address
        String cidadeAtual = address.get(0).getAddressLine(2);

        //popula o sppiner com a cidade atual
        if (mLastLocation != null) {
            List<String> locaisList = new ArrayList<>();
            locaisList.add(cidadeAtual);
            populateSpinner(spLocais, locaisList);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    /**
     * Constroi a google api client
     */

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

}
