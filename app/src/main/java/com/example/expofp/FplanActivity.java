package com.example.expofp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.expofp.common.Location;
import com.expofp.fplan.FplanView;
import com.expofp.fplan.SharedFplanView;
import com.expofp.fplan.contracts.DownloadZipToCacheCallback;
import com.expofp.fplan.contracts.FplanEventsListener;
import com.expofp.fplan.models.Bookmark;
import com.expofp.fplan.models.Category;
import com.expofp.fplan.models.Details;
import com.expofp.fplan.models.FloorPlanBoothBase;
import com.expofp.fplan.models.Route;
import com.expofp.fplan.models.Settings;
import com.expofp.fplan.views.OfflineFplanView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.expofp.databinding.ActivityFplanBinding;

public class FplanActivity extends AppCompatActivity {

    private SharedFplanView _fplanView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fplan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            /*Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);*/
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        _fplanView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fplan);

        _fplanView = findViewById(R.id.fplanView);
        //_fplanView.init("https://demo.expofp.com", new Settings());

        Settings settings = new Settings();
        settings.withGlobalLocationProvider()
                .withEventsListener(new FplanEventsListener() {
            @Override
            public void onFpConfigured() {

            }

            @Override
            public void onFpConfigureError(int i, String s) {

            }

            @Override
            public void onBoothClick(@Nullable FloorPlanBoothBase floorPlanBoothBase) {
                System.out.println("onBoothClick");
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
            public void onExhibitorCustomButtonClick(String s, int i, String s1) {

            }

            @Override
            public void onCurrentPositionChanged(Location location) {

            }

            @Override
            public void onMessageReceived(@Nullable String s) {

            }
        });

        //_fplanView.openFileFromCache("noOverlay=true", settings);

        /*SharedFplanView.downloadZipToCache("https://fieurope2024.expofp.com", getBaseContext(), new DownloadZipToCacheCallback() {
            @Override
            public void onCompleted(String s) {
                _fplanView.openFile(s, null, settings);
            }

            @Override
            public void onError(String s) {

            }
        });*/
        _fplanView.load("https://fieurope2024.expofp.com?noOverlay=true", settings);
    }

}