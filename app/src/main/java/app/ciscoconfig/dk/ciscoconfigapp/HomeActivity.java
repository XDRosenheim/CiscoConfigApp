package app.ciscoconfig.dk.ciscoconfigapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
                final String[] text = {null};
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Title");
                View viewInflated = LayoutInflater.from(getApplicationContext()).inflate(R.layout.text_inpu_password, null);
                final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String temp_text = input.getText().toString();
                        text[0] = temp_text;
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                cmd.setMOTD(text[0]);
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
