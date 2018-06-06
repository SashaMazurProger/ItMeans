package com.example.sasham.itmeans.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
