package rabade.anton.javier.macedonia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import Classes.DBContentHelper;

public class LogoMain extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_main);

        final DBContentHelper dH = new DBContentHelper(getApplicationContext());
        db = dH.getWritableDatabase();

        getSupportActionBar().hide();
    }

    public void onClickLogo(View view) {
        Intent i;
        Cursor c = db.rawQuery("select * from " + DBContentHelper.LOGIN_TABLE + " where "+DBContentHelper.LOGIN_COLUMN_STATUS+"='1'" ,null);
        if (c.getCount()==1){
            i = new Intent(getApplicationContext(), NavDrawer.class);
        }else{
            i = new Intent(getApplicationContext(), Login.class);
        }
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.FL_logo));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}
