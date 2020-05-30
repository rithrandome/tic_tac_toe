package com.example.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;


public class game_canvas extends View {

    private int cellWidth;
    private int cellHeight;
    private String[][] board = new String[3][3];
    private boolean[][] cellChecked = new boolean[3][3];
    private Paint black_paint = new Paint();
    private Paint red_paint = new Paint();
    private Paint yellow_paint = new Paint();
    private Paint orange_paint = new Paint();
    private Game a;
    private gameEngine ge;
    private int touched = 0;


    public game_canvas(Context context) {
        super(context);

        Arrays.fill(board,null);
    }

    public game_canvas(Context context, AttributeSet attrs){
        super(context,attrs);

        black_paint.setColor(Color.BLACK);
        orange_paint.setColor(Color.parseColor("#FF5722"));
        yellow_paint.setColor(Color.YELLOW);
        red_paint.setColor(Color.parseColor("#D83A40"));
        yellow_paint.setStrokeWidth(15);
        red_paint.setStrokeWidth(9);
        black_paint.setStrokeWidth(9);
        orange_paint.setStrokeWidth(15);
        yellow_paint.setStyle(Paint.Style.STROKE);
    }


    public game_canvas(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);
    }


    public void setGame(Game a)
    {
        this.a = a;
    }

    public void setGameEngine(gameEngine ge)
    {
        this.ge = ge;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawGrid(canvas);
        drawGame(canvas);
    }

    public void drawGrid(Canvas canvas)
    {
        cellWidth = getWidth() / 3;
        cellHeight = getWidth() / 3;

        //vertical lines
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float startX = (i * cellWidth);
                float startY = (j * cellHeight);
                float stopX = (i * cellWidth);
                float stopY = ((j + 1) * cellHeight);
                canvas.drawLine(startX, startY, stopX, stopY, black_paint);
            }
        }
        //horizontal lines
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float startX =(j * cellWidth);
                float startY = (i * cellHeight);
                float stopX = ((j + 1) * cellWidth);
                float stopY =(i * cellHeight);
                canvas.drawLine(startX, startY, stopX, stopY, black_paint);
            }
        }
    }

    public void drawGame(Canvas canvas)
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == "X") {
                    canvas.drawLine(i * cellWidth + 50, j * cellHeight + 50, (i + 1) * cellWidth - 50, (j + 1) * cellHeight- 50, orange_paint);
                    canvas.drawLine((i+1)* cellWidth - 50, j * cellHeight + 50, (i) * cellWidth + 50, (j+1) * cellHeight - 50, orange_paint);
                }
                else if(board[i][j]== "O")
                    canvas.drawCircle((i) * cellWidth + cellWidth/2 , (j) * cellHeight + cellHeight/2 , 125,  yellow_paint);
                else
                    assert true;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);
            if(!cellChecked[column][row]) {
                cellChecked[column][row] = true;
                if(touched % 2 == 0)
                    board[column][row] = "X";
                else
                    board[column][row] = "O";
                invalidate();
                int win = ge.startGame(find_cell(column, row),touched);

                if(win != 0)
                    a.endGame(win);
                if(touched == 8)
                    if(win == 0)
                        a.endGame(-1);
                    else
                        a.endGame(win);
                ++touched;

            }
            else
                assert true;

        }
        return super.onTouchEvent(event);
    }

    public int find_cell(int c,int r)
    {
       if(c + r == 1)
       {
           if(c > r)
               return 2;
           else if(c < r)
               return 4;
       }
       else if(c + r == 2)
        {
            if(c > r)
                return 3;
            else if(c < r)
                return 7;
            else if(c == r)
                return 5;
        }
       else if(c + r == 3)
       {
           if(c > r)
               return 6;
           else if(c < r)
               return 8;
       }
       else if(c + r == 4)
           return 9;
       else if(c + r == 0)
           return 1;
       return 0;
    }
}
