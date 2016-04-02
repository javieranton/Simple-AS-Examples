package com.example.material.joanbarroso.materialexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        setUpViews();

        //Creamos el primer fragment, y no le pasamos argumentos!
        //setTitle("Fragment 1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        //Reemplazamos el Frame Layout de la Activity por el nuevo fragment.
        //El Frame Layout es el contenedor
        fragmentTransaction.replace(R.id.fragment_container, new FirstFragment());
        fragmentTransaction.commit();
    }

    private void setUpViews() {
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout)
;
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        Toast.makeText(DrawerActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.rate:
                        Toast.makeText(DrawerActivity.this, "Rate Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.purchase:
                        Toast.makeText(DrawerActivity.this, "Purchase Selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Activity2.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(String text, Integer from) {

    }
}
