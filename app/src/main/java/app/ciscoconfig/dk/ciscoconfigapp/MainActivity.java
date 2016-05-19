package app.ciscoconfig.dk.ciscoconfigapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    //Variabler på Main Activity
    Intent  intent1;
    private EditText EditIpAddress, EditPortNr;
    private Button BtnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fundet komponenterne via ID.
        EditIpAddress = (EditText)findViewById(R.id.EditIPAdresse);
        EditPortNr = (EditText)findViewById(R.id.EditPortNr);
        BtnConnect = (Button)findViewById(R.id.BtnConnecting);
        BtnConnect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                try {
                    String Address = EditIpAddress.getText().toString();
                    String PortNr = EditPortNr.getText().toString();

                    //Opretter en socket som bruges som Telnet til at connecte til Router.
                    Socket Sock = new Socket(Address, Integer.parseInt(PortNr));


                    if(Sock.isConnected())
                    {
                        intent1 = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                    }



                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

