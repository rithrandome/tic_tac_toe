package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main_menu extends AppCompatActivity {

    Button b1,b2,s_p,d_p;
    private int flag = 0, mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        b1 = findViewById(R.id.play);
        b2 = findViewById(R.id.button2);
        //s_p = findViewById(R.id.s_player);
        d_p = findViewById(R.id.d_player);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                //b1.setVisibility(View.INVISIBLE);
                //s_p.setVisibility(View.VISIBLE);
                //d_p.setVisibility(View.INVISIBLE);

//                s_p.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mode = 1;
//                    }
//                });

// 
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
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setLayout(300,200);
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
                    i.putExtra("mode",mode);
                    alertDialog.dismiss();
                    startActivity(i);
                    finish();
                }

            }
        });

        dialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                flag = 0;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("flag",flag);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.getInt("flag") == 1)
            flag = 1;
            showAlertDialog(R.layout.player_name);
    }

}
