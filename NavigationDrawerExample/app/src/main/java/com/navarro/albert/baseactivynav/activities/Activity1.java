package com.navarro.albert.baseactivynav.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.navarro.albert.baseactivynav.BaseActivity;
import com.navarro.albert.baseactivynav.R;

public class Activity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        setTitle("Activity1");
    }
}
