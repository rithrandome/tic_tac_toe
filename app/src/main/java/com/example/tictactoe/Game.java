package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class Game extends AppCompatActivity {

    Button b1;
    TextView winner;

    private game_canvas g;
    private gameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //g.setActivity(this);
        setContentView(R.layout.activity_main);
        winner = findViewById(R.id.winner);
        g = new game_canvas(this);
        gameEngine = new gameEngine();
        g.setGameEngine(gameEngine);
        g.setGame(this);
    }

    private void showAlertDialog(int layout){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Game.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        dialogBuilder.setView(layoutView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent i = new Intent(Game.this,Main_menu.class);
                startActivity(i);
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void endGame(int winner)
    {
        if(winner == 1)
        {
            this.winner.setText("Player 1 is the winner !!");
        }
        else if(winner == 2)
        {
            this.winner.setText("Player 2 is the winner !!");
        }
        if(winner == 0)
        {
            this.winner.setText("It's a Draw !!");
        }
        showAlertDialog(R.layout.winner_dialog);
    }



}
