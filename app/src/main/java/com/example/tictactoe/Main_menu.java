package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class Main_menu extends AppCompatActivity {

    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        b1 = findViewById(R.id.button);
        b1 = findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(R.layout.player_name);
//                Intent intent = new Intent(Main_menu.this,Game.class);
////                startActivity(intent);
////                finish();
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
                Intent i = new Intent(Main_menu.this,Game.class);
                i.putExtra("player1_name",e1.getText().toString());
                i.putExtra("player2_name",e2.getText().toString());
                startActivity(i);
                alertDialog.dismiss();
                finish();


            }
        });
    }
}
