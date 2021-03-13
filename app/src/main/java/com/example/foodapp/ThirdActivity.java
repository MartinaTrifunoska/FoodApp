package com.example.foodapp;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodapp.adapters.IngredientsAdapter;
import com.example.foodapp.models.ListMeals;
import com.example.foodapp.models.Meal;
import com.example.foodapp.retrofit.ApiInterface;
import com.example.foodapp.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    TextView txtName, txtCategory, txtArea, txtIngredients, txtInstructions;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtArea = findViewById(R.id.txtArea);
        txtIngredients = findViewById(R.id.txtIngredients);
        txtInstructions = findViewById(R.id.txtInstructions);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        apiInterface = RetrofitClient.getRetrofitInstance();
        displayData(name);
    }

    private void displayData(String name) {
        apiInterface.getDetails(name).enqueue(new Callback<ListMeals>() {
            @Override
            public void onResponse(Call<ListMeals> call, Response<ListMeals> response) {
                ListMeals meals = response.body();
                Meal meal = meals.getMeals().get(0);
                txtName.setText(meal.getStrMeal());
                txtCategory.setText(meal.getStrCategory());
                txtArea.setText(meal.getStrArea());
                txtIngredients.setText(meal.toString());
                txtInstructions.setText(meal.getStrInstructions());
                Glide.with(getBaseContext()).load(meal.getStrMealThumb()).into(imageView);
            }

            @Override
            public void onFailure(Call<ListMeals> call, Throwable t) {
                Toast.makeText(ThirdActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}