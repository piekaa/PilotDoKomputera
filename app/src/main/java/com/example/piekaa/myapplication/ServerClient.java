package com.example.piekaa.myapplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by piekaa on 2015-12-13.
 */
public class ServerClient {

    Socket socket;
    OutputStream stream;
    ServerClient( ) throws IOException {




            System.out.println("trying to connect");
            socket = new Socket("192.168.0.105", 8900);
            System.out.println("Polaczenie nawiazane");




        try {
            stream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    ServerClient(String address, int port ) throws IOException {
        System.out.println("trying to connect");
            socket = new Socket(address, port);
            System.out.println("Polaczenie nawiazane");


        try {
            stream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void sendBytes(byte[] message)
    {

        try {
            stream.write(message);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
