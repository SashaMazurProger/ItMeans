package com.example.sasham.itmeans.favorites;

import android.arch.lifecycle.ViewModel;

import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import java.util.List;

import javax.inject.Inject;

public class FavoritesViewModel extends ViewModel {

    @Inject
    public FavoritesInteractor favoritesInteractor;

    public List<FavoriteWord> favoriteWords() {
       return favoritesInteractor.allFavoriteWords();
    }

    public void removeFavoriteWord(FavoriteWord favoriteWord) {
        favoritesInteractor.delete(favoriteWord);
    }

//    private void createData() {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.copyToRealm(Arrays.asList(
//                new FavoriteWord("School",new Date().getTime()),
//                new FavoriteWord("Boo",new Date().getTime()),
//                new FavoriteWord("Baa",new Date().getTime()),
//                new FavoriteWord("Dad",new Date().getTime())
//        ));
//        realm.commitTransaction();
//        realm.close();
//    }
//
//    private void addData() {
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults<FavoriteWord> words=realm.where(FavoriteWord.class).findAll();
//        favoriteWords.clear();
//        favoriteWords.addAll(words.subList(0,words.size()));
//        realm.close();
//    }

}
