package com.example.sasham.itmeans.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.databinding.ActivityMainBinding;
import com.example.sasham.itmeans.util.RxUtils;
import com.example.sasham.itmeans.viewmodel.WordViewModel;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

//    @Inject
//    WordViewModelFactory viewModelFactory;

    private WordViewModel wordViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_base);
//        setSupportActionBar(toolbar);


        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setWordModel(wordViewModel);

        wordViewModel.getStatus()
                .addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                        switch (wordViewModel.getStatus().get()) {
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

//        RxUtils.textInputObservable(binding.searchView)
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(String s) throws Exception {
//                        return !s.isEmpty();
//                    }
//                })
//                .distinctUntilChanged()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                               @Override
//                               public void accept(String s) throws Exception {
//                                   Log.d(TAG, "accept: ---");
//                                   wordViewModel.search(s);
//                               }
//                           }
//                );

        onError();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        SearchView search=(SearchView) menu.findItem(R.id.action_search).getActionView();

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
                                   wordViewModel.search(s);
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
        wordViewModel.dispose();
    }
}
