package com.example.piekaa.myapplication;

import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by piekaa on 2015-12-13.
 */
public class PiekaTouchPad extends TouchPad {




    private double sensivity = 0.5;


    public void setSensivity(double sensivity) {
        this.sensivity = sensivity;
    }

    PiekaTouchPad(TextView v ) {
        super(v);

    }

    @Override
    protected void onClick()
    {
        System.out.println("Click");

        byte[] bytes = new byte[1];

        bytes[0] = 1;


        try {
            Container.getServerClient().sendBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onMove(int x, int y )
    {

        x = increase(x);
        y = increase(y);
        System.out.println("Move x: " + x + " y: " + y);
        byte[] bytes = new byte[5];

        x+=10000;
        y+=10000;

        bytes[0] = 0;
        bytes[2] = (byte)(x >> 8);
        bytes[1] = (byte)(x & 255);

        bytes[4] = (byte)(y >> 8);
        bytes[3] = (byte)(y & 255);

/*
        for(int i = 0 ; i < 5 ; i ++)
        {
            System.out.print(bytes[i]+ " ");
        }
        System.out.println();
*/

        try {
            Container.getServerClient().sendBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private int increase(int v)
    {
        boolean minus = false;

        if( v < 0 ) {
            minus = true;
            v *= -1;
        }


        v = (int) Math.pow(v, 1.4);


        if( minus == true )
            v*=-1;



        return (int)(v*sensivity);



    }



}
