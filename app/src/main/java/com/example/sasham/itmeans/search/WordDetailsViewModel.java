package com.example.sasham.itmeans.search;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sasham.itmeans.data.network.Meaning;
import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;
import com.example.sasham.itmeans.data.network.db.FavoriteWord;
import com.example.sasham.itmeans.data.network.db.RecentWord;

import java.util.Date;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WordDetailsViewModel extends ViewModel {


    public enum Status {LOADING, SUCCESS, ERROR}

    public static final String TAG = WordDetailsViewModel.class.getSimpleName();

    public WordDetailsInteractor detailsInteractor;

    private ObservableField<WordDefinition> definition = new ObservableField<>();
//    private ObservableField<Meaning> meaning = new ObservableField<>();
    private ObservableField<WordAssociation> association = new ObservableField<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ObservableField<Status> status = new ObservableField<>();
    private ObservableBoolean isFavoriteWord = new ObservableBoolean();

    public WordDetailsViewModel(WordDetailsInteractor detailsInteractor) {
        this.detailsInteractor = detailsInteractor;
    }

    private final Observable.OnPropertyChangedCallback checkIfFavoriteCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            switch (getStatus().get()) {
                case SUCCESS:
                    if (isFavorite()) {
                        isFavoriteWord.set(true);
                    } else {
                        isFavoriteWord.set(false);
                    }

                    break;
                default:
                    isFavoriteWord.set(false);
            }
        }
    };


    public WordDetailsViewModel() {
        isFavoriteWord.set(false);
        status.addOnPropertyChangedCallback(checkIfFavoriteCallback);
    }

    public ObservableBoolean getIsFavoriteWord() {
        return isFavoriteWord;
    }

//    public ObservableField<Meaning> getMeaning() {
//        return meaning;
//    }

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

        Disposable definitionDisposable = detailsInteractor.getWordDefinition(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable -> status.set(Status.LOADING)))
                .subscribe(this::onDefinitionFetchSuccess, this::onDefinitionFetchFailed);

        compositeDisposable.add(definitionDisposable);

//        Disposable assocDisposable= detailsInteractor.getWordAssociation(word)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new Observer<WordAssociation>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(WordAssociation value) {
//                        if (value != null) {
//                            association.set(value);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        association.set(null);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private void onDefinitionFetchFailed(Throwable throwable) {
//        meaning.set(null);
        definition.set(null);
        status.set(Status.ERROR);
    }

    private void onDefinitionFetchSuccess(WordDefinition definition) {
        Log.d(TAG, "onDefinitionFetchSuccess: d:"+definition.getMeaning().toString());
        this.definition.set(definition);
        status.set(Status.SUCCESS);
        addRecentWord();
    }

    public void clickAddToFavorite() {
        String word = definition.get().getEntry();
        if ((word != null && !word.isEmpty())) {
            if (isFavorite()) {
                FavoriteWord searchFavoriteWord = detailsInteractor.findByName(word);
                detailsInteractor.delete(searchFavoriteWord);
                isFavoriteWord.set(false);
                Log.d(TAG, "clickAddToFavorite: deleted");
            } else {
                FavoriteWord newFavoriteWord = new FavoriteWord(word, new Date().getTime());
                detailsInteractor.create(newFavoriteWord);
                isFavoriteWord.set(true);
                Log.d(TAG, "clickAddToFavorite: added");
            }
        }
    }

    private void addRecentWord() {
        if (definition.get() == null) return;

        String word = definition.get().getEntry();
        if ((word != null && !word.isEmpty())) {
            Log.d(TAG, "addRecentWord: add");
            RecentWord recentWord = new RecentWord(word, new Date().getTime());
            detailsInteractor.create(recentWord);
        }
    }

    private boolean isFavorite() {
        if (definition.get() == null) return false;

        String word = definition.get().getEntry();
        if ((word != null && !word.isEmpty())) {
            FavoriteWord searchFavoriteWord = detailsInteractor.findByName(word);
            if (searchFavoriteWord != null) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        compositeDisposable.clear();
        detailsInteractor.dispose();
    }

    static class Factory implements ViewModelProvider.Factory {

        private WordDetailsInteractor wordDetailsInteractor;

        public Factory(WordDetailsInteractor wordDetailsInteractor) {
            this.wordDetailsInteractor = wordDetailsInteractor;
        }

        @NonNull
        @Override
        public WordDetailsViewModel create(@NonNull Class modelClass) {
            return new WordDetailsViewModel(wordDetailsInteractor);
        }
    }
}
