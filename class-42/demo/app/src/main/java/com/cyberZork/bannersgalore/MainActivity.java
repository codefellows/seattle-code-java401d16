package com.cyberZork.bannersgalore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "main_activity";
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    OkHttpClient client = new OkHttpClient();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        URL url = null;
        Scanner scanner;
        HttpURLConnection httpURLConnection = null;
        InputStream in = null;
        String result = "";
        try {
            url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(httpURLConnection.getInputStream());
            scanner = new Scanner(in);
            result = scanner.hasNext() ? scanner.next() : "";
            System.out.println("THE RESPONSE!" + result);
        } catch (IOException ioe) {
            Log.e(TAG, "ERROR: " + ioe);
        }

    }


}