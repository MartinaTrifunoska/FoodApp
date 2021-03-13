package com.example.foodapp;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.adapters.IngredientsAdapter;
import com.example.foodapp.models.ListMeals;
import com.example.foodapp.models.Meal;
import com.example.foodapp.retrofit.ApiInterface;
import com.example.foodapp.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");
        String category = intent.getStringExtra("category");
        String name = intent.getStringExtra("name");

        apiInterface = RetrofitClient.getRetrofitInstance();

        if(apiInterface != null){
            if(ingredient != null && !ingredient.isEmpty()){
                displayDataIngredient(ingredient);
            }

            if(category != null && !category.isEmpty()){
                displayDataCategory(category);
            }

            if(name != null && !name.isEmpty()){
                displayDataName(name);
            }
        }
    }

    private void displayDataName(String name) {
        apiInterface.getAllByName(name).enqueue(new Callback<ListMeals>() {
            @Override
            public void onResponse(Call<ListMeals> call, Response<ListMeals> response) {
                if(response.isSuccessful()){
                    ListMeals meals = response.body();
                    recyclerView = findViewById(R.id.recyclerView);
                    adapter = new IngredientsAdapter(meals.getMeals(), getBaseContext(),SecondActivity.this);
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(SecondActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ListMeals> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayDataCategory(String category) {
        apiInterface.getAllByCategory(category).enqueue(new Callback<ListMeals>() {
            @Override
            public void onResponse(Call<ListMeals> call, Response<ListMeals> response) {
                if(response.isSuccessful()){
                    ListMeals meals = response.body();
                    recyclerView = findViewById(R.id.recyclerView);
                    adapter = new IngredientsAdapter(meals.getMeals(), getBaseContext(),SecondActivity.this);
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(SecondActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ListMeals> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayDataIngredient(String ingredient) {
        apiInterface.getAllByIngredient(ingredient).enqueue(new Callback<ListMeals>() {
            @Override
            public void onResponse(Call<ListMeals> call, Response<ListMeals> response) {
                if(response.isSuccessful()){
                    ListMeals meals = response.body();
                    recyclerView = findViewById(R.id.recyclerView);
                    adapter = new IngredientsAdapter(meals.getMeals(), getBaseContext(),SecondActivity.this);
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(SecondActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ListMeals> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}