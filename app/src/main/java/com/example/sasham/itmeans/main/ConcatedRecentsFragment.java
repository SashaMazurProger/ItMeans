package com.example.sasham.itmeans.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.data.DataRepository;
import com.example.sasham.itmeans.data.db.model.RecentWord;
import com.example.sasham.itmeans.util.DataUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


public class ConcatedRecentsFragment extends Fragment {

    @Inject
    public DataRepository dataRepository;

    List<RecentWord> recentWordList = null;

    @BindView(R.id.concated_recent_words)
    TextView concatedText;

    public ConcatedRecentsFragment() {
        // Required empty public constructor
    }


    public static ConcatedRecentsFragment newInstance() {
        ConcatedRecentsFragment fragment = new ConcatedRecentsFragment();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recents_w, container, false);
        ButterKnife.bind(this, root);
        dataRepository.getAll(RecentWord.class)
                .subscribe((recentWords -> {
                    recentWordList = recentWords;
                    if(recentWordList!=null&&recentWordList.size()>1){
                        setConcatedWords();
                    }
                }));
//        recentWordList = new ArrayList<>(Arrays.asList(
//                new RecentWord("word1", 1),
//                new RecentWord("word2", 1),
//                new RecentWord("word3", 1)
//
//        ));
        setConcatedWords();

        return root;
    }

    private void setConcatedWords() {
        concatedText.setText(DataUtils.concatRecentWords(recentWordList));
    }


}
