package app.ciscoconfig.dk.ciscoconfigapp;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends Activity {

    Button btnBack, btnSetHostname, btnSetMotd;
    TextView txtConsole;
    ScrollView viewterminal;
    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;
    private String m_Text;


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
                    newText += txtConsole.getText().toString() + "\n" + reader.toString();
                    if (reader != null) {
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
                            txtConsole.setText(txtConsole.getText().toString() + "\n" + sendMe);
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
                btnSetMotd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

/////// TESTING AREA ///////
                        Intent ToConf = new Intent(getApplicationContext(), Pop.class);
                        startActivity(ToConf);
/////// TESTING AREA ///////

                        cmd.setMOTD(m_Text);
                        /*
                        for (String sendMe : cmd.array) {
                            Out.println(sendMe); // Send commands to device.
                            txtConsole.setText(txtConsole.getText().toString() + "\n" + sendMe);
                            viewterminal.fullScroll(View.FOCUS_DOWN);
                        }
                        */
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
