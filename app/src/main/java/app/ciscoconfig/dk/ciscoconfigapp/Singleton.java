package app.ciscoconfig.dk.ciscoconfigapp;

import java.net.Socket;

public class Singleton {


    private static Socket ServerSock;

    public static void setSocket(Socket ServerSocket){

        Singleton.ServerSock = ServerSocket;

    }

    public static Socket getSocket(){
        return Singleton.ServerSock;
    }

}