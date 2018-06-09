package com.example.sasham.itmeans.search;

import dagger.Component;
import dagger.Subcomponent;

@DetailsScope
@Subcomponent(modules = {WordDetailsModule.class})
public interface WordDetailsComponent {

    @Subcomponent.Builder
    interface Builder{
        WordDetailsComponent.Builder wordDetailsModule(WordDetailsModule wordDetailsModule);
        WordDetailsComponent build();
    }
    void injectWordViewModel(WordDetailsViewModel wordDetailsViewModel);
}
