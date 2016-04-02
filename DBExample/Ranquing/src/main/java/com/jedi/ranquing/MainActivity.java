package com.jedi.ranquing;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SQL sql = new SQL(getApplicationContext());
        db = sql.getWritableDatabase();

        String query = "SELECT id _id, nick, punts FROM ranking ORDER BY punts DESC";
        Cursor c = db.rawQuery(query, null);
        startManagingCursor(c);
        String[] columnes = { "nick", "punts" };
        int[] items = { R.id.TVnick, R.id.TVpunts };
        final SimpleCursorAdapter sca = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.row, c, columnes, items);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(sca);

    }

}
