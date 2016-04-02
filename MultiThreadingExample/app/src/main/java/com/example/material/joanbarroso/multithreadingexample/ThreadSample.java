package com.example.material.joanbarroso.multithreadingexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadSample extends AppCompatActivity {

    @Bind(R.id.imageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_sampl);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.threadButton)
    public void getImagecontent() {
        downloadImage(imageView);
    }
    @OnClick(R.id.nextButton)
    public void goNextSample() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private Handler mhHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 100 && msg.obj != null) {
                imageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };


    public void downloadImage(View v) {
        new Thread(new Runnable() {
            private Bitmap loadImageFromNetwork(String url) {
                try {
                    Bitmap bitmap = BitmapFactory
                            .decodeStream((InputStream) new URL(url)
                                    .getContent());
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void run() {
                final Bitmap bitmap = loadImageFromNetwork("http://jakewharton.github.io/butterknife/static/logo.png");
                Message msg = new Message();
                msg.what = 100;
                msg.obj = bitmap;
                mhHandler.sendMessage(msg);
            }
        }).start();
    }
}
