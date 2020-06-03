package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Game extends AppCompatActivity {

    TextView winner;
    game_canvas g;
    private String p1, p2;
    gameEngine gameEngine;
    MediaPlayer x_sound;
    MediaPlayer o_sound;
    MediaPlayer draw_sound;
    int flag = 0,mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x_sound = MediaPlayer.create(Game.this,R.raw.x_sound);
        o_sound = MediaPlayer.create(Game.this,R.raw.o_sound);
        draw_sound = MediaPlayer.create(Game.this,R.raw.draw_sound);
        winner = findViewById(R.id.winner);
        Intent i = getIntent();
        p1 = i.getStringExtra("player1_name");
        p2 = i.getStringExtra("player2_name");
        mode = i.getIntExtra("mode",0);

        g = findViewById(R.id.grid);
        gameEngine = new gameEngine();
        g.setGameEngine(gameEngine);
        g.setGame(this);

    }

    public void showAlertDialog(int layout,String winner){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Game.this);
        View layoutView = getLayoutInflater().inflate(R.layout.winner_dialog, null);
        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        TextView win = layoutView.findViewById(R.id.winner);
        dialogBuilder.setView(layoutView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Objects.requireNonNull(alertDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        win.setText(winner);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Game.this,Main_menu.class);
                startActivity(i);
                finish();
                x_sound.stop();
                o_sound.stop();
                draw_sound.stop();
                alertDialog.dismiss();

            }
        });
    }

    public void endGame(int w)
    {

        if(w == 1)
        {
            showAlertDialog(R.layout.winner_dialog,p1 + " is the winner !!");
            x_sound.start();

        }
        else if(w == 2)
        {
            showAlertDialog(R.layout.winner_dialog,p2 + " is the winner !!");
            o_sound.start();

        }
        else if(w == -1)
        {
            showAlertDialog(R.layout.winner_dialog,"It's a Draw !!");
            draw_sound.start();
        }
        else
            assert true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("touched",g.getTouched());
        outState.putStringArray("board",g.getBoard());
        outState.putBooleanArray("cellChecked",g.getCellChecked());
        outState.putInt("game",g.getGame());
        outState.putInt("win",g.getWin());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        g.setTouched(savedInstanceState.getInt("touched"));
        g.setBoard(savedInstanceState.getStringArray("board"));
        g.setCellChecked(savedInstanceState.getBooleanArray("cellChecked"));
        g.setGame(savedInstanceState.getInt("game"));
        g.setWin(savedInstanceState.getInt("win"));

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View layoutView = getLayoutInflater().inflate(R.layout.alert_dialog, null);

        Button nbutton = layoutView.findViewById(R.id.nButton);
        Button pbutton = layoutView.findViewById(R.id.pButton);

        builder.setView(layoutView);

        final AlertDialog alert = builder.create();
        Objects.requireNonNull(alert.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();

         pbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
                 Intent intent = new Intent(Game.this,Main_menu.class);
                 startActivity(intent);
             }
         });

        nbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });





    }
}
