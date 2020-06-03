package com.example.tictactoe;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class gameEngine {

    private List<Integer> p1 = new ArrayList<>();
    private List<Integer> p2 = new ArrayList<>();
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


    int startGame(int game, int i, int mode)
    {
        int win;

        if (i % 2 == 0) {
            p1.add(game);
        } else {
            p2.add(game);
        }
        place_marker(d, marker, p, i, game);

        win = endGame(p1,p2);

        return win;
    }

    private void place_marker(Hashtable<Integer, String> d, List<String> marker, String p, int i, int pos) {
        if(p.equals("X")) {
            d.put(pos, marker.get(i + 1));
        }
        else if(p.equals("O")) {
            d.put(pos, marker.get(i));
        }
    }

    int endGame( List<Integer> p1, List<Integer> p2) {


        final ArrayList<Integer> a1 = new ArrayList<Integer>(){{add(1);add(2);add(3);}};
        final ArrayList<Integer> a2 = new ArrayList<Integer>(){{add(1);add(4);add(7);}};
        final ArrayList<Integer> a3 = new ArrayList<Integer>(){{add(5);add(4);add(6);}};
        final ArrayList<Integer> a4 = new ArrayList<Integer>(){{add(3);add(5);add(7);}};
        final ArrayList<Integer> a5 = new ArrayList<Integer>(){{add(1);add(5);add(9);}};
        final ArrayList<Integer> a6 = new ArrayList<Integer>(){{add(2);add(5);add(8);}};
        final ArrayList<Integer> a7 = new ArrayList<Integer>(){{add(3);add(6);add(9);}};
        final ArrayList<Integer> a8 = new ArrayList<Integer>(){{add(7);add(8);add(9);}};

        ArrayList<ArrayList<Integer>> win = new ArrayList<ArrayList<Integer>>(){{
            add(a1);add(a2);add(a3);add(a4);add(a5);add(a6);add(a7);add(a8);
        }};

        for ( int j = 0; j < 8; j++ ) {

            if (p1.size() > 2 && p1.containsAll(win.get(j))) {
                return 1;
            }
            if(p2.size() > 2 && p2.containsAll(win.get(j))) {
                return 2;
            }
        }
        return 0;
    }


}
