package com.example.sasham.itmeans.recents;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.itmeans.BR;
import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.adapter.BaseRecyclerAdapter;
import com.example.sasham.itmeans.data.network.db.RecentWord;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentsFragment extends Fragment implements RecentsView {

    @Inject
    public RecentsPresenter recentsPresenter;

    @BindView(R.id.recents_list)
    RecyclerView recentsList;

    private Unbinder unbinder;
    private BaseRecyclerAdapter<RecentWord> recentsAdapter;

    public RecentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recents, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        initRecentsList();

        //Load words
        recentsPresenter.fetchWords();

        return rootView;
    }

    private void initRecentsList() {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recentsAdapter = new BaseRecyclerAdapter<RecentWord>(R.layout.recents_item, BR.recent);

        recentsList.setLayoutManager(layoutManager);
        recentsList.setAdapter(recentsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        recentsPresenter.destroy();
    }

    @Override
    public void showWords(List<RecentWord> words) {
        recentsAdapter.setItems(words);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded() {

    }
}
