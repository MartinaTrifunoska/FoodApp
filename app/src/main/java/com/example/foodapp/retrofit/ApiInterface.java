package com.example.foodapp.retrofit;

import com.example.foodapp.models.ListMeals;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/json/v1/1/filter.php")
    Call<ListMeals> getAllByIngredient(@Query("i") String strIngredient1);

    @GET("/api/json/v1/1/search.php")
    Call<ListMeals> getAllByName(@Query("s") String strMeal);

    @GET("/api/json/v1/1/filter.php")
    Call<ListMeals> getAllByCategory(@Query("c") String strCategory);

    @GET("/api/json/v1/1/search.php")
    Call<ListMeals> getDetails(@Query("s") String strMeal);
}
