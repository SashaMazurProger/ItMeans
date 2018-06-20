package com.example.sasham.itmeans.search;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sasham.itmeans.data.network.response.WordAssociation;
import com.example.sasham.itmeans.data.network.response.WordDefinition;
import com.example.sasham.itmeans.data.db.model.FavoriteWord;
import com.example.sasham.itmeans.data.db.model.RecentWord;
import com.example.sasham.itmeans.data.network.response.WordExample;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordDetailsViewModel extends ViewModel {

    public static final String TAG = WordDetailsViewModel.class.getSimpleName();

    public WordDetailsInteractor detailsInteractor;

    private ObservableField<WordDefinition> definition = new ObservableField<>();
    private ObservableField<WordAssociation> association = new ObservableField<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ObservableBoolean isLoading = new ObservableBoolean(false);
    private ObservableBoolean isSuccess = new ObservableBoolean(false);
    private ObservableBoolean isFavoriteWord = new ObservableBoolean(false);
    private ObservableField<WordExample> example = new ObservableField<>();


    public WordDetailsViewModel(WordDetailsInteractor detailsInteractor) {
        this.detailsInteractor = detailsInteractor;
    }


    public WordDetailsViewModel() {
    }

    public ObservableField<WordExample> getExample() {
        return example;
    }

    public ObservableBoolean getIsFavoriteWord() {
        return isFavoriteWord;
    }

    public ObservableField<WordDefinition> getDefinition() {
        return definition;
    }

    public ObservableField<WordAssociation> getAssociation() {
        return association;
    }

    public ObservableBoolean getIsSuccess() {
        return isSuccess;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void search(String word) {
        Log.d(TAG, "search: " + word);

        Disposable definitionDisposable = detailsInteractor.getWordDefinition(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable -> isLoading.set(true)))
                .subscribe(this::onDefinitionFetchSuccess, this::onDefinitionFetchFailed);

        compositeDisposable.add(definitionDisposable);

        Disposable assocDisposable = detailsInteractor.getWordAssociation(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((wordAssociation -> association.set(wordAssociation)),
                        (throwable -> association.set(null)));

        compositeDisposable.add(assocDisposable);

        Disposable exampleDisposable = detailsInteractor.getWordExample(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((wordExample -> example.set(wordExample)),
                        (throwable -> example.set(null)));

        compositeDisposable.add(exampleDisposable);
    }

    private void onDefinitionFetchFailed(Throwable throwable) {
        Log.e(TAG, "onDefinitionFetchFailed: e", throwable);
        definition.set(null);
        isLoading.set(false);
        isSuccess.set(false);
    }

    private void onDefinitionFetchSuccess(WordDefinition definition) {
        Log.d(TAG, "onDefinitionFetchSuccess: d:" + definition.getMeaning().toString());
        this.definition.set(definition);
        isLoading.set(false);
        isSuccess.set(true);
        if (isFavorite()) {
            isFavoriteWord.set(true);
        } else {
            isFavoriteWord.set(false);
        }
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
