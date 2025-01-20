package com.example.expofp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String categories;
    private String exhibitors;
    private String booths;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle arguments = getIntent().getExtras();
        if(arguments == null){
            return;
        }

        if(arguments.containsKey("CATEGORIES")){
            categories = arguments.get("CATEGORIES").toString();
        }

        if(arguments.containsKey("EXHIBITORS")){
            exhibitors = arguments.get("EXHIBITORS").toString();
        }

        if(arguments.containsKey("BOOTHS")){
            booths = arguments.get("BOOTHS").toString();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, FplanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("COMMAND", "LOAD");
        startActivity(intent);
    }

    public void categoriesClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("COMMAND", "SHOW_CATEGORY");
        intent.putExtra("DATA", categories);
        startActivity(intent);
    }

    public void exhibitorsClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("COMMAND", "SHOW_EXHIBITOR");
        intent.putExtra("DATA", exhibitors);
        startActivity(intent);
    }

    public void boothsClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("COMMAND", "SHOW_BOOTH");
        intent.putExtra("DATA", booths);
        startActivity(intent);
    }

    public void floorplanClick(View view) {
        Intent intent = new Intent(this, FplanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("COMMAND", "SHOW");
        startActivity(intent);
    }
}
