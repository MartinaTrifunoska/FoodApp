package com.example.foodapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.SecondActivity;
import com.example.foodapp.ThirdActivity;
import com.example.foodapp.models.Ingredient;
import com.example.foodapp.models.Meal;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    List<Meal> mealList;
    Context context;
    SecondActivity activity;

    public IngredientsAdapter(List<Meal> mealList, Context context, SecondActivity activity) {
        this.mealList = mealList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull

    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_row, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IngredientsAdapter.IngredientsViewHolder holder, int position) {
        holder.txtName.setText(mealList.get(position).getStrMeal());
        Glide.with(context).load(mealList.get(position).getStrMealThumb()).into(holder.imageView);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThirdActivity.class);
                intent.putExtra("name", holder.txtName.getText().toString());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtName;
        ConstraintLayout parent;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.txtName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
