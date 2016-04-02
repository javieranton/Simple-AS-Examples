package com.example.material.joanbarroso.multithreadingexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyTask task = new MyTask();
                        task.execute(4);
                    }
                });

                ListView lv = (ListView) findViewById(R.id.listView);
                final ArrayList<String> values = new ArrayList<String>(Arrays.asList("Red", "Green", "Blue", "Black"));

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

                // Assign adapter to ListView
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
                        // TODO Auto-generated method stub
                        values.remove(pos);
                        adapter.notifyDataSetChanged();
                    }

                });
            }

            private class MyTask extends AsyncTask<Integer, Integer, String> {
                @Override
                protected String doInBackground(Integer... param) {
                    int num = param[0];
                    for (int i = 0; i < 1000; ++i) {
                        num++;
                        publishProgress(num);
                    }
                    return "proceso finalizado: " + num;
                }
                @Override
                protected void onPostExecute(String result) {
                    TextView tv = (TextView) findViewById(R.id.textView);
                    tv.setText(result);
                }
                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    Log.i("task", "progress update: " + values[0]);
                }
                @Override
                protected void onPreExecute() {
                    Log.i( "task", "onPreExecute()" );
                    super.onPreExecute();
                }


            }
}


