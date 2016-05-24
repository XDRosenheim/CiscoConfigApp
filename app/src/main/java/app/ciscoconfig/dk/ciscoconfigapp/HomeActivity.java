package app.ciscoconfig.dk.ciscoconfigapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends AppCompatActivity {

    private Socket sock;
    private PrintWriter Out;
    private BufferedReader In;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {

            sock = Singleton.getSocket();
            Out = new PrintWriter(sock.getOutputStream(), true);
            In = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
