package perez.marcos.com.recyclerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder>{

    ArrayList<Contact> contactos;

    MyCustomAdapter(ArrayList contactos){
        this.contactos = contactos;
    }


    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewholder, int position) {
        int iconLayout = contactos.get(position).getIcon();
        //Dependiendo del entero se asignará una imagen u otra
        switch (iconLayout){
            case 0:
                //male
                adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.icon0));
                break;
            case 1:
                //female
                adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.icon1));
                break;
        }
        adapterViewholder.name.setText(contactos.get(position).getName());
        adapterViewholder.phone.setText(contactos.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        //Debemos retornar el tamaño de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrará nada.
        return contactos.size();
    }



    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */

        public ImageView icon;
        public TextView name;
        public TextView phone;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }
}
