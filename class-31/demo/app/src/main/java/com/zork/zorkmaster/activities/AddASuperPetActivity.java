package com.zork.zorkmaster.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.*;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddASuperPetActivity extends AppCompatActivity {
    public final static String TAG = "AddASuperPetActivity";
    Spinner superPetTypeSpinner;
    Spinner superOwnerSpinner;
    CompletableFuture<List<SuperOwner>> superOwnersFuture = new CompletableFuture<>();
    ArrayList<String> ownerNames;
    ArrayList<SuperOwner> superOwners;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);

        // WARNING: The ActivityResultLauncher MUST be initialized in onCreate(), not in onResume() or a click handler! Otherwise it will fail
        activityResultLauncher = getImagePickingActivityResultLauncher();
        superPetTypeSpinner = findViewById(R.id.superPetTypeSpinner);
        superOwnerSpinner = findViewById(R.id.AddASuperPetActivityOwnerSpinner);
        ownerNames = new ArrayList<>();
        superOwners = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(SuperOwner.class),
                success -> {
                    Log.i(TAG, "Read superOwners successfully!");
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

    public void setupAddImageButton(){
        // on click listener -> launch the Image picking intent
        findViewById(R.id.AddASuperPetImagePicker).setOnClickListener(v -> {
            launchImageSelectionIntent();
        });
    }

    public void launchImageSelectionIntent(){
        // OnActivityResult
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT); // one of several file picking activities built into Android
        imageFilePickingIntent.setType("*/*");  // only allow one kind or category of file; if you don't have this, you get a very cryptic error about "No activity found to handle Intent"
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});  // only pick JPEG and PNG images

        // Launch Android's built-in file picking activity using our newly created ActivityResultLauncher below
        activityResultLauncher.launch(imageFilePickingIntent);
    }
//
    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher() {
        ActivityResultLauncher imagePickingActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

        )

        return null;
    }

    public void uploadInputStreamToS3(){

    }

    public void saveSuperPet(){

    }

}