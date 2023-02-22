package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;

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
        findViewById(R.id.MainActivityBttnAddASuperPet).setOnClickListener(v -> {
            Intent goToAddASuperPetIntent = new Intent(this, AddASuperPetActivity.class);
            startActivity(goToAddASuperPetIntent);
                });
    }
}