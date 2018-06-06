package com.example.sasham.itmeans.search;

import dagger.Component;
import dagger.Subcomponent;

@DetailsScope
@Subcomponent(modules = {WordDetailsModule.class})
public interface WordDetailsComponent {
    void injectWordViewModel(WordDetailsViewModel wordDetailsViewModel);
}
