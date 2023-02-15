package com.zork.zorkmaster.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zork.zorkmaster.R;


// TODO Step 1-6: Make a Fragment class for the RecyclerView ViewHolders
    // Delete all references to Param1 and Param2
    // In the Component Tree on the left, right-click "FrameLayout" -> "Convert FrameLayout to ConstraintLayout", and then click "OK" on the dialog that comes up
    // DON'T delete newInstance()
public class SuperPetFragment extends Fragment {

    public SuperPetFragment() {
        // Required empty public constructor
    }

    public static SuperPetFragment newInstance(String param1, String param2) {
        SuperPetFragment fragment = new SuperPetFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_super_pet, container, false);
    }
}