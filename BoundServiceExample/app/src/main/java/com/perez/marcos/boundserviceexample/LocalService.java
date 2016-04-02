package com.perez.marcos.boundserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by marcos on 01/02/2016.
 */

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    public LocalService(){

    }

    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * method for clients
     */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}


