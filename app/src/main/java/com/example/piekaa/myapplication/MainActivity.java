package com.example.piekaa.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{


    TextView tPad;
    TextView sPad;
    PiekaTouchPad touchPad;


    EditText keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated
        tPad = (TextView) findViewById(R.id.touchPad);
        sPad = (TextView) findViewById(R.id.scrollPad);
        tPad.setLayoutParams(new LinearLayout.LayoutParams((int) (width * 0.8), height / 2));
        sPad.setLayoutParams(new LinearLayout.LayoutParams((int) (width * 0.2), height / 2));

        tPad.invalidate();
        sPad.invalidate();


            Container.sPad = new ScrollPad(sPad);


            Container.tPad = new PiekaTouchPad(tPad);



        final Button button = (Button) findViewById(R.id.rightClickButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                byte b[] = new byte[1];

                b[0] = 2;


                try {
                    Container.getServerClient().sendBytes(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });




        final Button buttonIncVolume = (Button) findViewById(R.id.incVolume);
        buttonIncVolume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                byte b[] = new byte[2];

                b[0] = 3;
                b[1] = 2;

                try {
                    Container.getServerClient().sendBytes(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        final Button buttonDecVolume = (Button) findViewById(R.id.decVolume);
        buttonDecVolume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                byte b[] = new byte[2];

                b[0] = 3;
                b[1] = 1;

                try {
                    Container.getServerClient().sendBytes(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getBaseContext(), StartActivity.class);
            startActivity(intent);
            return true;
        }


        if (id == R.id.reconnect) {

            try {
                Container.reconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        System.out.println(event.toString());


        if( v.getId() == R.id.touchPad )
        {
            System.out.println("Down " + event.getX() );
            return true;
        }



        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent e) {
       if( e.getAction() != KeyEvent.ACTION_DOWN)
            return false;

        boolean tru = false;

        System.out.println("Key unicode: " + e.getUnicodeChar() );
        System.out.println("Key code: " + e.getKeyCode() );
        byte[] b = new byte[2];

        b[0] = 3;
        b[1] = (byte) e.getUnicodeChar();
        if( e.getUnicodeChar() == 0 )
        {


            if (e.getKeyCode() == 67) {
                b[1] = 8;
                tru = true;
            }


            if (e.getKeyCode() == 19) {
                b[1] = 19;
                tru = true;
            }

            if (e.getKeyCode() == 20)
            {
                b[1] = 20;
                tru = true;
            }


            if (e.getKeyCode() == 21)
            {
                b[1] = 21;
                tru = true;
            }


            if (e.getKeyCode() == 22) {
                b[1] = 22;
                tru = true;
            }
        }


        try {
            Container.getServerClient().sendBytes(b);
        } catch (IOException e1) {
            e1.printStackTrace();
        }



        if(e.getUnicodeChar() != 0 || tru == true )
        {
            return true;
        }


        return false;
    }
/*
    @Override daj mnie nogie qqqqq  qqqqqqqqw
    public boolean dispatchKeyEvent(KeyEvent e) {

        if( e.getAction() != KeyEvent.ACTION_DOWN)
            return false;

        System.out.println("Key unicode: " + e.getUnicodeChar() );
        System.out.println("Key code: " + e.getKeyCode() );
        byte[] b = new byte[2];

        b[0] = 3;

        b[1] = (byte)e.getUnicodeChar();
        if( e.getKeyCode() == 67 )
        b[1] = 8;
        client.sendBytes(b);

    return false;
    }
*/
}
