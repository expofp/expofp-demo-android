package com.example.expofp;

import android.os.Bundle;

import com.expofp.fplan.FplanView;
import com.expofp.fplan.SharedFplanView;
import com.expofp.fplan.models.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

public class FplanActivity extends AppCompatActivity {

    private SharedFplanView fplanView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fplan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        fplanView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fplan);

        fplanView = findViewById(R.id.fplanView);
        fplanView.load("https://demo.expofp.com", new Settings());
    }
}