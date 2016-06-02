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
    int CodeMotd = 2;
    int CodeHostName = 3;


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



                Intent GetPopupData = new Intent(HomeActivity.this, Pop.class);
                startActivityForResult(GetPopupData, CodeMotd);
            }
        });

        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                Intent GetPopupData = new Intent(HomeActivity.this, Pop.class);
                startActivityForResult(GetPopupData, CodeHostName);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

        if(requestCode==CodeHostName){

            if(resultCode == RESULT_OK) {
                String Svaret = data.getStringExtra("PopAnswer");

                cmd.setHostName(Svaret);

            }
        }

        if(requestCode==CodeMotd){

            if(resultCode == RESULT_OK) {
                String Svaret = data.getStringExtra("PopAnswer");

                cmd.setMOTD(Svaret);

            }
        }



    }

}

