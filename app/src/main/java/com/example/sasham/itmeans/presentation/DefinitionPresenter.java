package com.example.sasham.itmeans.presentation;

import android.support.annotation.NonNull;

/**
 * Created by Sasha M on 10.04.2018.
 */

public class DefinitionPresenter {
    private IDefinitionView definitionView;

    public DefinitionPresenter(@NonNull IDefinitionView definitionView) {
        this.definitionView = definitionView;
    }
    public void init(){
        definitionView.showDefinition("First MVP project");
    }
}
