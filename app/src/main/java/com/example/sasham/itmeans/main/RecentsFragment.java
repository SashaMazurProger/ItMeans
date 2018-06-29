package com.example.sasham.itmeans.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.data.DataRepository;

import javax.inject.Inject;


public class RecentsFragment extends Fragment {

    @Inject
    public DataRepository dataRepository;

    public RecentsFragment() {
        // Required empty public constructor
    }


    public static RecentsFragment newInstance() {
        RecentsFragment fragment = new RecentsFragment();
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
        View root=inflater.inflate(R.layout.fragment_recents_w, container, false);
        return root;
    }


}
