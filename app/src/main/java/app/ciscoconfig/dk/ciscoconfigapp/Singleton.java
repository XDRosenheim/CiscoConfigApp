package app.ciscoconfig.dk.ciscoconfigapp;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Singleton {
    private static Socket ServerSock;
    public static void setSocket(Socket ServerSocket){
        Singleton.ServerSock = ServerSocket;
    }
    public static Socket getSocket(){
        return Singleton.ServerSock;
    }

    private static PrintWriter SocketOut;
    public static void setOut(PrintWriter SocketOut){
        Singleton.SocketOut = SocketOut;
    }
    public static PrintWriter getOut(){
        return Singleton.SocketOut;
    }

    private static BufferedReader SocketIn;
    public static void setIn(BufferedReader SocketIn){
        Singleton.SocketIn = SocketIn;
    }
    public static BufferedReader getIn(){
        return Singleton.SocketIn;
    }


}
