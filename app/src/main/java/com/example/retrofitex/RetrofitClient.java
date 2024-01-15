package com.example.retrofitex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private static UserRetrofitInterface userRetrofitInterface;

    private static String baseUrl = "http://192.168.0.41:8000/";

    private RetrofitClient(){
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRetrofitInterface = retrofit.create(UserRetrofitInterface.class);
    }

    public static RetrofitClient getInstance(){
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static UserRetrofitInterface getUserRetrofitInterface(){
        return userRetrofitInterface;
    }
}
