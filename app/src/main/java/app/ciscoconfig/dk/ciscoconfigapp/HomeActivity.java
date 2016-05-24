package app.ciscoconfig.dk.ciscoconfigapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends AppCompatActivity {

    Button btnBack, btnSetHostname,
            btnSetMotd;
    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;

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

        btnSetHostname = (Button) findViewById(R.id.btnSetHostname);
        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
                cmd.setHostName("GET MESSAGE FROM USER SOMEHOW");
                for (String sendMe : cmd.array) {
                    Out.println(sendMe); // Send commands to device.
                }
            }
        });

        btnSetMotd = (Button) findViewById(R.id.btnSetMotd);
        btnSetMotd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
                cmd.setMOTD("GET MESSAGE FROM USER SOMEHOW");
                for (String sendMe : cmd.array) {
                    Out.println(sendMe); // Send commands to device.
                }
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
