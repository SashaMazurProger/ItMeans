package com.example.sasham.itmeans.data.network;

import android.util.Log;

import com.example.sasham.itmeans.data.WordAssociation;
import com.example.sasham.itmeans.data.WordRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkWordRepository implements WordRepository {
    private static final String TAG = NetworkWordRepository.class.getSimpleName();

    private WordAssociation resWordAssociation;

    @Override
    public WordAssociation getWordAssociation(final String word) {
        resWordAssociation = null;

        NetworkModule.getTwinWordWebService()
                .getWordAssociation(word)
                .enqueue(new Callback<WordAssociation>() {
                    @Override
                    public void onResponse(Call<WordAssociation> call, Response<WordAssociation> response) {
                        resWordAssociation = response.body();
                    }

                    @Override
                    public void onFailure(Call<WordAssociation> call, Throwable t) {
                        Log.e(TAG, "onFailure: error", t);
                    }
                });

        return resWordAssociation;
    }
}
