package com.example.sasham.itmeans.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sasha M on 15.04.2018.
 */

public class NetworkModule {

    public static ITwinWordWebService getTwinWordWebService() {
        Interceptor twinWordinterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.addHeader(BuildConfig.TWIN_WORD_ACCEPT_HEADER, BuildConfig.TWIN_WORD_ACCEPT_VALUE);
                requestBuilder.addHeader(BuildConfig.TWIN_WORD_MASHAPE_KEY_HEADER, BuildConfig.TWIN_WORD_MASHAPE_KEY);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.TWIN_WORD_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient(twinWordinterceptor))
                .build();

        return retrofit.create(ITwinWordWebService.class);
    }

    public static OkHttpClient getOkHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(interceptor);
        return okHttpBuilder.build();
    }
}
