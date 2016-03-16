package br.com.onedreams.projetosalao.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.onedreams.projetosalao.Classes.SistemaSalaoProfissionalClasses.Salao;
import br.com.onedreams.projetosalao.R;

/**
 * Created by root on 09/03/16.
 */
public class CardSaloesAdapter extends RecyclerView.Adapter<CardSaloesAdapter.MyViewHolder>  {

    private List<Salao> cardSaloesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivSalao;
        public TextView tvNomeSalao;
        public TextView tvDistance;

        public MyViewHolder(View view) {
            super(view);
            ivSalao = (ImageView) view.findViewById(R.id.ivSalao);
            tvNomeSalao = (TextView) view.findViewById(R.id.tvNomeSalao);
            tvDistance = (TextView) view.findViewById(R.id.tvDistance);
        }

    }

    public CardSaloesAdapter(List<Salao> cardSaloesList) {
        this.cardSaloesList = cardSaloesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_saloes_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Salao salao = cardSaloesList.get(position);
        holder.tvNomeSalao.setText(salao.getName());

        double distanciaDouble = salao.getDistancia();

        String distanciaFormat = String.format("%.3f", distanciaDouble);

        if ( distanciaDouble < 1 ) holder.tvDistance.setText(distanciaFormat+" metros ");
        else holder.tvDistance.setText(distanciaFormat+" Km ");
    }

    @Override
    public int getItemCount() {
        return cardSaloesList.size();
    }

}
