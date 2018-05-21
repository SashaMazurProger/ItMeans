package com.example.sasham.itmeans.presentation;

import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sasham.itmeans.data.WordAssociation;
import com.example.sasham.itmeans.data.network.BuildConfig;
import com.example.sasham.itmeans.data.network.NetworkModule;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sasha M on 10.04.2018.
 */

public class DefinitionPresenter {
    private IDefinitionView definitionView;

    public static final String TAG = DefinitionPresenter.class.getSimpleName();

    public DefinitionPresenter(@NonNull IDefinitionView definitionView) {
        this.definitionView = definitionView;
    }

    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NetworkModule
                        .getTwinWordWebService()
                        .getWordAssociation("mask")
                        .enqueue(new Callback<WordAssociation>() {
                            @Override
                            public void onResponse(Call<WordAssociation> call, Response<WordAssociation> response) {
                                WordAssociation wordAssociation = response.body();
                                if (wordAssociation != null) {
                                    definitionView.showDefinition(wordAssociation.getAssocWord().get(0));
                                    Log.d(TAG, "run: response:" + wordAssociation.getAssocWord());
                                }
                                else
                                {
                                    Log.d(TAG, "onResponse: null response");
                                }

                            }

                            @Override
                            public void onFailure(Call<WordAssociation> call, Throwable t) {
                                Log.e(TAG, "onFailure: error", t);
                            }
                        });


            }
        }).start();
    }
}
