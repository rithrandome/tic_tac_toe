package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Main_menu extends AppCompatActivity {

    Button b1,b2;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                showAlertDialog(R.layout.player_name);
            }
        });

    }

    public void showAlertDialog(int layout){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Main_menu.this);
        View layoutView = getLayoutInflater().inflate(layout, null);

        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        final EditText e1 = layoutView.findViewById(R.id.editText);
        final EditText e2 = layoutView.findViewById(R.id.editText2);

        dialogBuilder.setView(layoutView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Objects.requireNonNull(alertDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(e1.getText().toString()) || TextUtils.isEmpty(e2.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter Player's name !!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(Main_menu.this, Game.class);
                    i.putExtra("player1_name", e1.getText().toString());
                    i.putExtra("player2_name", e2.getText().toString());
                    startActivity(i);
                    alertDialog.dismiss();
                    finish();
                }

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("flag",1);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.getInt("flag") == 1)
            showAlertDialog(R.layout.player_name);
    }
}
