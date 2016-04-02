package perez.marcos.com.recyclerviewexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    private ArrayList contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById del layout activity_main
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(this);

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);


        contactos = new ArrayList();
        for(int i=0;i<10; i++){
            cretateContactos();
        }
        //El adapter se encarga de  adaptar un objeto definido en el c�digo a una vista en xml
        //seg�n la estructura definida.
        //Asignamos nuestro custom Adapter
        mRecyclerView.setAdapter(new MyCustomAdapter(contactos));

    }

    private void cretateContactos() {
        contactos.add(new Contact(0,"Benito Camela","123456789"));
        contactos.add(new Contact(0,"Alberto Carlos Huevos","123456789"));
        contactos.add(new Contact(1,"Lola Mento","123456789"));
        contactos.add(new Contact(0,"Aitor Tilla","123456789"));
        contactos.add(new Contact(0,"Elvis Teck","123456789"));
        contactos.add(new Contact(1,"Débora Dora","123456789"));
        contactos.add(new Contact(0,"Borja Món de York","123456789"));
        contactos.add(new Contact(1,"Encarna Vales","123456789"));
        contactos.add(new Contact(0,"Enrique Cido","123456789"));
        contactos.add(new Contact(0,"Francisco Jones","123456789"));
        contactos.add(new Contact(1,"Estela Gartija","123456789"));
        contactos.add(new Contact(0,"Andrés Trozado","123456789"));
        contactos.add(new Contact(0,"Carmelo Cotón","123456789"));
        contactos.add(new Contact(0,"Alberto Mate","123456789"));
        contactos.add(new Contact(0,"Chema Pamundi","123456789"));
        contactos.add(new Contact(0,"Armando Adistancia","123456789"));
        contactos.add(new Contact(1,"Helena Nito Del Bosque","123456789"));
        contactos.add(new Contact(0,"Unai Nomás","123456789"));
        contactos.add(new Contact(1,"Ester Colero","123456789"));
        contactos.add(new Contact(0,"Marcos Corrón","123456789"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
