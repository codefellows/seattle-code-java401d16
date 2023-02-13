package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reyjroliva.buystuff.R;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Event Listener for clicking a button
        // TODO: Step 1: Get a UI element by ID
        Button submitButton = (Button) findViewById(R.id.MainActivitySubmitButton);

        // TODO: Step 2: Set OnClickListener
        submitButton.setOnClickListener(v -> {
            //TODO: Step 3: Declare onClick() callback

            //TODO: Step: 4 Define logic to be run
//            System.out.println("Hello, we submitted!");

//            Log.v(TAG, "This is a verbose log");
//            Log.d(TAG, "This is a debug log");
//            Log.i(TAG, "This is a info log");
//            Log.w(TAG, "This is a warning log");
//            Log.e(TAG,"This is a error log");

            TextView nameTextView = (TextView) findViewById(R.id.MainActivityNameTextView);
            nameTextView.setText("My new name");

        });


        Button orderFormIntentButton = (Button) findViewById(R.id.MainActivityOrderFormIntentButton);
        orderFormIntentButton.setOnClickListener(v -> {
            // Need class context that we are coming from, and class that we are going to, so we can navigate back after
            Intent goToOrderFormIntent = new Intent(this, OrderFormActivity.class);
            startActivity(goToOrderFormIntent);
        });


//        Step 2: setOnCLickListener
//        submitButton.setOnClickListener(new View.OnClickListener() {
//        Step 3: Declare onClick() callback
//             @Override
//            public void onClick(View v) {
//                Step 4: Add logic
//            }
//        });
    }
}