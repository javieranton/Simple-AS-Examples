package rabade.anton.javier.macedonia;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by joanbarroso on 3/2/15.
 */
public class MyServiceMusic extends Service {

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    MediaPlayer mediaPlayer;
    File sdCard;
    File song;



    public class LocalBinder extends Binder {
        MyServiceMusic getService() {
            return MyServiceMusic.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();
        try {
            sdCard = Environment.getExternalStorageDirectory();
            song = new File(sdCard.getAbsolutePath() + "/Song.mp3");
            mediaPlayer.setDataSource(song.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // start();

        return super.onStartCommand(intent, flags, startId);
    }

    public void start() {
        Toast.makeText(getApplicationContext(), "I'M ALIVE :D!", Toast.LENGTH_SHORT).show();
        //stopSelf(); sirve para parar el servicio

        try {
            Log.d("", "onStartCommand: " + song.getAbsolutePath());
            mediaPlayer.prepare();
            //mediaPlayer.setOnCompletionListener(new onCompletion());
        }catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
