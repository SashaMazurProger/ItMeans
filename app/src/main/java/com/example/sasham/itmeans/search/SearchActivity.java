package com.example.sasham.itmeans.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.sasham.itmeans.R;

import com.example.sasham.itmeans.databinding.ActivitySearchBinding;
import com.example.sasham.itmeans.favorites.FavoritesActivity;
import com.example.sasham.itmeans.recents.RecentsActivity;
import com.example.sasham.itmeans.util.RxUtils;
import com.example.sasham.itmeans.util.TextUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName();
    public static final String STRING_WORD_EXTRA = "word_extra";
    public static final String SEARCH_WORD = "search_word";
    public static final String START_SEARCH_WORD = "start_search_word";

    private WordDetailsViewModel wordDetailsViewModel;
    private ActivitySearchBinding binding;
    private SearchView search;


    @Inject
    WordDetailsViewModel.Factory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_base);
//        setSupportActionBar(toolbar);

        wordDetailsViewModel = ViewModelProviders.of(this, factory).get(WordDetailsViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setWordModel(wordDetailsViewModel);

        onEmptyResult();
        checkIntent();
    }

    private void checkIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals(SEARCH_WORD)) {
                String word = intent.getStringExtra(STRING_WORD_EXTRA);
                if (word != null && !word.isEmpty())
                    wordDetailsViewModel.search(word);
            }
        }
    }

    private void checkIfNeedFocuseOnSearchView() {
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals(START_SEARCH_WORD)) {
                search.setFocusable(true);
                search.setIconified(false);
                search.requestFocusFromTouch();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        MenuItem favorites = menu.findItem(R.id.action_favorites);
        favorites.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(SearchActivity.this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            }
        });

        MenuItem recents = menu.findItem(R.id.action_recents);
        recents.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(SearchActivity.this, RecentsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        search = (SearchView) menu.findItem(R.id.action_search).getActionView();

        RxUtils.textInputObservable(search)
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(word -> !TextUtils.isNotNullOrEmpty(word.trim()))
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((word) -> wordDetailsViewModel.search(word), (e) -> onEmptyResult());

        checkIfNeedFocuseOnSearchView();

        return true;
    }

////    public void onLoading() {
////        binding.emptyView.setVisibility(View.GONE);
////        binding.resultContainer.setVisibility(View.GONE);
////        binding.progress.setVisibility(View.VISIBLE);
////    }
////
////    public void onSuccess() {
////        binding.emptyView.setVisibility(View.GONE);
////        binding.resultContainer.setVisibility(View.VISIBLE);
////        binding.progress.setVisibility(View.GONE);
////    }

    public void onEmptyResult() {
        Log.d(TAG, "onEmptyResult: ---");
        binding.emptyView.setVisibility(View.VISIBLE);
        binding.resultContainer.setVisibility(View.GONE);
        binding.progress.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        wordDetailsViewModel.dispose();
    }

}
