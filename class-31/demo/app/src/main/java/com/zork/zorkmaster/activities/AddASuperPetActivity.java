package com.zork.zorkmaster.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.*;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperOwner;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.amplifyframework.datastore.generated.model.SuperPetTypeEnum;
import com.zork.zorkmaster.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddASuperPetActivity extends AppCompatActivity {
    public final static String TAG = "AddASuperPetActivity";
    private String s3ImageKey = "";
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
        setupSaveButton();
        setupAddImageButton();
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

    public void setupSaveButton(){
        findViewById(R.id.AddASuperPetSaveBttn).setOnClickListener(v -> {
            saveSuperPet();
        });
    }

    public void saveSuperPet(){
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
                    .s3ImageKey(s3ImageKey)
                    // TODO edit schema to include a string for s3Image Key
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newSuperPet),
                    success -> Log.i(TAG, "Made a superPet successfully!"),
                    failure -> Log.e(TAG, "FAILED to make superPet", failure)
            );
            Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
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
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Uri pickedImageFileUri = result.getData().getData();
                        try {
                            InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                            String pickedImageFileName = getFileNameFromUri(pickedImageFileUri);
                            Log.i(TAG, "Successfully got the image: " + pickedImageFileName);
                            uploadInputStreamToS3(pickedImageInputStream, pickedImageFileName, pickedImageFileUri);
                        } catch (FileNotFoundException fnfe) {
                            Log.e(TAG, "Could not get file from picker! " + fnfe);
                        }

                    }
                }
        );

        return imagePickingActivityResultLauncher;
    }

    public void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFileName, Uri pickedImageFileUri){
        // upload to s3
        Amplify.Storage.uploadInputStream(
                pickedImageFileName,
                pickedImageInputStream,
                success -> {
                    Log.i(TAG, "SUCCESS! Uploaded file to S3! Filename is: " + success.getKey());
                    s3ImageKey = pickedImageFileName;
                    ImageView superPetImageView = findViewById(R.id.AddASuperPetImagePicker);
                    InputStream pickedImageInputStreamCopy = null;
                    try {
                        pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
                    } catch (FileNotFoundException fnfe) {
                        Log.e(TAG, "Could not get file stream from URI! " + fnfe.getMessage(), fnfe);
                    }
                    superPetImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
                    // render chosen image to view
                },
                failure -> Log.e(TAG, "FAILED to upload file to S3 with filename: " + pickedImageFileName + " with error: " + failure)
        );
    }

    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

}