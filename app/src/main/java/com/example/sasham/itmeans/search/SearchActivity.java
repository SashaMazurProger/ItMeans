package com.example.sasham.itmeans.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sasham.itmeans.BaseApplication;
import com.example.sasham.itmeans.R;

import com.example.sasham.itmeans.databinding.ActivitySearchBinding;
import com.example.sasham.itmeans.favorites.FavoritesActivity;
import com.example.sasham.itmeans.util.RxUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName();
    public static final String STRING_WORD_EXTRA = "word_extra";
    public static final String SEARCH_WORD = "search_word";

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


        //WordDetailsComponent detailsComponent = BaseApplication.get(this).createDetailsComponent();

        wordDetailsViewModel = ViewModelProviders.of(this,factory).get(WordDetailsViewModel.class);
        //detailsComponent.injectWordViewModel(wordDetailsViewModel);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setWordModel(wordDetailsViewModel);
        wordDetailsViewModel.getStatus()
                .addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                        switch (wordDetailsViewModel.getStatus().get()) {
                            case LOADING:
                                onLoading();
                                break;
                            case SUCCESS:
                                onSuccess();
                                break;
                            case ERROR:
                                onError();
                                break;
                            default:
                                onError();
                        }
                    }
                });

        onError();
        checkIntentData();
    }

    private void checkIntentData() {
        Intent intent=getIntent();
        if(intent!=null){
            String action=intent.getAction();
            if(action.equals(SEARCH_WORD)){
                String word=intent.getStringExtra(STRING_WORD_EXTRA);
                if(word!=null&&!word.isEmpty())
                wordDetailsViewModel.search(word);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        MenuItem favorites=menu.findItem(R.id.action_favorites);
        favorites.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(SearchActivity.this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            }
        });

        MenuItem recents=menu.findItem(R.id.action_recents);
        recents.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(SearchActivity.this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            }
        });

        search = (SearchView) menu.findItem(R.id.action_search).getActionView();

        RxUtils.textInputObservable(search)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.isEmpty();
                    }
                })
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) throws Exception {
                                   Log.d(TAG, "accept: ---");
                                   wordDetailsViewModel.search(s);
                               }
                           }
                );

        return true;
    }

    public void onLoading() {
        binding.emptyView.setVisibility(View.GONE);
        binding.resultContainer.setVisibility(View.GONE);
        binding.progress.setVisibility(View.VISIBLE);
    }

    public void onSuccess() {
        binding.emptyView.setVisibility(View.GONE);
        binding.resultContainer.setVisibility(View.VISIBLE);
        binding.progress.setVisibility(View.GONE);
    }

    public void onError() {
        Log.d(TAG, "onError: ---");
        binding.emptyView.setVisibility(View.VISIBLE);
        binding.resultContainer.setVisibility(View.GONE);
        binding.progress.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        wordDetailsViewModel.dispose();
    }

}
