package com.example.foodapp;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.adapters.CategoriesAdapter;
import com.example.foodapp.adapters.IngredientsAdapter;
import com.example.foodapp.adapters.PopularAdapter;
import com.example.foodapp.models.Ingredient;
import com.example.foodapp.models.ListMeals;
import com.example.foodapp.models.Meal;
import com.example.foodapp.retrofit.ApiInterface;
import com.example.foodapp.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerPopular, recyclerCategories;
    PopularAdapter popularAdapter;
    CategoriesAdapter categoriesAdapter;
    List<Ingredient> ingredients;
    List<String> categories;
    EditText search;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                    String name = search.getText().toString();
                    if(!name.isEmpty()){
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        search.setText("");
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void init() {
        ingredients = new ArrayList<>();
        categories = new ArrayList<>();

        categories.add("Beef");
        categories.add("Breakfast");
        categories.add("Chicken");
        categories.add("Dessert");
        categories.add("Goat");
        categories.add("Lamb");
        categories.add("Miscellaneous");
        categories.add("Pasta");
        categories.add("Pork");
        categories.add("Seafood");
        categories.add("Side");
        categories.add("Starter");
        categories.add("Vegan");
        categories.add("Vegetarian");

        ingredients.add(new Ingredient("https://www.themealdb.com/images/ingredients/Chicken.png", "Chicken"));
        ingredients.add(new Ingredient("https://www.themealdb.com/images/ingredients/Salmon.png", "Salmon"));
        ingredients.add(new Ingredient("https://www.themealdb.com/images/ingredients/Beef.png" , "Beef"));
        ingredients.add(new Ingredient("https://www.themealdb.com/images/ingredients/Pork.png", "Pork"));

        recyclerPopular = findViewById(R.id.recyclerPopular);
        popularAdapter = new PopularAdapter(ingredients, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerPopular.setLayoutManager(layoutManager);
        recyclerPopular.setAdapter(popularAdapter);

        recyclerCategories = findViewById(R.id.recyclerCategories);
        categoriesAdapter = new CategoriesAdapter(categories, this);
        recyclerCategories.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerCategories.setAdapter(categoriesAdapter);

        search = findViewById(R.id.search);
    }
}