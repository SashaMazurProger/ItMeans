package com.example.sasham.itmeans.domain;

import com.example.sasham.itmeans.data.WordAssociation;
import com.example.sasham.itmeans.data.WordDefinition;
import com.example.sasham.itmeans.data.WordRepository;
import com.example.sasham.itmeans.data.network.NetworkWordRepository;


import javax.inject.Inject;

import io.reactivex.Observable;

public class WordUseCase {

    public final WordRepository wordRepository;

    @Inject
    public WordUseCase(NetworkWordRepository networkWordRepository) {
        this.wordRepository =networkWordRepository;
    }

    public Observable<WordDefinition> executeDefinition(String word){
        return wordRepository.getWordDefinition(word);
    }

    public Observable<WordAssociation> executeAssociation(String word){
        return wordRepository.getWordAssociation(word);
    }
}
