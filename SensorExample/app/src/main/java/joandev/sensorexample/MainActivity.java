package joandev.sensorexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonStart)
    public void startTheParty() {
        startService(new Intent(this, IntelService.class));
    }
    @OnClick(R.id.buttonStop)
    public void stopTheParty() {
        stopService(new Intent(this, IntelService.class));
    }
    @OnClick(R.id.fab)
    public void startGpsActivity(){
        startActivity(new Intent (MainActivity.this, GPSActivity.class));
    }
}



