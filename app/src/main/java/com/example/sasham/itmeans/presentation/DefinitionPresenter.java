package com.example.sasham.itmeans.presentation;

import android.support.annotation.NonNull;

import com.example.sasham.itmeans.data.RepositoryProvider;
import com.example.sasham.itmeans.data.WordAssociation;

/**
 * Created by Sasha M on 10.04.2018.
 */

public class DefinitionPresenter {
    private DefinitionView definitionView;

    public static final String TAG = DefinitionPresenter.class.getSimpleName();

    public DefinitionPresenter(@NonNull DefinitionView definitionView) {
        this.definitionView = definitionView;
    }

    public void init(final String word) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                WordAssociation wordAssociation = RepositoryProvider
                        .getDefaultWordRepository()
                        .getWordAssociation(word);

                setAssociationsWords(wordAssociation);
            }
        }).start();
    }

    private void setAssociationsWords(WordAssociation wordAssociation) {
        definitionView.showDefinition(wordAssociation.toString());
    }

}
