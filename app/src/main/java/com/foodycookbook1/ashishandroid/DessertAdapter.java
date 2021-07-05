package com.foodycookbook1.ashishandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodycookbook1.ashishandroid.R;

import java.util.List;

class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.GridViewHolder> {
    private List<Dessert> desserts;
    private Context context;

    public DessertAdapter(Context context, List<Dessert> desserts) {
        this.desserts = desserts;
        this.context = context;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        GridViewHolder viewHolder = new GridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {

        final String id = desserts.get(position).getIdMeal();
        final String meal = desserts.get(position).getStrMeal();
        final String photo = desserts.get(position).getStrMealThumb();

        holder.tvMeal.setText(meal);

        Glide.with(context)
                .load(photo)
                .into(holder.imgMeal);

        holder.imgMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, meal, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, DetilActivity.class);
                i.putExtra("i_idMeal", id);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });



        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    holder.fav.setBackgroundResource(R.drawable.fav2);
                Toast.makeText(context, meal + " Added to favorites", Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return desserts.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvMeal;
        ImageView imgMeal;
        Button fav;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMeal = (TextView) itemView.findViewById(R.id.tv_meal);
            imgMeal = (ImageView) itemView.findViewById(R.id.img_meal);

            fav = (Button) itemView.findViewById(R.id.favbutton);
        }
    }
}
