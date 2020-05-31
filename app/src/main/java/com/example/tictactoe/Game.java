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


}
