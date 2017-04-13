package com.example.piekaa.myapplication;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by piekaa on 2015-12-13.
 */
public class TouchPad implements View.OnTouchListener{


    private TextView pad;

    private int x,y;

    private int downX, downY;

    private long downTime;

    private long time;

    TouchPad(View v)
    {
        pad = (TextView) v;
        pad.setOnTouchListener(this);

    }


    TouchPad(TextView v)
    {
        pad = v;
        pad.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

  //      System.out.println("On Touch");



                    if( event.getAction() == MotionEvent.ACTION_DOWN )
                    {
                        downTime = System.currentTimeMillis();
                        time = downTime;
                        x =(int)event.getX();
                        y = (int)event.getY();

                        downX = x;
                        downY = y;

                    }

                    if( event.getAction() == MotionEvent.ACTION_MOVE )
                    {
                        int nx,ny;
                        nx =(int) event.getX();
                        ny =(int) event.getY();

                        if( (nx - x) * (nx - x)  +  (ny - y) * (ny - y) >= 5 && System.currentTimeMillis() - time > 100)
                        {

                       //     System.out.println( "On move: " + (nx - x) + "  " + (ny - y) );
                            onMove(nx - x, ny - y);
                            x = nx;
                            y = ny;

                            time = System.currentTimeMillis();

                        }


                    }

                    if( event.getAction() == MotionEvent.ACTION_UP)
                    {
                  //      System.out.println( System.currentTimeMillis() - downTime );

                        int nx,ny;
                        nx =(int) event.getX();
                        ny =(int) event.getY();

                        int distance = (nx - downX) * (nx - downX)  +  (ny - downY) * (ny - downY);

                        if( System.currentTimeMillis() - downTime <= 200  && distance <= 100)
                        {
                         //   System.out.println("Click");
                            onClick();
                        }
                    }

        return true;


    }



    protected void onClick()
    {

    }

    protected void onMove(int x, int y)
    {

    }


}
