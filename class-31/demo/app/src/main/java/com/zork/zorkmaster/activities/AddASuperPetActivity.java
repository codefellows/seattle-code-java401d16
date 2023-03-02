package com.zork.zorkmaster.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.zork.zorkmaster.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
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
    //TODO: Step 1-1 Add gradle dependency and request perissions in AndroidManifest
    //TODO: Step 1-2 setup fusedLocationProviderClient
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Geocoder geocoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
        //TODO: Step 1-2 initialize fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());

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
//        getLocation();
        getLocationsUpdates();
    }

    private void getLocation() {
        // TODO 1-3 implement getLastLocation
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location == null) {
                Log.e(TAG, "Location callback was null");
            }
            String currentLatitude = Double.toString(location.getLatitude());
            String currentLongitude = Double.toString(location.getLongitude());
            Log.i(TAG, "Our Latitude: " + currentLatitude);
            Log.i(TAG, "Our Longitude: " + currentLongitude);
        });

        // TODO 1-4 implement getCurrentLocation -> more up to date and accurate -> Downside is it is ACTIVE which means using resources
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }

            @Override
            public boolean isCancellationRequested() {
                return false;
            }
        }).addOnSuccessListener(location -> {
            if (location == null) {
                Log.e(TAG, "Location callback was null");
            }
            String currentLatitude = Double.toString(location.getLatitude());
            String currentLongitude = Double.toString(location.getLongitude());
            Log.i(TAG, "Our Latitude: " + currentLatitude);
            Log.i(TAG, "Our Longitude: " + currentLongitude);
        });
    }

    private void getLocationsUpdates() {
        //TODO Step 1-5 implement requestLocationUpdates with our Geocoder
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // grab lat and long
                try {
                // create a geogoder(String of address aka human readable)
                    String address = geocoder.getFromLocation(
                                    locationResult.getLastLocation().getLatitude(),
                                    locationResult.getLastLocation().getLongitude(),
                                    1) // 1 ->  give the best result
                            .get(0)
                            .getAddressLine(0); // first line of the address
                    Log.i(TAG, "Repeating current location is: " + address);
                } catch (IOException ioe) {
                    Log.i(TAG, "Could not get location: " + ioe);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
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