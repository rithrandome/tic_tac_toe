package com.example.tictactoe;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class gameEngine {

    private int i;
    private List<Integer> p1 = new ArrayList<>();
    private List<Integer> p2 = new ArrayList<>();
    private List<Integer> game_list = new ArrayList<>();
    private List<String> marker=new ArrayList<String>(){{
        add("O");add("X");add("O");add("X");add("O");add("X");add("O");add("X");add("O");add("X");
    }};
    private String n1=" ";
    private String n2=" ";
    private String n3=" ";
    private String n4=" ";
    private String n5=" ";
    private String n6=" ";
    private String n7=" ";
    private String n8=" ";
    private String n9=" ";

    Hashtable<Integer,String> d = new Hashtable<Integer, String>() {{
        put(1,n1);put(2,n2);put(3,n3);put(4,n4);put(5,n5);put(6,n6);put(7,n7);put(8,n8);put(9,n9);
    }};
    private String p="X";


    String getP(){return this.p;}

    public void setP(String p){this.p = p;}

    List<Integer> getP1(){return this.p1;}
    List<Integer> getP2(){return this.p2;}
    List<Integer> getGame_list(){return this.game_list;}

    public void startGame(int game)
    {
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

        }
    }

    private void place_marker(Hashtable<Integer, String> d, List<String> marker, String p, int i, int pos) {
        if(p.equals("X")) {
//            d[pos] = marker[i + 1];
            d.replace(pos, marker.get(i + 1));
            setP(d.get(pos));
        }
        else if(p.equals("O")) {
//            d[pos] = marker[i];
            d.replace(pos, marker.get(i));
            setP(d.get(pos));
        }
    }

    int endGame(List<Integer> game_list, List<Integer> p1, List<Integer> p2) {
//        final ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>(){{
//            add()
//        }};

        final ArrayList<Integer>[] numbers = new ArrayList[]{new ArrayList<>()};

        Hashtable<Integer,ArrayList<Integer>> win = new Hashtable<Integer,ArrayList<Integer>>(){{
            put(0, numbers[0] = new ArrayList<>(Arrays.asList(1, 2, 3)));
            put(1, numbers[1] = new ArrayList<>(Arrays.asList(1, 4, 7)));
            put(2, numbers[2] = new ArrayList<>(Arrays.asList(4, 5, 6)));
            put(3, numbers[3] = new ArrayList<>(Arrays.asList(3, 5, 7)));
            put(4, numbers[4] = new ArrayList<>(Arrays.asList(1, 5, 9)));
            put(5, numbers[5] = new ArrayList<>(Arrays.asList(2, 5, 8)));
            put(6, numbers[6] = new ArrayList<>(Arrays.asList(3, 6, 9)));
            put(7, numbers[7] = new ArrayList<>(Arrays.asList(7, 8, 9)));
        }};

        for (int j = 0; j<8; j++) {
            if (p1.containsAll(Objects.requireNonNull(win.get(j)))) {
                return 1;
            }
            else if(p2.containsAll(Objects.requireNonNull(win.get(j)))) {
                return 2;
            }

            else
                return 0;
        }
        return -1;
    }
}
