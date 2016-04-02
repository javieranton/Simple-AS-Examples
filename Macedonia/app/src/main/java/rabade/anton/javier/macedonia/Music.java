package rabade.anton.javier.macedonia;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import Classes.CoolImageFlipper;
import Classes.DBContentHelper;


public class Music extends NavDrawer {

    private MyServiceMusic mService;
    private boolean bound = false;
    private ToggleButton tb_on_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        tb_on_off = (ToggleButton)findViewById(R.id.tb_on_off);


        Intent intent = new Intent(this, MyServiceMusic.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        startService(intent);
        bound= true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        DBContentHelper dH = new DBContentHelper(getApplicationContext());
        SQLiteDatabase db = dH.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DBContentHelper.LOGIN_TABLE);
        db.execSQL(DBContentHelper.LOGIN_TABLE_CREATE);

        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //stopService(intent); matar el servicio desde fuera
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(bound){
            unbindService(mConnection);
            bound = false;
        }
    }

    public void onClickToggle(View view) {
        if (tb_on_off.isChecked()){
            if(bound) {
                tb_on_off.setBackgroundDrawable( getResources().getDrawable(R.drawable.pause) );
                mService.start();
            }
        }else{
            if(bound){
                tb_on_off.setBackgroundDrawable( getResources().getDrawable(R.drawable.play) );
                mService.stop();
            }
        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyServiceMusic.LocalBinder binder = (MyServiceMusic.LocalBinder) service;

            mService = binder.getService();

            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };
}
