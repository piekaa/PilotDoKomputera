package com.example.piekaa.myapplication;

import java.io.IOException;

/**
 * Created by piekaa on 2015-12-14.
 */
public class Container {

    private static ServerClient serverClient;
    public static PiekaTouchPad tPad;
    public static ScrollPad sPad;

    public static String ip;


    public static ServerClient getServerClient() throws IOException {
        if( serverClient == null)
        {
            serverClient = new ServerClient(ip, 8900);
        }
        return serverClient;
    }


    public static void reconnect() throws IOException {
        serverClient = new ServerClient(ip, 8900);
    }
}
