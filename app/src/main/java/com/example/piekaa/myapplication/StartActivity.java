package com.example.piekaa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class StartActivity extends AppCompatActivity {

    TextView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        final Button button2 = (Button) findViewById(R.id.connectButton);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText ip = ((EditText) findViewById(R.id.ip));
                Container.ip = ip.getText().toString();


                try {
                    Container.getServerClient();
                    System.out.println("Polaczene w traj");

                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);


                } catch (Throwable t) {

                    messages = (TextView) findViewById(R.id.connectionMessages);

                    messages.setText("Connection failed");

                    t.printStackTrace();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
