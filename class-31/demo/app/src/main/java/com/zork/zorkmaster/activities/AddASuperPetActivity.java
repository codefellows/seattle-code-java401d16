package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.model.SuperPet;

import java.util.Date;

public class AddASuperPetActivity extends AppCompatActivity {
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
                SuperPet.SuperPetTypeEnum.values()
        ));
    }

    public void setupSaveBttn(){
        findViewById(R.id.AddASuperPetSaveBttn).setOnClickListener(v -> {
            SuperPet newSuperPet = new SuperPet(
                    ((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString(),
                    SuperPet.SuperPetTypeEnum.fromString(superPetTypeSpinner.getSelectedItem().toString()),
                    new Date(),
                    Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString())
            );
//            zorkMasterDatabase.superPetDao().insertASuperPet(newSuperPet); TODO // COMMENT OUT. THIS IS WHERE AMPLIFY CALLS WILL GO
            Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
        });
    }

}