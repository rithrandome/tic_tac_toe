package com.example.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


public class game_canvas extends View {

    private int cellWidth, cellHeight;
    private int x,y;
    private int touchCheck = 0;
    private String X_O ;
    private String[][] board;
    private boolean[][] cellChecked;
    private Paint black_paint = new Paint();
    private Paint red_paint = new Paint();
    private Paint yellow_paint = new Paint();
    Game activity;
    
    public game_canvas(Context context) {
        super(context);

        init(null);
    }

    public game_canvas(Context context, AttributeSet attrs){
        super(context,attrs);

        init(attrs);
    }


    public game_canvas(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set)
    {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        calculateDimensions();
    }

    public void setActivity(Game a)
    {
        activity = a;
    }

    private void calculateDimensions() {

        cellWidth = getWidth() / 3;
        cellHeight = getWidth() / 3;

        cellChecked = new boolean[3][3];
        board = new String[3][3];

        invalidate();
    }

    public void set_X_O(String X_O)
    {
        this.X_O = X_O;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //calculateDimensions();

        black_paint.setColor(Color.BLACK);
        yellow_paint.setColor(Color.YELLOW);
        red_paint.setColor(Color.parseColor("#D83A40"));
        yellow_paint.setStrokeWidth(15);
        red_paint.setStrokeWidth(9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j].equals("X"))
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, black_paint);
                else if(board[i][j].equals("O"))
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, red_paint);
            }
        }

        drawGrid(canvas);

    }

    public void drawGrid(Canvas canvas)
    {


        //vertical lines
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float startX = (i * cellWidth);
                float startY = (j * cellHeight);
                float stopX = (i * cellWidth);
                float stopY = ((j + 1) * cellHeight);
                canvas.drawLine(startX, startY, stopX, stopY, red_paint);

            }

        }

        //horizontal lines
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float startX =(j * cellWidth);
                float startY = (i * cellHeight);
                float stopX = ((j + 1) * cellWidth);
                float stopY =(i * cellHeight);
                canvas.drawLine(startX, startY, stopX, stopY, red_paint);
            }
        }
    }

    public void drawGame(boolean touchCheck)
    {





    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.x = (int)event.getX();
        this.y = (int)event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);
            touchCheck++;
            cellChecked[column][row] = true;
            board[column][row] = X_O;
            activity.set_game(find_cell(column,row));
            invalidate();
        }
        return true;
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
