package com.example.sasham.itmeans.search;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.sasham.itmeans.data.network.Meaning;
import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WordDetailsViewModel extends ViewModel {
    public enum Status {LOADING, SUCCESS, ERROR}

    public static final String TAG = WordDetailsViewModel.class.getSimpleName();

    private ObservableField<Status> status = new ObservableField<>();

    @Inject
    public WordDetailsInteractor wordRepository;

    private ObservableField<WordDefinition> definition = new ObservableField<>();
    private ObservableField<Meaning> meaning = new ObservableField<>();
    private ObservableField<WordAssociation> association = new ObservableField<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public WordDetailsViewModel() {

    }

    public ObservableField<Meaning> getMeaning() {
        return meaning;
    }

    public ObservableField<WordDefinition> getDefinition() {
        return definition;
    }

    public ObservableField<WordAssociation> getAssociation() {
        return association;
    }

    public ObservableField<Status> getStatus() {
        return status;
    }

    public void search(String word) {
        Log.d(TAG, "search: " + word);

        wordRepository.getWordDefinition(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        status.set(Status.LOADING);
                    }
                })
                .subscribeWith(new Observer<WordDefinition>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WordDefinition value) {
                        definition.set(value);
                        if (value != null && value.getResultCode().equals("200")) {
                            meaning.set(value.getMeaning());
                            status.set(Status.SUCCESS);
                        } else {
                            status.set(Status.ERROR);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        meaning.set(null);
                        definition.set(null);
                        status.set(Status.ERROR);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        wordRepository.getWordAssociation(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<WordAssociation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WordAssociation value) {
                        if (value != null) {
                            association.set(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        association.set(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void dispose() {
        compositeDisposable.clear();
    }
}
