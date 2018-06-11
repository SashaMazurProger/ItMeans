package com.example.sasham.itmeans.data.network;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Module
public class OkHttpModule {
    @Singleton
    @Provides
    public OkHttpClient okHttpClient(Interceptor interceptor) {

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public Interceptor interceptor() {
        Interceptor interceptor = new Interceptor() {
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

        return interceptor;
    }
}
