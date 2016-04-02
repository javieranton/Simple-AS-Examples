package com.perez.marcos.simplecommunicationbetweenfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos el primer fragment, y no le pasamos argumentos!
        setTitle("Fragment 1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        //Reemplazamos el Frame Layout de la Activity por el nuevo fragment.
        //El Frame Layout es el contenedor
        fragmentTransaction.replace(R.id.frameLayout, new FirstFragment());
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(String text, Integer from) {

        Fragment f = null;

        if (from == 1 ) {
            f = new SecondFragment();
            //Podemos hacer directamente setTitle por que nuestra activity
            //es AppCompatActivity
            setTitle("Fragment 2");
        }
        else if(from == 2) {
            f = new FirstFragment();
            setTitle("Fragment 1");
        }

        //Creamos un bundle con el text recibido del fragment
        Bundle b = new Bundle();
        b.putString("message", text);
        //Añadimos el Bundle al nuevo fragment
        f.setArguments(b);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, f);
        fragmentTransaction.commit();


    }
}
