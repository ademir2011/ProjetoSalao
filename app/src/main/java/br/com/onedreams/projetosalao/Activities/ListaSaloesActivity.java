package br.com.onedreams.projetosalao.Activities;

import android.content.Context;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.onedreams.projetosalao.Adapters.CardSaloesAdapter;
import br.com.onedreams.projetosalao.Classes.Filtro;
import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Salao;
import br.com.onedreams.projetosalao.DAO.DaoSalao;
import br.com.onedreams.projetosalao.R;

public class ListaSaloesActivity extends AppCompatActivity {

    private List<Salao> cardSaloesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CardSaloesAdapter mAdapter;
    private Toolbar toolbar;

    DaoSalao daoSalao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_saloes);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lista_saloes);

        mAdapter = new CardSaloesAdapter(cardSaloesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Filtro filtro = (Filtro) getIntent().getSerializableExtra("Filtro");

        /** passar para o parametro do construtor qual deve ser o tipo de busca
        *   se passar o nome pelo construtor então vai buscar todos os saloes que tem
        *   aquele nome
        */
        populateScreenSaloes(filtro);

    }

    /**
     * Método para pegar distancia em metros a partir de dois pontos no mapa
     * @param oldPositionLatitude
     * @param oldPositionLongitude
     * @param newPositionLatitude
     * @param newPositionLongitude
     * @return
     */

    public double getDistanceBetweenLatLong(double oldPositionLatitude, double oldPositionLongitude, double newPositionLatitude, double newPositionLongitude){
        float[] results = new float[1];
        Location.distanceBetween(oldPositionLatitude, oldPositionLongitude, newPositionLatitude, newPositionLongitude, results);
        return (double) results[0];
    }

    private void populateScreenSaloes(Filtro filtro) {

        daoSalao = new DaoSalao(filtro);

        for (int i = 0; i < daoSalao.getSalaoList().size(); i++){

            double distancia = getDistanceBetweenLatLong(   filtro.getLatitude(),
                                                            filtro.getLongitude(),
                                                            daoSalao.getSalaoList().get(i).getLocalizacao().getLatitude(),
                                                            daoSalao.getSalaoList().get(i).getLocalizacao().getLongitude());

            // Valor da distancia em km
            distancia /= 1000;

            daoSalao.getSalaoList().get(i).setDistancia( distancia );
            cardSaloesList.add(daoSalao.getSalaoList().get(i));

        }

        mAdapter.notifyDataSetChanged();

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
