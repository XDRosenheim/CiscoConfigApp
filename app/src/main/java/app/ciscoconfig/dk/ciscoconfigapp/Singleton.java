package app.ciscoconfig.dk.ciscoconfigapp;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Singleton {
    private static Socket ServerSock;
    private static PrintWriter SocketOut;
    private static BufferedReader SocketIn;

    public static Socket getSocket() {
        return Singleton.ServerSock;
    }

    public static void setSocket(Socket ServerSocket) {
        Singleton.ServerSock = ServerSocket;
    }

    public static PrintWriter getOut() {
        return Singleton.SocketOut;
    }

    public static void setOut(PrintWriter SocketOut) {
        Singleton.SocketOut = SocketOut;
    }

    public static BufferedReader getIn() {
        return Singleton.SocketIn;
    }

    public static void setIn(BufferedReader SocketIn) {
        Singleton.SocketIn = SocketIn;
    }
}
