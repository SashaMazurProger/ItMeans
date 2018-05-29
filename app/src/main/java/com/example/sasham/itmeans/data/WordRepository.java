package com.example.sasham.itmeans.data;


import io.reactivex.Observable;

/**
 * Created by Sasha M on 14.04.2018.
 */

public interface WordRepository  {
    Observable<WordAssociation> getWordAssociation(String word);
    Observable<WordDefinition> getWordDefinition(String word);
}
