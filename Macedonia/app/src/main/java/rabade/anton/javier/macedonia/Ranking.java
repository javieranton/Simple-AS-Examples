package rabade.anton.javier.macedonia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Classes.DBContentHelper;
import Classes.MyRankingAdapter;
import Classes.Puntuation;


public class Ranking extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    private List<Puntuation> ranking;

    public Ranking(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_ranking, container, false);

        //findViewById del layout activity_login
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(getContext());

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);

        final DBContentHelper sql = new DBContentHelper(rootView.getContext());
        ranking =  sql.getPuntuations();

        //El adapter se encarga de  adaptar un objeto definido en el c�digo a una vista en xml
        //seg�n la estructura definida.
        //Asignamos nuestro custom Adapter
        mRecyclerView.setAdapter(new MyRankingAdapter(ranking));


        return rootView;
    }
}
