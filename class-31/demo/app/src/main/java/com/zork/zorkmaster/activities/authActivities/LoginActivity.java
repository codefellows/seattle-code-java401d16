package com.zork.zorkmaster.activities.authActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.MainActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "login_activity";
    Intent callingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callingActivity = getIntent();

        setupLoginButton();
    }

    public void setupLoginButton(){
        findViewById(R.id.LoginButtonLogin).setOnClickListener(v -> {
            // gather intel
            String userEmail = ((EditText)findViewById(R.id.LoginETEmail)).getText().toString();
            String userPassword = ((EditText)findViewById(R.id.LoginETPassword)).getText().toString();
            // make a call to cognito
            Amplify.Auth.signIn(
                    userEmail,
                    userPassword,
                    success -> Log.i(TAG, "Successfully logged in user: " + userEmail),
                    failure -> Log.e(TAG, "FAILED to login user: " + userEmail + "with error code: " + failure)
            );
            // redirect to  Main Activity
            // hide login and signup on success!
            findViewById(R.id.MainActivityButtonSignUp).setVisibility(View.INVISIBLE);
            findViewById(R.id.MainActivityButtonLogin).setVisibility(View.INVISIBLE);
            Intent goToMainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(goToMainActivityIntent);
        });
    }
}