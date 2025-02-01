package com.example.expofp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.expofp.common.GlobalLocationProvider;
import com.expofp.common.Location;
import com.expofp.common.LocationProvider;
import com.expofp.crowdconnected.CrowdConnectedProvider;
import com.expofp.crowdconnected.Mode;
import com.expofp.fplan.FplanView;
import com.expofp.fplan.SharedFplanView;
import com.expofp.fplan.WebViewClient;
import com.expofp.fplan.contracts.DownloadZipToCacheCallback;
import com.expofp.fplan.contracts.FplanEventsListener;
import com.expofp.fplan.models.Bookmark;
import com.expofp.fplan.models.Category;
import com.expofp.fplan.models.Details;
import com.expofp.fplan.models.FloorPlanBoothBase;
import com.expofp.fplan.models.Route;
import com.expofp.fplan.models.Settings;
import com.expofp.fplan.views.WKWebView;

public class MainActivity extends AppCompatActivity {

    SharedFplanView fplanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*SharedFplanView.downloadZipToCache("https://fieurope2024.expofp.com", getBaseContext(), new DownloadZipToCacheCallback() {
            @Override
            public void onCompleted(String htmlFilePath) {
                SharedFplanView.preloadFile(htmlFilePath, getBaseContext());
            }

            @Override
            public void onError(String s) {
                SharedFplanView.preloadFileFromCache(getBaseContext());
            }
        });*/

        com.expofp.crowdconnected.Settings lpsettings = new com.expofp.crowdconnected.Settings("WpDTJbRD",
                "3c5fc9295450423bb3c0b344fc5edc3a",
                "72m325ygJ46rI8317251r5I35I12zVIP", Mode.GPS_ONLY);
        lpsettings.setServiceNotificationInfo("TEST", R.drawable.ic_booth);

        LocationProvider lp = new CrowdConnectedProvider(getApplication(), lpsettings);
        GlobalLocationProvider.init(lp);
        GlobalLocationProvider.start();

        SharedFplanView.preload("https://fieurope2024.expofp.com", this);
        /*fplanView = new SharedFplanView(getApplicationContext());
        fplanView.load("https://fieurope2024.expofp.com");
        fplanView.destroy();*/

        /*WebView fplanView = new WebView(this);
        fplanView.getSettings().setJavaScriptEnabled(true);
        fplanView.loadUrl("https://fieurope2024.expofp.com");
        setContentView(fplanView);*/

        /*SharedFplanView fplanView = new SharedFplanView(this);
        //setContentView(fplanView);

        Settings settings = new Settings().withEventsListener(new FplanEventsListener() {
            @Override
            public void onFpConfigured() {
                (new Handler(Looper.getMainLooper())).post(() -> {
                    fplanView.destroy();
                    setContentView(R.layout.activity_main);
                });

            }

            @Override
            public void onFpConfigureError(int i, String s) {
                (new Handler(Looper.getMainLooper())).post(() -> {
                    fplanView.destroy();
                    setContentView(R.layout.activity_main);
                });
            }

            @Override
            public void onBoothClick(@Nullable FloorPlanBoothBase floorPlanBoothBase) {

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


        fplanView.load("https://fieurope2024.expofp.com", settings);*/

        /*(new Handler(Looper.getMainLooper())).post(() -> {
            SharedFplanView.preload("https://fieurope2024.expofp.com", getApplicationContext());

        });*/



    }

    public void categoriesClick(View view) {
        Intent intent = new Intent(this, FplanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void exhibitorsClick(View view) {
        Intent intent = new Intent(this, FplanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void boothsClick(View view) {
        Intent intent = new Intent(this, FplanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void floorplanClick(View view) {

        //setContentView(fplanView);
        Intent intent = new Intent(this, FplanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
