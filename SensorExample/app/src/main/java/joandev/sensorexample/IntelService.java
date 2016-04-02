package joandev.sensorexample;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class IntelService extends Service implements SensorEventListener, MediaPlayer.OnCompletionListener {


    SensorManager sensorManager;
    Sensor sensor;
    Float maxLux, minLux;
    MediaPlayer mPlayer;

    public IntelService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        maxLux = 0f;
        minLux = 4000f;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        } else {
            Toast.makeText(getApplicationContext(), "SHIT", Toast.LENGTH_SHORT).show();
        }
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.up);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("sensorData", "detached");
        sensorManager.unregisterListener(this);
        mPlayer.release();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.v("sensorData", "sensorChanged");
        Float lux = sensorEvent.values[0];
        Log.v("sensorData", lux.toString());

        if (lux < minLux) minLux = lux;
        if (lux > maxLux) maxLux = lux;
        if (maxLux > minLux + 500) {
            mPlayer.start();
            maxLux = 0f;
            minLux = 4000f;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
    }
}
