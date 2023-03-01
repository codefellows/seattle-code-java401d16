package com.zork.zorkmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.SuperPetDetails;

import java.util.List;

// TODO Step 1-4: Make a class whose sole purpose is to manage RecyclerViews: a RecyclerView.Adapter
public class SuperPetRecyclerViewAdapter extends RecyclerView.Adapter<SuperPetRecyclerViewAdapter.SuperPetViewHolder> {
    public static final String SUPER_PET_NAME_TAG = "super_pet_name";
    public static final String SUPER_PET_TYPE_TAG = "super_pet_type";
    public static final String SUPER_PET_IMAGE_KEY_TAG = "super_pet_image_key";

    // TODO Step 3-2: // In the RecyclerViewAdapter class, at the top:
    Context callingActivity;


    // TODO Step 2-3: (In main activity and RecyclerViewAdapter) Hand in some data items
    List<SuperPet> superPetList;
    // TODO Step 3-2: // Change the RecyclerViewAdpater constructor to:
    public SuperPetRecyclerViewAdapter(List<SuperPet> superPetList, Context callingActivity) {
        this.superPetList = superPetList;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    // TODO Step 1-7: (In RecyclerViewAdapter.onCreateViewHolder()) Inflate fragment
    public SuperPetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View superPetFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_super_pet, parent, false);
        // TODO Step 1-9: (In RecyclerViewAdapter.onCreateViewHolder()) Attach Fragment to ViewHolder
        return new SuperPetViewHolder(superPetFragment);
    }

    @Override
    // TODO Step 2-4: (In RecyclerViewAdapter.onBindViewHolder()) Bind data items to Fragments inside of ViewHolders
    public void onBindViewHolder(@NonNull SuperPetViewHolder holder, int position) {
        TextView superPetFragNameView = holder.itemView.findViewById(R.id.SuperPetFragmentTextViewName);

        // TODO Step 6-2 refactor the rendering
        SuperPet superPet = superPetList.get(position);
        superPetFragNameView.setText((position+1) + ". " + superPet.getName()
        + "\n" + superPet.getHeight()
        );
        View superPetViewHolder = holder.itemView;
        superPetViewHolder.setOnClickListener(v -> {
            Intent goToSuperPetDetailsIntent = new Intent(callingActivity, SuperPetDetails.class);
            goToSuperPetDetailsIntent.putExtra(SUPER_PET_NAME_TAG, superPet.getName());
            goToSuperPetDetailsIntent.putExtra(SUPER_PET_IMAGE_KEY_TAG, superPet.getS3ImageKey());
            callingActivity.startActivity(goToSuperPetDetailsIntent);
        });
    }

    @Override
    public int getItemCount() {
        return superPetList.size();
    }

    // TODO Step 1-8: (In RecyclerViewAdapter) Make a ViewHolder class to hold a Fragment
    public static class SuperPetViewHolder extends RecyclerView.ViewHolder{
        public SuperPetViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
