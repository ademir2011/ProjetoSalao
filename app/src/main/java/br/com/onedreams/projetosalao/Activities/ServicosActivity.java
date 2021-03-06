package br.com.onedreams.projetosalao.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.onedreams.projetosalao.Classes.Filtro;
import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ServicosActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.spLocais)
    Spinner spLocais;
    @Bind(R.id.rgSexo)
    RadioGroup rgSexo;
    @Bind(R.id.rbSexoMasculino)
    RadioButton rbSexoMasculino;
    @Bind(R.id.rbSexoFeminino)
    RadioButton rbSexoFeminino;

    Filtro filtro;
    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        filtro = new Filtro();

        floatingactionbuttoninit();
        spinnerListener();

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

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

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
     *
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
     *
     * @param bundle
     */

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.e("teste", "entrou");

        //checa compatibilidade
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //pega informações sobre a última localização
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        //armazena no objeto address informações de localização dada latitude e longitude
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        List<Address> address = null;
        try {
            address = geoCoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
        } catch (Exception ignored) {
        }

        //seta latitude e longitude no filtro
        filtro.setLatitude(mLastLocation.getLatitude());
        filtro.setLongitude(mLastLocation.getLongitude());

        //pega a cidade atual do objeto address
        String cidadeAtual = address.get(0).getAddressLine(1);

        //popula o sppiner com a cidade atual
        if (mLastLocation != null) {
            List<String> locaisList = new ArrayList<>();
            locaisList.add(cidadeAtual);
            populateSpinner(spLocais, locaisList);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, connectionResult.toString(), Toast.LENGTH_LONG).show();
    }

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
