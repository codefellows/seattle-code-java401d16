package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.buystuff.R;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity {
    SharedPreferences preferences;
    public static final String USER_NICKNAME_TAG = "userNickname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent callingIntent = getIntent();
        String userInputString = null;
        if (callingIntent != null) {
            userInputString = callingIntent.getStringExtra(MainActivity.USER_INPUT_EXTRA_TAG);
        }

        TextView userInputTextView = (TextView)findViewById(R.id.userProfileActivityInputTextView);
        if (userInputString != null) {
            userInputTextView.setText(userInputString);
        } else {
            userInputTextView.setText(R.string.no_input);
        }


        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userNickname = preferences.getString(USER_NICKNAME_TAG, "");
        EditText userNicknameEditText = findViewById(R.id.userProfileActivityNicknameEditTextView);
        userNicknameEditText.setText(userNickname);

        Button saveButton = findViewById(R.id.userProfileActivitySaveButton);
        saveButton.setOnClickListener(v -> {
            SharedPreferences.Editor preferencesEditor = preferences.edit(); // This will FAIL if you declare "preferences in onCreate()
            String userNicknameString = userNicknameEditText.getText().toString();

            preferencesEditor.putString(USER_NICKNAME_TAG, userNicknameString);
            preferencesEditor.apply(); // Nothing will save if you don't call apply()

//            Snackbar.make(findViewById(R.id.userProfileActivity), "Settings saved!", Snackbar.LENGTH_SHORT).show();
            Toast.makeText(UserProfileActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
        });
    }
}