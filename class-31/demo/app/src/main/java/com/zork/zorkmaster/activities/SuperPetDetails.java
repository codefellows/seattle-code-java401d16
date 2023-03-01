package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;

import java.io.File;

public class SuperPetDetails extends AppCompatActivity {
    public final static String TAG = "super_pet_details";
        String superPetName;
        String superPetImageKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_pet_details);
        consumeExtras();

        // consume and call amplify to get the image from the superPet s3Key
        if (superPetImageKey != null && !superPetImageKey.isEmpty()){
            Amplify.Storage.downloadFile(
                    superPetImageKey,
                    new File(getApplication().getFilesDir(), superPetImageKey),
                    success -> {
                        Log.i(TAG, "SUCCESS! Got the image with key: " + superPetImageKey);
                        ImageView superPetImageView = findViewById(R.id.SuperPetDetailsImageViewImage);
                        superPetImageView.setImageBitmap(BitmapFactory.decodeFile(success.getFile().getPath()));
                    },
                    failure -> Log.e(TAG, "FAILED! Unable to get timage from S3 with the key + " + superPetImageKey + " with error " + failure)
            );
        }
    }

    public void consumeExtras(){
        Intent callingIntent = getIntent();
        if (callingIntent != null) {
            superPetName = callingIntent.getStringExtra(SuperPetRecyclerViewAdapter.SUPER_PET_NAME_TAG);
            superPetImageKey = callingIntent.getStringExtra(SuperPetRecyclerViewAdapter.SUPER_PET_IMAGE_KEY_TAG);
        }
        ((TextView)findViewById(R.id.SuperPetDetailsTVName)).setText(superPetName);
    }

}