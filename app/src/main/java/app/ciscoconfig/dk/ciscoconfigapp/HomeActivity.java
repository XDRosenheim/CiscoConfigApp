package app.ciscoconfig.dk.ciscoconfigapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends AppCompatActivity {

    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {

            Sock = Singleton.getSocket();
            Out = new PrintWriter(Sock.getOutputStream(), true);
            In = new BufferedReader(new InputStreamReader(Sock.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }


        back = (Button) findViewById(R.id.button4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
