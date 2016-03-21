package br.com.onedreams.projetosalao.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.onedreams.projetosalao.Classes.CardFeed;
import br.com.onedreams.projetosalao.R;

/**
 * Created by root on 09/03/16.
 */
public class CardFeedAdapter extends RecyclerView.Adapter<CardFeedAdapter.MyViewHolder>  {

    private List<CardFeed> cardFeedList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNomeDoComentaristaCardFeed;
        public TextView tvMensagemDoComentaristaCardFeed;

        public MyViewHolder(View view) {
            super(view);
            tvNomeDoComentaristaCardFeed = (TextView) view.findViewById(R.id.tvNomeDoComentaristaCardFeed);
            tvMensagemDoComentaristaCardFeed = (TextView) view.findViewById(R.id.tvMensagemDoComentaristaCardFeed);
        }

    }

    public CardFeedAdapter(List<CardFeed> cardFeedList) {
        this.cardFeedList = cardFeedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_feed_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CardFeed cardFeed = cardFeedList.get(position);
        holder.tvNomeDoComentaristaCardFeed.setText(cardFeed.getmNomeDoComentarista());
        holder.tvMensagemDoComentaristaCardFeed.setText(cardFeed.getmMensagemDoComentarista());
    }

    @Override
    public int getItemCount() {
        return cardFeedList.size();
    }

}
