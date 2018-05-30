package com.example.sasham.itmeans.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {OkHttpModule.class})
public class ApiModule {

    @Provides
    public TwinWordApi twinWordApi(Retrofit retrofit){
        return retrofit.create(TwinWordApi.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory converterFactory){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.TWIN_WORD_BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder=new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }
}
