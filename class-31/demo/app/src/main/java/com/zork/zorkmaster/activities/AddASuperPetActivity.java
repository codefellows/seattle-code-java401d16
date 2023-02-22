package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperOwner;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.amplifyframework.datastore.generated.model.SuperPetTypeEnum;
import com.zork.zorkmaster.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddASuperPetActivity extends AppCompatActivity {
    public final static String TAG = "AddASuperPetActivity";
    Spinner superPetTypeSpinner;
    // Todo Step: 1-3 setup owner spinner
    Spinner superOwnerSpinner;
    // Todo Step: 1-4 implement CompletableFuture
    CompletableFuture<List<SuperOwner>> superOwnersFuture = new CompletableFuture<>();
    ArrayList<String> ownerNames;
    ArrayList<SuperOwner> superOwners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
        superPetTypeSpinner = findViewById(R.id.superPetTypeSpinner);
        superOwnerSpinner = findViewById(R.id.AddASuperPetActivityOwnerSpinner);
        ownerNames = new ArrayList<>();
        superOwners = new ArrayList<>();
        // Todo Step: 1-2 hardcode/add 4 SuperOwners to AWS db
        // Builder Pattern
//        SuperOwner sharmarke = SuperOwner.builder()
//                .name("Sharmarke")
//                .build();
//        SuperOwner devon = SuperOwner.builder()
//                .name("Devon")
//                .build();
//        SuperOwner adrian = SuperOwner.builder()
//                .name("Adrian")
//                .build();
//        SuperOwner ryan = SuperOwner.builder()
//                .name("Ryan")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(sharmarke),
//                success -> {},
//                failure -> {});

        // TODO query and setup a spinner for super owners
        // Compeletable Future
        Amplify.API.query(
                ModelQuery.list(SuperOwner.class),
                success -> {
                    Log.i(TAG, "Read superOWners successfully!");
                    for (SuperOwner databaseSuperOwners : success.getData()) {
                        ownerNames.add(databaseSuperOwners.getName());
                        superOwners.add(databaseSuperOwners);
                    }
                    superOwnersFuture.complete(superOwners);
                    runOnUiThread(this::setupSpinners);
                },
                failure -> {
                    superOwnersFuture.complete(null);
                    Log.e(TAG, "FAILED to read superOwners" + failure);
                }
        );
        setupSaveBttn();
    }

    public void setupSpinners(){
        superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                SuperPetTypeEnum.values()
        ));
        superOwnerSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ownerNames
        ));
    }

    public void setupSaveBttn(){
        // TODO step: 1-5 attempt to save a new SuperPet with associated SuperOwner to AWS db
        findViewById(R.id.AddASuperPetSaveBttn).setOnClickListener(v -> {
            String selectedSuperOwnerStringName = superOwnerSpinner.getSelectedItem().toString();
            try {
                superOwners = (ArrayList<SuperOwner>) superOwnersFuture.get();
            } catch (InterruptedException | ExecutionException ie) {
                ie.printStackTrace();
            }

            SuperOwner selectedOwner = superOwners.stream().filter(owner -> owner.getName().equals(selectedSuperOwnerStringName)).findAny().orElseThrow(RuntimeException::new);

            SuperPet newSuperPet = SuperPet.builder()
                    .name(((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString())
                    .type((SuperPetTypeEnum) superPetTypeSpinner.getSelectedItem())
                    .height(Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString()))
                    .superOwner(selectedOwner)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newSuperPet),
                    success -> Log.i(TAG, "Made a superPet successfully!"),
                    failure -> Log.e(TAG, "FAILED to make superPet", failure)
            );
            Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
        });
    }

}