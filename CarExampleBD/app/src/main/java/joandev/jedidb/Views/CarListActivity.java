package joandev.jedidb.Views;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import joandev.jedidb.Data.CarHelper;
import joandev.jedidb.R;

public class CarListActivity extends ListActivity {


    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    CarHelper carHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);

        carHelper = new CarHelper(getApplicationContext());

        /*Cursor c = carHelper.getAllCars();
        if (c.moveToFirst()) {
            do {
                adapter.add(c.getString(c.getColumnIndex("name")));
            } while (c.moveToNext());
        }
        */

        Collection c = carHelper.getAllCars(); //IMPORTANTE
        adapter.addAll(c);


        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                Cursor cursor = carHelper.getCarSpeedByName(((TextView) v).getText().toString());
                if (cursor.moveToFirst()) {
                    do {
                        Toast.makeText(getApplicationContext(),cursor.getString(cursor.getColumnIndex("maxSpeed")),Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_list, menu);
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
