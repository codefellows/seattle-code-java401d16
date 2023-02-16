package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.database.ZorkMasterDatabase;
import com.zork.zorkmaster.model.SuperPet;

import java.util.Date;

public class AddASuperPetActivity extends AppCompatActivity {
    ZorkMasterDatabase zorkMasterDatabase;
    Spinner superPetTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
        superPetTypeSpinner = findViewById(R.id.superPetTypeSpinner);
        //TODO Step: 5-6 instantiate the DB wherever you need it
        zorkMasterDatabase = Room.databaseBuilder(
                getApplicationContext(),
                ZorkMasterDatabase.class,
                MainActivity.DATABASE_NAME)
                .fallbackToDestructiveMigration()// If Room gets confused, it tosses your database; don't use this in production!
                .allowMainThreadQueries()
                .build();

        setupTypeSpinner();
        setupSaveBttn();
    }

    // TODO Step: 5-4 setup spinner for enum
    public void setupTypeSpinner(){
        superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                SuperPet.SuperPetTypeEnum.values()
        ));
    }

    //TODO Step: 5-5 save superPet to database onClick with the DAO
    public void setupSaveBttn(){
        // set onClick listener on bttn
        findViewById(R.id.AddASuperPetSaveBttn).setOnClickListener(v -> {
            // gather all info
        // when it is clicked, make a new SuperPet
            SuperPet newSuperPet = new SuperPet(
                    ((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString(),
                    SuperPet.SuperPetTypeEnum.fromString(superPetTypeSpinner.getSelectedItem().toString()),
                    new Date(),
                    Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString())
            );
        // Save that superPet to the DB!
            zorkMasterDatabase.superPetDao().insertASuperPet(newSuperPet);
            Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
        });
    }

}