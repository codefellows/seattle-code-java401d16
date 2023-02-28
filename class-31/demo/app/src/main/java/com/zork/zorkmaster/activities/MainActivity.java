package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.authActivities.LoginActivity;
import com.zork.zorkmaster.activities.authActivities.SignUpActivity;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "mainActivity";
    List<SuperPet> superPetList;
    SuperPetRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBttns();
        setUpRecyclerView();

//        Amplify.Auth.signUp(
//                "alex.white@codefellows.com",
//                "p@ssword",
//                AuthSignUpOptions.builder()
//                        .userAttribute(AuthUserAttributeKey.email(), "alex.white@codefellows.com")
//                        .userAttribute(AuthUserAttributeKey.nickname(), "Firefly")
//                        .build(),
//                success -> Log.i(TAG, "Sign Up success!"),
//                failure -> Log.e(TAG, "Sign up failed with email: alex.white@codefellows.com" + failure)
//                );

//        Amplify.Auth.confirmSignUp(
//                "alex.white@codefellows.com",
//                "212348",
//                success -> Log.i(TAG, "Confirmed signed up success!"),
//                failure -> Log.e(TAG, "confirm sign up up failed with email: alex.white@codefellows.com" + failure)
//        );

//        Amplify.Auth.signIn(
//                "alex.white@codefellows.com",
//                "p@ssword",
//                success -> Log.i(TAG, "signed in success!"),
//                failure -> Log.e(TAG, "sign in failed with email: alex.white@codefellows.com" + failure)
//        );

//        Amplify.Auth.fetchAuthSession(
//                success -> Log.i(TAG, "CURRENT AUTH SESS" + success),
//                failure -> Log.e(TAG, "Failed to fetch auth sess" + failure)
//        );

        // LOGOUT
//        Amplify.Auth.signOut(
//                success -> Log.i(TAG, "SIGNED OUT!!!")
//        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        Amplify.API.query(
                ModelQuery.list(SuperPet.class),
                success -> {
                    superPetList.clear();
                    Log.i(TAG, "Read SuperPets successfully!");
                    for (SuperPet databaseSuperPet : success.getData()) {
                        String selectedOwnerName = "Devon";
                        if (databaseSuperPet.getSuperOwner() != null){
                            if (databaseSuperPet.getSuperOwner().getName().equals(selectedOwnerName)){
                                superPetList.add(databaseSuperPet);
                            }
                        }
                    }
                    runOnUiThread(() -> adapter.notifyDataSetChanged()); // since this runs asynchronously, the adapter may already have rendered, so we have to tell it to update
                },
                failure -> Log.e(TAG, "FAILED to read superPets from the Datatbase")
        );
    }

    public void setUpRecyclerView(){
        superPetList = new ArrayList<>();
        RecyclerView superPetRecyclerView = findViewById(R.id.MainActivityRecyclerViewSuperPet);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        superPetRecyclerView.setLayoutManager(layoutManager);

        adapter = new SuperPetRecyclerViewAdapter(superPetList, this);
        superPetRecyclerView.setAdapter(adapter);
    }

    public void setupBttns(){
        // Add a super pet Button
        findViewById(R.id.MainActivityBttnAddASuperPet).setOnClickListener(v -> {
            Intent goToAddASuperPetIntent = new Intent(this, AddASuperPetActivity.class);
            startActivity(goToAddASuperPetIntent);
                });

        AtomicReference<String> username = new AtomicReference<>("");
        // we need to get access to current auth user
        Amplify.Auth.getCurrentUser(
                success -> {
                    Log.i(TAG, "Got current user");
                    username.set(success.getUsername());
                },
                failure -> {}
        );

        if (username.toString().equals("")) {
        // if auth user is null
            // show sign up and login buttons
            ((Button)findViewById(R.id.MainActivityButtonSignUp)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.MainActivityButtonLogin)).setVisibility(View.VISIBLE);
            // hide logout bttn
        } else {
            ((Button)findViewById(R.id.MainActivityButtonSignUp)).setVisibility(View.INVISIBLE);
            ((Button)findViewById(R.id.MainActivityButtonLogin)).setVisibility(View.INVISIBLE);
            // show logout bttn
        }
        // else
            // only show logout button


        // Login Button
        findViewById(R.id.MainActivityButtonLogin).setOnClickListener(v -> {
            Intent goToLoginActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(goToLoginActivityIntent);
        });

        // Sign Up Button
        findViewById(R.id.MainActivityButtonSignUp).setOnClickListener(v -> {
            Intent goToSignUpActivityIntent = new Intent(this, SignUpActivity.class);
            startActivity(goToSignUpActivityIntent);
        });

        // logout Button

    }
}