package com.reyjroliva.buystuff.activities;

import static com.reyjroliva.buystuff.activities.UserProfileActivity.USER_NICKNAME_TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.reyjroliva.buystuff.R;

public class MainActivity extends AppCompatActivity {
    public static final String USER_INPUT_EXTRA_TAG = "userInput"; // at top of the class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView settingsButton = (ImageView) findViewById(R.id.mainActivitySettingsImageView);
        settingsButton.setOnClickListener(v -> {
            String userInput = ((EditText)findViewById(R.id.mainActivityUserInputEditText)).getText().toString();

            Intent goToUserSettingsIntent = new Intent(this, UserProfileActivity.class);
            goToUserSettingsIntent.putExtra(USER_INPUT_EXTRA_TAG, userInput);
            startActivity(goToUserSettingsIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userNickname = preferences.getString(USER_NICKNAME_TAG, "no nickname");
        ((TextView)findViewById(R.id.mainActivityNicknameTextView)).setText(userNickname);
    }
}