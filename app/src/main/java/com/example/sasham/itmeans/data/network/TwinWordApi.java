package com.example.sasham.itmeans.data.network;

import com.example.sasham.itmeans.data.network.response.WordAssociation;
import com.example.sasham.itmeans.data.network.response.WordDefinition;
import com.example.sasham.itmeans.data.network.response.WordExample;
import com.example.sasham.itmeans.data.network.response.WordReference;
import com.example.sasham.itmeans.data.network.response.WordTheme;

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

    @GET("/theme/")
    Observable<WordTheme> getWordTheme(@Query("entry") String word);

    @GET("/reference/")
    Observable<WordReference> getWordReference(@Query("entry") String word);

    @GET("/example/")
    Observable<WordExample> getWordExample(@Query("entry") String word);
}
