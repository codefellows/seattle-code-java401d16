package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;

public class SuperPetDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_pet_details);
        consumeExtras();
    }

    public void consumeExtras(){
        Intent callingIntent = getIntent();
        String superPetName = null;
        String superPetType = null;
        if (callingIntent != null) {
            superPetName = callingIntent.getStringExtra(SuperPetRecyclerViewAdapter.SUPER_PET_NAME_TAG);
            superPetType = callingIntent.getStringExtra(SuperPetRecyclerViewAdapter.SUPER_PET_TYPE_TAG);
        }
        TextView superPetTextViewType = findViewById(R.id.SuperPetDetailsTVType);
        superPetTextViewType.setText(superPetType);

        ((TextView)findViewById(R.id.SuperPetDetailsTVName)).setText(superPetName);
    }



}