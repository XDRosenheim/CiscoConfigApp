package app.ciscoconfig.dk.ciscoconfigapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends AppCompatActivity {

    Button btnBack, btnSetHostname, btnSetMotd;
    TextView txtConsole;
    ScrollView viewterminal;
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

        btnSetHostname = (Button) findViewById(R.id.btnSetHostname);
        btnSetMotd = (Button) findViewById(R.id.btnSetMotd);
        btnBack = (Button) findViewById(R.id.btnBack);

        viewterminal = (ScrollView) findViewById(R.id.viewterminal);

        txtConsole = (TextView) findViewById(R.id.txtConsole);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String reader;
                    String newText = "";
                    reader = In.readLine();
                    newText += txtConsole.getText().toString()+"\n"+reader.toString();
                    if ( reader != null ) {
                        txtConsole.setText(newText);
                        viewterminal.fullScroll(View.FOCUS_DOWN);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {

                String newText;

                btnSetHostname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
                        cmd.setHostName("ThisIsMyLifeNow");
                        for (String sendMe : cmd.array) {
                            Out.println(sendMe); // Send commands to device.
                            txtConsole.setText(txtConsole.getText().toString()+"\n"+sendMe);
                            viewterminal.fullScroll(View.FOCUS_DOWN);
                        }
                    }
                });


                btnSetMotd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
                        cmd.setMOTD("This is your brand new MOTD!");
                        for (String sendMe : cmd.array) {
                            Out.println(sendMe); // Send commands to device.
                            txtConsole.setText(txtConsole.getText().toString()+"\n"+sendMe);
                            viewterminal.fullScroll(View.FOCUS_DOWN);
                        }
                    }
                });


                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CloseAll();
                        finish(); // Die.
                    }
                });

            }
        });

        t2.start();
        t1.start();
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
