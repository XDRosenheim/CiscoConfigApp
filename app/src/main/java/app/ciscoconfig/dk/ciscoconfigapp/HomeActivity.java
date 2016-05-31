package app.ciscoconfig.dk.ciscoconfigapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends AppCompatActivity {

    Button btnBack, btnSetHostname, btnSetMotd;
    TextView txtConsole;
    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Sock = Singleton.getSocket();
        Out = Singleton.getOut();
        In = Singleton.getIn();

        txtConsole = (TextView) findViewById(R.id.txtConsole);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reader = In.readLine();
                    if (reader != null) {
                        txtConsole.append(reader);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        btnSetHostname = (Button) findViewById(R.id.btnSetHostname);
        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
                cmd.setHostName("ThisIsMyLifeNow");
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
                cmd.setMOTD("This is your brand new MOTD!");
                for (String sendMe : cmd.array) {
                    Out.println(sendMe); // Send commands to device.
                }
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseAll();
                finish(); // Die.
            }
        });
    }

    private void CloseAll() {
        //Lukker alle forbindelserne ned igen.
        try {
            In.close();
            Out.close();
            Sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
