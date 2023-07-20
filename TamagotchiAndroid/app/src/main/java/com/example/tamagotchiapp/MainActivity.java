package com.example.tamagotchiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static com.example.tamagotchiapp.R.*;
import static com.example.tamagotchiapp.R.layout.*;

public class MainActivity extends AppCompatActivity implements Tamagotchi.Observer{
    int amount;
    ListView listView = null;
    ArrayList<Tamagotchi> tamagotchis = new ArrayList<>();
    ArrayList<Tamagotchi> list = new ArrayList<>();
    ArrayList<Integer> gameOverCheck = new ArrayList<>();
    TamagotchiAdapter adapter;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        adapter = new TamagotchiAdapter(this, list);
        int min = 3;
        int max = 9;
        listView = findViewById(id.list);
        listView.setAdapter(adapter);
        Random tamagotchiAmount = new Random();
        amount = tamagotchiAmount.nextInt(max - min) + min;
        CreateTamagotchis();
    }

    public void CreateTamagotchis(){

        for (num = 0; num < amount; num++){
            Tamagotchi thread = new Tamagotchi(this);
            tamagotchis.add(thread);
            thread.setNumber(num);
            thread.start();
            adapter.add(thread);
            gameOverCheck.add(num);
        }
    }

    @Override
    public void StatusReport(int food) {
        Update(food);
    }


    public void Update (final int food){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                if (food <= 0 || food >= 20){
                    if(!gameOverCheck.isEmpty()) {
                    gameOverCheck.remove(gameOverCheck.size()-1);
                    CheckIfGameOver();
                    }
                }
            }
        });
        }

     public void CheckIfGameOver(){
        if(gameOverCheck.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("Game Over");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
     }

    }



