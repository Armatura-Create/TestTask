package com.example.alex.testtask.client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static final String ROOT_URL = "http://164.132.224.6";
    private static Retrofit retrofit;

    private static Retrofit getRetrofitInstance() {
        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    static API getAPI() {
        return getRetrofitInstance().create(API.class);
    }
}
