package com.example.tamagotchiapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TamagotchiAdapter extends ArrayAdapter<Tamagotchi> {
    static final int VIEW_TYPE_TAMAGOTCHI = 0;

    public TamagotchiAdapter(@NonNull Context context, @NonNull ArrayList<Tamagotchi> list) {
        super(context, 0, list);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_TAMAGOTCHI;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int layoutId;
        final Tamagotchi tamagotchi = getItem(position);
        layoutId = R.layout.list_item_tamagotchi;

        convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        TextView title = convertView.findViewById(R.id.TamagotchiName);
        TextView food = convertView.findViewById(R.id.FoodAmount);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tamagotchi.getFood() > 0 && tamagotchi.getFood() < 20) {
                    tamagotchi.addFood();
                    notifyDataSetChanged();
                }
            }
        });
        if(tamagotchi.getFood() <= 0 || tamagotchi.getFood() >= 20){
            convertView.setBackgroundColor(Color.RED);
            tamagotchi.setTitle("DEAD");
            title.setText(tamagotchi.getTitle());
            food.setText(tamagotchi.getFoodText() + tamagotchi.getFood());

        }
        else {
            tamagotchi.setTitle("Tamagotchi");
            title.setText(tamagotchi.getTitle());
            food.setText(tamagotchi.getFoodText() + tamagotchi.getFood());
            convertView.setClickable(true);
        }


    return convertView;
    }
}

