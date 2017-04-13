package com.example.piekaa.myapplication;

import android.view.View;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by piekaa on 2015-12-15.
 */
public class ScrollPad extends TouchPad {
    ScrollPad(View v) {
        super(v);
    }

    ScrollPad(TextView v) {
        super(v);
    }


    @Override
    protected void onMove(int x, int y )
    {
        byte b[] = new byte[2];
        b[0] = (byte)4;
        b[1] = (byte)y;

        try {
            Container.getServerClient().sendBytes(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
