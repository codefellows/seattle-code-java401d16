package com.zork.zorkmaster.activities.authActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;

public class VerifySignUpActivity extends AppCompatActivity {
    public static final String TAG = "verify_sign_up_activity";
    Intent callingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sign_up);
        callingIntent = getIntent();

        setupConfirmButton();
    }

    public void setupConfirmButton(){
        findViewById(R.id.VerifySignUpButtonConfirm).setOnClickListener(view -> {
            // gather intel
            String userEmail = "";
            if (callingIntent != null){
                userEmail = callingIntent.getStringExtra(SignUpActivity.USER_EMAIL);
            }
            String confirmationCode = ((EditText) findViewById(R.id.VerifySignUpETConfirmCode)).getText().toString();
            // make call to cognito
            String finalUserEmail = userEmail;
            Amplify.Auth.confirmSignUp(
                    userEmail,
                    confirmationCode,
                    success -> Log.i(TAG, "Confirmation successful with user: " + finalUserEmail),
                    failure -> Log.e(TAG, "FAILED to verify confirmation code: " + confirmationCode + " for user: " + finalUserEmail + "with failure: " + failure)
            );
            Intent goToLoginActivityIntent = new Intent(this, LoginActivity.class);
            goToLoginActivityIntent.putExtra(SignUpActivity.USER_EMAIL, userEmail);
            startActivity(goToLoginActivityIntent);
        });
    }
}