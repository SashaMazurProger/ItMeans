package com.example.sasham.itmeans.data.network;

import com.example.sasham.itmeans.data.WordAssociation;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

/**
 * Created by Sasha M on 15.04.2018.
 */

public interface ITwinWordWebService {
    @GET("/association/")
    Call<WordAssociation> getWordAssociation(@Query("entry") String word);
}
