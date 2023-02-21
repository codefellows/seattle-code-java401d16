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
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.amplifyframework.datastore.generated.model.SuperPetTypeEnum;
import com.zork.zorkmaster.R;

import java.util.Date;

public class AddASuperPetActivity extends AppCompatActivity {
    public final static String TAG = "AddASuperPetActivity";
    Spinner superPetTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
        superPetTypeSpinner = findViewById(R.id.superPetTypeSpinner);

        setupTypeSpinner();
        setupSaveBttn();
    }

    public void setupTypeSpinner(){
        superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                SuperPetTypeEnum.values()
        ));
    }

    public void setupSaveBttn(){
        findViewById(R.id.AddASuperPetSaveBttn).setOnClickListener(v -> {
            // TODO implement superPet builder pattern
            SuperPet newSuperPet = SuperPet.builder()
                    .name(((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString())
                    .type((SuperPetTypeEnum) superPetTypeSpinner.getSelectedItem())
                    .height(Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString()))
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