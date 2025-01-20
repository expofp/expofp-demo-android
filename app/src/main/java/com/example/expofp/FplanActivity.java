package com.example.expofp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.expofp.common.Location;
import com.expofp.fplan.FplanView;
import com.expofp.fplan.contracts.FplanEventsListener;
import com.expofp.fplan.models.Bookmark;
import com.expofp.fplan.models.Category;
import com.expofp.fplan.models.Details;
import com.expofp.fplan.models.FloorPlanBoothBase;
import com.expofp.fplan.models.Route;
import com.expofp.fplan.models.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;

public class FplanActivity extends AppCompatActivity {

    private FplanView fplanView;
    private ConstraintLayout progressBar;
    private String command;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fplan_menu, menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        command = null;

        Bundle arguments = getIntent().getExtras();
        if (arguments == null) {
            return;
        }

        if (!arguments.containsKey("COMMAND")) {
            return;
        }

        command = arguments.get("COMMAND").toString();
        if(!command.equalsIgnoreCase("LOAD")){
            progressBar.setVisibility(View.INVISIBLE);
        }

        if (command.equalsIgnoreCase("SHOW_CATEGORY")) {
            fplanView.selectCategory(arguments.get("DATA").toString());
        } else if (command.equalsIgnoreCase("SHOW_EXHIBITOR")) {
            fplanView.selectExhibitor(arguments.get("DATA").toString());
        } else if (command.equalsIgnoreCase("SHOW_BOOTH")) {
            fplanView.selectBooth(arguments.get("DATA").toString());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {

            if(command.equalsIgnoreCase("SHOW_CATEGORY")
                    || command.equalsIgnoreCase("SHOW_EXHIBITOR")
                    || command.equalsIgnoreCase("SHOW_BOOTH")) {
                Intent intent = new Intent(this, ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
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

        //SHOW LOADER AND LOAD DATA

        Context context = this;

        fplanView = findViewById(R.id.fplanView);
        progressBar = findViewById(R.id.progressBar);

        Settings settings = new Settings();
        settings.withEventsListener(new FplanEventsListener() {
            @Override
            public void onFpConfigured() {
                fplanView.categoriesList(categories -> {
                    fplanView.exhibitorsList(exhibitors ->{
                        fplanView.boothsList(booths -> {
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                            intent.putExtra("CATEGORIES", String.join(";", Arrays.stream(categories).map(c -> c.getName()).toArray(String[]::new)));
                            intent.putExtra("EXHIBITORS", String.join(";", Arrays.stream(exhibitors).map(c -> c.getName()).toArray(String[]::new)));
                            intent.putExtra("BOOTHS", String.join(";", Arrays.stream(booths).map(c -> c.getName()).toArray(String[]::new)));

                            startActivity(intent);
                        });
                    });
                });
            }

            @Override
            public void onFpConfigureError(int errorCode, String description) {
            }

            @Override
            public void onBoothClick(@Nullable FloorPlanBoothBase booth) {
            }

            @Override
            public void onDirection(@Nullable Route route) {
            }

            @Override
            public void onDetails(@Nullable Details details) {
            }

            @Override
            public void onBookmarkClick(Bookmark bookmark) {
            }

            @Override
            public void onCategoryClick(Category category) {
            }

            @Override
            public void onExhibitorCustomButtonClick(String externalId, int buttonNumber, String buttonUrl) {
            }

            @Override
            public void onCurrentPositionChanged(Location location) {
            }

            @Override
            public void onMessageReceived(@Nullable String message) {
            }
        });

        fplanView.init("https://demo.expofp.com", settings);
    }
}