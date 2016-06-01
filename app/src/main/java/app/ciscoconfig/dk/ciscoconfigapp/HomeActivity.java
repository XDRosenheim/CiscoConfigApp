package app.ciscoconfig.dk.ciscoconfigapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends Activity {

    static final int Popup_SvarMotd = 1;
    static final int Popup_SvarHostname = 1;

    Button btnBack, btnSetHostname, btnSetMotd;
    TextView txtConsole;
    ScrollView viewterminal;
    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;
    String Oldvalue = "";


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
                    newText += txtConsole.getText().toString() + "\n" + reader;
                    if (reader != null) {
                        txtConsole.setText(newText);
                        viewterminal.fullScroll(View.FOCUS_DOWN);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnSetMotd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent GetMotd = new Intent(HomeActivity.this, Pop.class);
                    GetMotd.putExtra("OldValue", Oldvalue);
                    startActivityForResult(GetMotd, 0);

            }
        });

        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                Intent GetHostName = new Intent(HomeActivity.this, Pop.class);
                GetHostName.putExtra("Hostname", true);
                startActivity(GetHostName);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
        String Answer = data.getStringExtra("PopupAnswer");

        if ( resultCode== 1) {
            Toast.makeText(getApplicationContext(), "Something went wrong, try again.", Toast.LENGTH_LONG).show();
        }else if (requestCode == Popup_SvarMotd) {
            if (resultCode == 0) {

                cmd.setMOTD(Answer);

                for (String str : cmd.array) {
                    Out.println(str);
                }
            }
        }else if (requestCode == Popup_SvarHostname) {
            if (resultCode == RESULT_OK) {

                cmd.setMOTD(Answer);
            }
        }
    }
}
