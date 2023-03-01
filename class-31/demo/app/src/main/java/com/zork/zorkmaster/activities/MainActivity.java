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
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.authActivities.LoginActivity;
import com.zork.zorkmaster.activities.authActivities.SignUpActivity;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "mainActivity";
    List<SuperPet> superPetList;
    SuperPetRecyclerViewAdapter adapter;
    AuthUser authUser;
    Button addASuperPetButton;
    Button loginButton;
    Button signUpButton;
    Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addASuperPetButton = findViewById(R.id.MainActivityBttnAddASuperPet);
        loginButton = findViewById(R.id.MainActivityButtonLogin);
        signUpButton = findViewById(R.id.MainActivityButtonSignUp);
        logoutButton = findViewById(R.id.MainActivityButtonLogout);

        setupBttns();
        setUpRecyclerView();
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
                        if (databaseSuperPet.getSuperOwner() != null) {
                            if (databaseSuperPet.getSuperOwner().getName().equals(selectedOwnerName)) {
                                superPetList.add(databaseSuperPet);
                            }
                        }
                    }
                    runOnUiThread(() -> adapter.notifyDataSetChanged()); // since this runs asynchronously, the adapter may already have rendered, so we have to tell it to update
                },
                failure -> Log.e(TAG, "FAILED to read superPets from the Datatbase")
        );

        Amplify.Auth.getCurrentUser(
                success -> {
                    Log.i(TAG, "User Authenticated with username: " + success.getUsername());
                    authUser = success;
                    runOnUiThread(this::renderButtons);
                },
                failure -> {
                    Log.w(TAG, "There is no current authenticated User");
                    authUser = null;
                    runOnUiThread(this::renderButtons);
                }
        );

    }

    public void renderButtons(){
        // Conditional Button Rendering
        // if authUser is null -> Show signUp/login bttn and hide logout bttns
        if (authUser != null) {
            // if authUser is notNull -> Show logout and hide signUp/login bttns
//            signUpButton.setVisibility(View.INVISIBLE);
//            loginButton.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
        } else if (authUser == null) {
            signUpButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setUpRecyclerView() {
        superPetList = new ArrayList<>();
        RecyclerView superPetRecyclerView = findViewById(R.id.MainActivityRecyclerViewSuperPet);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        superPetRecyclerView.setLayoutManager(layoutManager);

        adapter = new SuperPetRecyclerViewAdapter(superPetList, this);
        superPetRecyclerView.setAdapter(adapter);
    }

    public void setupBttns() {
        // Add a SuperPet Button
        addASuperPetButton.setOnClickListener(v -> {
            Intent goToAddASuperPetIntent = new Intent(this, AddASuperPetActivity.class);
            startActivity(goToAddASuperPetIntent);
        });
        // Login Button
        loginButton.setOnClickListener(v -> {
            Intent goToLoginActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(goToLoginActivityIntent);
        });
        // Sign Up Button
        signUpButton.setOnClickListener(v -> {
            Intent goToSignUpActivityIntent = new Intent(this, SignUpActivity.class);
            startActivity(goToSignUpActivityIntent);
        });
        // logout Button
        logoutButton.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    success -> {
                        Log.i(TAG, "User successfully logged out.");
                        authUser = null;
                        runOnUiThread(this::renderButtons);
                    }
            );
        });

    }
}