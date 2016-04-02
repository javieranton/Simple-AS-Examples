package Classes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rabade.anton.javier.macedonia.R;

/**
 * Created by Javier on 09/02/2016.
 */
public class MyRankingAdapter extends RecyclerView.Adapter<MyRankingAdapter.AdapterViewHolder>{

    List<Puntuation> ranking;

    public MyRankingAdapter(List<Puntuation> ranking) {
        this.ranking = ranking;
    }


    @Override
    public MyRankingAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRankingAdapter.AdapterViewHolder holder, int position) {
        holder.nick.setText(ranking.get(position).getName());
        holder.points.setText(ranking.get(position).getAttemps());

    }

    @Override
    public int getItemCount() {
        //Debemos retornar el tamaño de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrará nada.
        return ranking.size();
    }

    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */
        public TextView nick;
        public TextView points;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.nick = (TextView) itemView.findViewById(R.id.TVnick);
            this.points = (TextView) itemView.findViewById(R.id.TVpoints);
        }
    }

}
