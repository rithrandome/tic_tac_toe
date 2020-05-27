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
    private Game g;
    private int x,y;
    private int touchCheck = 0;
    private boolean[][] cellChecked;
    private Paint  black_paint,red_paint, yellow_paint;
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

    public void setActivity(Game a)
    {
        activity = a;
    }

    private void calculateDimensions() {

        cellWidth = getWidth() / 3;
        cellHeight = getWidth() / 3;

        cellChecked = new boolean[3][3];

        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        black_paint = new Paint();
        red_paint = new Paint();
        yellow_paint = new Paint();

        black_paint.setColor(Color.BLACK);
        yellow_paint.setColor(Color.YELLOW);
        red_paint.setColor(Color.parseColor("#D83A40"));
        yellow_paint.setStrokeWidth(15);
        red_paint.setStrokeWidth(9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (cellChecked[i][j])
                    if(touchCheck !=0 && touchCheck %2==0)
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, black_paint);
                    else
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, red_paint);
            }
        }

        drawGrid(canvas);

    }

    public void drawGrid(Canvas canvas)
    {
        calculateDimensions();

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
            invalidate();
        }
        return true;
    }

//    public Boolean find_cell(float startx,float starty,float stopx)
//    {
//        if(x > startx && x < stopx && y > starty && y < starty + cellHeight)
//        {
//            return true;
//        }
//        return false;
//    }
}
