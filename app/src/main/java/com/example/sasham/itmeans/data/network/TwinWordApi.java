package com.example.sasham.itmeans.data.network;

import com.example.sasham.itmeans.data.WordAssociation;
import com.example.sasham.itmeans.data.WordDefinition;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

/**
 * Created by Sasha M on 15.04.2018.
 */

public interface TwinWordApi {
    @GET("/association/")
    Observable<WordAssociation> getWordAssociation(@Query("entry") String word);

    @GET("/definition/")
    Observable<WordDefinition> getWordDefinition(@Query("entry") String word);
}
