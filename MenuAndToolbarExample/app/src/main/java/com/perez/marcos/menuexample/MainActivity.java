package com.perez.marcos.menuexample;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

public class MainActivity extends AppCompatActivity {

    ImageView foodContainer;
    CoolImageFlipper coolImageFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodContainer = (ImageView) findViewById(R.id.foodContainer);
        coolImageFlipper = new CoolImageFlipper(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.pizza:
                giveMeAPizza();
                return true;
            case R.id.burger:
                giveMeABurger();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void giveMeABurger() {
        Log.v("Burger", "Give me a burger!");
        coolImageFlipper.flipImage(getDrawable(R.drawable.bg), foodContainer);
    }

    private void giveMeAPizza() {
        Log.v("Pizza", "Give me a pizza!");
        coolImageFlipper.flipImage(getDrawable(R.drawable.pz), foodContainer);
    }

}
