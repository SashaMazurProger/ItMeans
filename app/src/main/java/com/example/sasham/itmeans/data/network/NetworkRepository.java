package com.example.sasham.itmeans.data.network;

import com.example.sasham.itmeans.data.network.response.WordAssociation;
import com.example.sasham.itmeans.data.network.response.WordDefinition;
import com.example.sasham.itmeans.data.network.response.WordExample;
import com.example.sasham.itmeans.data.network.response.WordReference;
import com.example.sasham.itmeans.data.network.response.WordTheme;

import io.reactivex.Observable;

public interface NetworkRepository {
    Observable<WordAssociation> getWordAssociation(String word);
    Observable<WordDefinition> getWordDefinition(String word);
    Observable<WordExample> getWordExample(String word);
    Observable<WordReference> getWordReference(String word);
    Observable<WordTheme> getWordTheme(String word);
}
