package app.ciscoconfig.dk.ciscoconfigapp;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

    static final int Popup_Svar = 1;

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

        btnSetMotd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                Intent GetMotd = new Intent(HomeActivity.this, Pop.class);
                startActivityForResult(GetMotd, Popup_Svar);

                cmd.setMOTD("BaconBanner");

            }
        });

        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                Intent GetHostName = new Intent(HomeActivity.this, Pop.class);
                startActivityForResult(GetHostName, Popup_Svar);

                cmd.setHostName("Bacon");

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


        if(requestCode == Popup_Svar)
        {
            if(resultCode == RESULT_OK)
            {
                final String PopupAnswar = data.getStringExtra("Popup_Svar");
                btnSetMotd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                        Intent GetMotd = new Intent(HomeActivity.this, Pop.class);
                        startActivityForResult(GetMotd, Popup_Svar);

                        cmd.setMOTD(PopupAnswar);

                    }
                });

                btnSetHostname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

                        Intent GetHostName = new Intent(HomeActivity.this, Pop.class);
                        startActivityForResult(GetHostName, Popup_Svar);

                        cmd.setHostName(PopupAnswar);

                        for (String sendMe : cmd.array) {
                            Out.println(sendMe); // Send commands to device.
                            txtConsole.setText(txtConsole.getText().toString() + "\n" + sendMe);
                            viewterminal.fullScroll(View.FOCUS_DOWN);
                        }
                    }
                });
            }
        }

        if(requestCode == 2)
        {
            if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(), "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
