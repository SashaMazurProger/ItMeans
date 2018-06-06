package com.example.sasham.itmeans.favorites;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;

import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class FavoritesViewModel extends ViewModel{

    private ObservableArrayList<FavoriteWord> favoriteWords;

    public ObservableArrayList<FavoriteWord> favoriteWords(){
        favoriteWords= new ObservableArrayList<>();
        favoriteWords.addAll(Arrays.asList(
                new FavoriteWord("Sasha",new Date().getTime()),
                new FavoriteWord("Max",new Date().getTime()),
                new FavoriteWord("Dima",new Date().getTime()),
                new FavoriteWord("Mom",new Date().getTime())
        ));

        Collections.sort(favoriteWords, new Comparator<FavoriteWord>() {
            @Override
            public int compare(FavoriteWord o1, FavoriteWord o2) {
                long t1=o1.getTimeStamp(), t2=o2.getTimeStamp();
                if(t1>t2){
                    return 1;
                }
                else if(t2>t1){
                    return -1;
                }
                return 0;
            }
        });

        return favoriteWords;
    }

}
