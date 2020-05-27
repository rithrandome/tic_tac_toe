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
    List<Integer> p1 = new ArrayList<>();
    List<Integer> p2 = new ArrayList<>();
    List<Integer> game_list = new ArrayList<>();
    List<String> marker=new ArrayList<String>(){{
        add("O");add("X");add("O");add("X");add("O");add("X");add("O");add("X");add("O");add("X");
    }};
    String n1=" ";
    String n2=" ";
    String n3=" ";
    String n4=" ";
    String n5=" ";
    String n6=" ";
    String n7=" ";
    String n8=" ";
    String n9=" ";

    Hashtable<Integer,String> d = new Hashtable<Integer, String>() {{
        put(1,n1);put(2,n2);put(3,n3);put(4,n4);put(5,n5);put(6,n6);put(7,n7);put(8,n8);put(9,n9);
    }};
    private game_canvas g;
    private int i=0;
    private int game;
    private String p="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g.setActivity(this);
        game_canvas gameCanvas = new game_canvas(this);
        setContentView(R.layout.activity_main);
        winner = findViewById(R.id.winner);

    }

    public void set_game(int game)
    {
        this.game = game;
    }

    private void showAlertDialog(int layout){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Game.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        dialogBuilder.setView(layoutView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

    public void setGame(){
        while(i<9) {

//            if game not in range (1, 10){
//                print("Invalid position");
//                continue;
//            }
//            if game in game_list {
//                print('Already filled!!');
//                continue;
//            }
//            else
                game_list.add(game);

            if(i % 2 == 0) {
                p1.add(game);
            }
            else {
                p2.add(game);
            }
            place_marker(d, marker, p, i, game);

            i += 1;
            if(i > 4){
                //if(winner(game_list, p1, p2))
                    break;
            }
            if(i == 9) {
                winner.setText("DRAW");
                break;
            }
        }
    }

    public void place_marker(Hashtable<Integer,String> d, List<String> marker,String p, int i,int pos) {
        g.set_X_O(p);
        if(p.equals("X"))
//            d[pos] = marker[i + 1];
            d.replace(pos,marker.get(i+1));
        else if(p.equals("O"))
//            d[pos] = marker[i];
            d.replace(pos,marker.get(i));
    }
//    @SuppressLint("SetTextI18n")
//    public Boolean winner(List<Integer> game_list, List<Integer> p1, List<Integer> p2) {
//        final ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>(){{
//            add()
//        }};
//
////        final ArrayList<Integer>[] numbers = new ArrayList[]{new ArrayList<>()};
//
//        Hashtable<Integer,ArrayList<Integer>> win = new Hashtable<Integer,ArrayList<Integer>>(){{
//            put(0, numbers.get(0) = new ArrayList<>(Arrays.asList(1, 2, 3)));
//            put(1, numbers[1] = new ArrayList<>(Arrays.asList(1, 4, 7)));
//            put(2, numbers[2] = new ArrayList<>(Arrays.asList(4, 5, 6)));
//            put(3, numbers[3] = new ArrayList<>(Arrays.asList(3, 5, 7)));
//            put(4, numbers[4] = new ArrayList<>(Arrays.asList(1, 5, 9)));
//            put(5, numbers[5] = new ArrayList<>(Arrays.asList(2, 5, 8)));
//            put(6, numbers[6] = new ArrayList<>(Arrays.asList(3, 6, 9)));
//            put(7, numbers[7] = new ArrayList<>(Arrays.asList(7, 8, 9)));
//        }};
//
//
//
//        for (int j = 0; j<8; j++) {
//            if (p1.containsAll(Objects.requireNonNull(win.get(j)))) {
//                winner.setText("Player 1 has won !!");
//                return true;
//            }
//            else if(p2.containsAll(Objects.requireNonNull(win.get(j)))) {
//                winner.setText("Player 2 has won !!");
//                return true;
//            }
//        }
//        return null;
//    }

//    public Boolean issubset(){
//
//
//    }


}
