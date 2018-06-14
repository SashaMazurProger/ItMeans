package com.example.sasham.itmeans.favorites;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import java.util.List;

import javax.inject.Inject;

public class FavoritesViewModel extends ViewModel {

    public FavoritesInteractor favoritesInteractor;

    public List<FavoriteWord> favoriteWords() {
       return favoritesInteractor.allFavoriteWords();
    }

    public FavoritesViewModel(FavoritesInteractor favoritesInteractor) {
        this.favoritesInteractor = favoritesInteractor;
    }

    public void removeFavoriteWord(FavoriteWord favoriteWord) {
        favoritesInteractor.delete(favoriteWord);
    }

    public void dispose(){
        favoritesInteractor.dispose();
    }

    public void removeAllFavoriteWords() {
        favoritesInteractor.removeAllFavoriteWords();
    }

    static class Factory implements ViewModelProvider.Factory{

        private FavoritesInteractor favoritesInteractor;

        public Factory(FavoritesInteractor favoritesInteractor) {
            this.favoritesInteractor = favoritesInteractor;
        }

        @NonNull
        @Override
        public FavoritesViewModel  create(@NonNull Class modelClass) {
            return new FavoritesViewModel(favoritesInteractor);
        }
    }

}
