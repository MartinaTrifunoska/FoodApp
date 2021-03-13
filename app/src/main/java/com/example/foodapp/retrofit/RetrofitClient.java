package com.example.foodapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static  ApiInterface retrofit;
    private static String BASE_URL = "https://www.themealdb.com/";

    public static ApiInterface getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface.class);
        }
        return retrofit;
    }
}
