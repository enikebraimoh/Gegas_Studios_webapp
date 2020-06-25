package com.enike.gegasstudios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

        private WebView webView;
        ProgressBar progressBar;
        ProgressDialog progressDialog;
        ImageView mImageView;

        private AdView adView;

    private InterstitialAd mInterstitialAd;

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            progressBar= findViewById(R.id.progress);
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading wait....");

            mImageView = findViewById(R.id.home);


            MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                    adView = findViewById(R.id.adView);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    adView.loadAd(adRequest);
                }
            });

            adView = findViewById(R.id.adView);
            final AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);





            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mInterstitialAd = new InterstitialAd(MainActivity.this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.loadAd(adRequest);

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Toast.makeText(MainActivity.this,"chill first the ad neva load",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            setContentView(R.layout.activity_main);
            webView = findViewById(R.id.webview);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://www.arewacinews.xyz/");

        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){

                case R.id.menu_back:
                    onBackPressed();
                    break;

                case R.id.menu_forwars:
                    onForwardPressed();
                    break;

                case R.id.menu_refresh:
                    webView.reload();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
        private void onForwardPressed(){
            if (webView.canGoForward()){
                webView.goForward();
            } else {
                Toast.makeText(this, "Can't go further", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onBackPressed() {

            if (webView.canGoBack()) {
                webView.goBack();
            }else {
                super.onBackPressed();
            }
        }
    }
