package rabade.anton.javier.macedonia;


import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import Classes.CoolImageFlipper;
import Classes.DBContentHelper;

public class Memory extends Fragment implements View.OnClickListener {

    private ImageView lastImage, currentImage, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16;
    private CoolImageFlipper coolImageFlipper;
    private ArrayList cards;
    private Map myMap;
    private boolean first = false, second = false;
    private int intentos = 0, aciertos = 0;
    private TextView tv_intentos, tv_aciertos;
    SQLiteDatabase db;

    public Memory() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_memory, container, false);

        coolImageFlipper = new CoolImageFlipper(getActivity().getApplicationContext());

        tv_intentos = (TextView) rootView.findViewById(R.id.tv_intentos);
        tv_aciertos = (TextView) rootView.findViewById(R.id.tv_aciertos);

        card1 = (ImageView) rootView.findViewById(R.id.iv_card1);
        card1.setOnClickListener(this);
        card2 = (ImageView) rootView.findViewById(R.id.iv_card2);
        card2.setOnClickListener(this);
        card3 = (ImageView) rootView.findViewById(R.id.iv_card3);
        card3.setOnClickListener(this);
        card4 = (ImageView) rootView.findViewById(R.id.iv_card4);
        card4.setOnClickListener(this);
        card5 = (ImageView) rootView.findViewById(R.id.iv_card5);
        card5.setOnClickListener(this);
        card6 = (ImageView) rootView.findViewById(R.id.iv_card6);
        card6.setOnClickListener(this);
        card7 = (ImageView) rootView.findViewById(R.id.iv_card7);
        card7.setOnClickListener(this);
        card8 = (ImageView) rootView.findViewById(R.id.iv_card8);
        card8.setOnClickListener(this);
        card9 = (ImageView) rootView.findViewById(R.id.iv_card9);
        card9.setOnClickListener(this);
        card10 = (ImageView) rootView.findViewById(R.id.iv_card10);
        card10.setOnClickListener(this);
        card11 = (ImageView) rootView.findViewById(R.id.iv_card11);
        card11.setOnClickListener(this);
        card12 = (ImageView) rootView.findViewById(R.id.iv_card12);
        card12.setOnClickListener(this);
        card13 = (ImageView) rootView.findViewById(R.id.iv_card13);
        card13.setOnClickListener(this);
        card14 = (ImageView) rootView.findViewById(R.id.iv_card14);
        card14.setOnClickListener(this);
        card15 = (ImageView) rootView.findViewById(R.id.iv_card15);
        card15.setOnClickListener(this);
        card16 = (ImageView) rootView.findViewById(R.id.iv_card16);
        card16.setOnClickListener(this);

        final DBContentHelper sql = new DBContentHelper(getActivity().getApplicationContext());
        db = sql.getWritableDatabase();

        buildAndMap();

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void buildAndMap() {
        cards = new ArrayList();
        addCards();

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        myMap = new LinkedHashMap();

        Iterator i1 = cards.iterator();
        Iterator i2 = numbers.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            myMap.put(i1.next(), i2.next());
        }
    }

    private void addCards() {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);
        cards.add(card12);
        cards.add(card13);
        cards.add(card14);
        cards.add(card15);
        cards.add(card16);
    }

    //Importante! Aquí no se puede usar el método del "onClick" en xml
    // por que referencia a la Activity y no al fragment!!
    @Override
    public void onClick(View v) {
        if (currentImage == null) {
            currentImage = ((ImageView) v);
            Log.e(((ImageView) v).toString(), "onClick: ");
            MyTask task = new MyTask();
            task.execute();
        }
    }

    private boolean comprobarImagen() {
        if ((int) myMap.get(lastImage) == ((int) myMap.get(currentImage) - 8) || (int) myMap.get(lastImage) == ((int) myMap.get(currentImage) + 8)) {
            return true;
        }
        return false;
    }

    private void flipIm(ImageView iV, int i) {
        Drawable d = null;
        switch (i % 8) {
            case 0:
                d = getResources().getDrawable(R.drawable.pear);
                break;
            case 1:
                d = getResources().getDrawable(R.drawable.pinneaple);
                break;
            case 2:
                d = getResources().getDrawable(R.drawable.strawberry);
                break;
            case 3:
                d = getResources().getDrawable(R.drawable.orange);
                break;
            case 4:
                d = getResources().getDrawable(R.drawable.watermelon);
                break;
            case 5:
                d = getResources().getDrawable(R.drawable.banana);
                break;
            case 6:
                d = getResources().getDrawable(R.drawable.blackberry);
                break;
            case 7:
                d = getResources().getDrawable(R.drawable.lemon);
                break;

        }
        coolImageFlipper.flipImage(d, iV);
    }

    /**
     *
     */
    private class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... param) {
            publishProgress(0);
            if (first && !second) {
                try {
                    second = true;
                    boolean equals = comprobarImagen();
                    intentos++;
                    if (equals) {
                        aciertos++;
                        publishProgress(1);
                        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                        //Thread.sleep(200);
                    } else {
                        publishProgress(1);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "proceso finalizado";
        }


        @Override
        protected void onPostExecute(String result) {
            if (!first) {
                lastImage = currentImage;
                first = true;
                currentImage = null;
            }
            if (second) {
                boolean equals = comprobarImagen();
                if (equals) {
                    lastImage.setVisibility(View.INVISIBLE);
                    currentImage.setVisibility(View.INVISIBLE);
                } else {
                    coolImageFlipper.flipImage(getResources().getDrawable(R.drawable.carta), lastImage);
                    coolImageFlipper.flipImage(getResources().getDrawable(R.drawable.carta), currentImage);
                }

                if (aciertos == 8) {
                    theEnd();
                }

                first = false;
                second = false;
                lastImage = null;
                currentImage = null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values[0] == 1) {
                tv_intentos.setText("Intentos: " + intentos);
                tv_aciertos.setText("Aciertos: " + aciertos);
            } else {
                int i = (int) myMap.get(currentImage);
                flipIm(currentImage, i);
            }
        }

        @Override
        protected void onPreExecute() {
            Log.i("task", "onPreExecute()");
            super.onPreExecute();
        }
    }

    private void theEnd() {
        DBContentHelper dH = new DBContentHelper(getContext());
        ContentValues valuesToStore = new ContentValues();
        valuesToStore.put(DBContentHelper.RANKING_COLUMN_NAME, String.valueOf(dH.getLogedName()));
        valuesToStore.put(DBContentHelper.RANKING_COLUMN_POINS, String.valueOf(intentos));

        long ret = db.insert(DBContentHelper.RANKING_TABLE, null, valuesToStore);

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);
        dialog.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.memory_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_reload:
                Intent i = new Intent(getContext(), MemoryHolder.class);
                startActivity(i);
                getActivity().finish();
                break;

        }
        return true;
    }
}


