package com.example.sasham.itmeans.presentation;

import android.support.annotation.NonNull;

import com.example.sasham.itmeans.data.WordAssociation;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sasha M on 10.04.2018.
 */

public class DefinitionPresenter {
    private DefinitionView definitionView;

    public static final String TAG = DefinitionPresenter.class.getSimpleName();
    private Disposable disposable;

    public DefinitionPresenter(@NonNull DefinitionView definitionView) {
        this.definitionView = definitionView;
    }

    public void init(final String word) {

    }

    public void setAssociationsWords(WordAssociation wordAssociation) {
        definitionView.showDefinition(wordAssociation.toString());
    }

}
