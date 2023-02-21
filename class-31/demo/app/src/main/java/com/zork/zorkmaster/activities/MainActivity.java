package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;
import com.zork.zorkmaster.model.SuperPet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<SuperPet> superPetList;
    SuperPetRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        superPetList = new ArrayList<>();

        superPetList.add(new SuperPet("Test", SuperPet.SuperPetTypeEnum.FIRE, new Date(), 20));
        superPetList.add(new SuperPet("Test", SuperPet.SuperPetTypeEnum.FIRE, new Date(), 20));

        setupBttns();
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        superPetList.clear();
//        superPetList.addAll(zorkMasterDatabase.superPetDao().findAll()); TODO amplify call goes here
        adapter.notifyDataSetChanged();
    }

    public void setUpRecyclerView(){

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