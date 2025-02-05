package com.example.expofp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.expofp.fplan.SharedFplanView;
import com.expofp.fplan.models.Settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedFplanView.preload("demo.expofp.com", new Settings(), getBaseContext());
    }

    public void floorplanClick(View view) {
        Intent intent = new Intent(this, FplanActivity.class);
        startActivity(intent);
    }
}
