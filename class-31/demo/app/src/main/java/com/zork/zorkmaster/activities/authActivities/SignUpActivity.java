package com.zork.zorkmaster.activities.authActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "sign_up_activity";
    public static final String USER_EMAIL = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupSignUpButton();
    }

    public void setupSignUpButton(){
        findViewById(R.id.SignUpButtonSignUp).setOnClickListener(view -> {
            // gather intel
            String userEmail = ((EditText)findViewById(R.id.SignUpETEmail)).getText().toString();
            String userPassword = ((EditText)findViewById(R.id.SignUpETPassword)).getText().toString();
            // make call to cognito
            Amplify.Auth.signUp(
                userEmail,
                userPassword,
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), userEmail)
                        .userAttribute(AuthUserAttributeKey.nickname(), "Firefly")
                        .build(),
                success -> Log.i(TAG, "Sign Up success!"),
                failure -> Log.e(TAG, "Sign up failed with email: " + userEmail + failure)
                );
            // redirect to Confrim activity
            Intent goToConfirmSignUpActivity = new Intent(this, VerifySignUpActivity.class);
            goToConfirmSignUpActivity.putExtra(USER_EMAIL, userEmail);
            startActivity(goToConfirmSignUpActivity);
        });
    }
}