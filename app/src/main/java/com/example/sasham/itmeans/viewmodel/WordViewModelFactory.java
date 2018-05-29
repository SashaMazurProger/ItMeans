package com.example.sasham.itmeans.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.sasham.itmeans.domain.WordUseCase;

public class WordViewModelFactory implements ViewModelProvider.Factory {

    private final WordUseCase wordUseCase;

    public WordViewModelFactory(WordUseCase wordUseCase) {
        this.wordUseCase = wordUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(WordViewModel.class)){
            return (T) new WordViewModel();
        }
        else{
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
