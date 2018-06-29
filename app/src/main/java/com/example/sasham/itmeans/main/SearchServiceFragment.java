package com.example.sasham.itmeans.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.itmeans.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchServiceFragment extends Fragment {


    public SearchServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_service, container, false);
    }

    public static Fragment newInstance() {
        return new SearchServiceFragment();
    }
}
