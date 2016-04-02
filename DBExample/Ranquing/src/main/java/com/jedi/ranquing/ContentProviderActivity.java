package com.jedi.ranquing;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContentProviderActivity extends Activity {
    public ContentResolver cr;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        cr = getContentResolver();
        Cursor cursor = cr.query(Settings.System.CONTENT_URI, null, null, null, null);
        lv = (ListView)findViewById(R.id.listView);
        startManagingCursor(cursor);
        String[] from = {Settings.System.NAME, Settings.System.VALUE};
        int[] to = {R.id.TVnick, R.id.TVpunts};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.row, cursor, from,to);
        lv.setAdapter(adapter);
        stopManagingCursor(cursor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content, menu);
        return true;
    }
    
}
