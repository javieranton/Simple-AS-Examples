package com.example.material.joanbarroso.materialexample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    CoordinatorLayout coordinatorLayout;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpViews();
    }

    private void setUpViews() {
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "¡Soy un snackbar con botón!", Snackbar.LENGTH_LONG)
                        .setAction("Action",new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Starting activity with a Transition Animation
                                startActivity((new Intent(getApplicationContext(), DrawerActivity.class)), ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                            }
                        });


                // Changing message text color
                snackbar.setActionTextColor(Color.YELLOW);

                // Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.BLUE);

                snackbar.show();
                break;
        }
    }
}
