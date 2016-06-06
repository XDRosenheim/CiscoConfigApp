package app.ciscoconfig.dk.ciscoconfigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HomeActivity extends Activity {

    private static final int CodeMotd = 374;
    private static final int CodeHostName = 157;
    private static final int CodeRip = 268;
    Button btnBack, btnSetHostname, btnSetMotd,
            btnSaveConfig, btnNoIpDomainLookup, btnConfigRip;
    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;

    ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

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
        btnSaveConfig = (Button) findViewById(R.id.btnSaveConfig);
        btnNoIpDomainLookup = (Button) findViewById(R.id.btnNoIpDomainLookup);
        btnConfigRip = (Button) findViewById(R.id.btnConfigRip);

        btnSetMotd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GetPopupData = new Intent(getApplicationContext(), Pop_SingleEdittext.class);
                startActivityForResult(GetPopupData, CodeMotd);
            }
        });

        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GetPopupData = new Intent(getApplicationContext(), Pop_SingleEdittext.class);
                startActivityForResult(GetPopupData, CodeHostName);
            }
        });

        btnSaveConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmd.copyRunToStart();
            }
        });

        btnNoIpDomainLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmd.noIpDomainLookUp();
            }
        });

        btnConfigRip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GetPopupData = new Intent(getApplicationContext(), Pop_TwoEdittext.class);
                startActivityForResult(GetPopupData, CodeRip);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseAll();
                finish(); // Die potato.
            }
        });
    }

    private void CloseAll() {
        // Lukker alle forbindelserne ned igen.
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
        switch (requestCode) {
            case CodeHostName: {
                if (resultCode == RESULT_OK) {
                    String Svaret = data.getStringExtra("PopAnswer");
                    cmd.setHostName(Svaret);
                    Toast.makeText(getApplicationContext(), "HOSTNAME set.", Toast.LENGTH_LONG);
                }
                break;
            }
            case CodeMotd: {
                if (resultCode == RESULT_OK) {
                    String Svaret = data.getStringExtra("PopAnswer");
                    cmd.setMOTD(Svaret);
                    Toast.makeText(getApplicationContext(), "MOTD set.", Toast.LENGTH_LONG);
                }
                break;
            }
            case CodeRip: {
                if (resultCode == RESULT_OK) {
                    String[] Svaret = data.getStringArrayExtra("PopMultiAnswer");
                    cmd.configureRIPv2(Svaret[0], Svaret[1]);
                    Toast.makeText(getApplicationContext(), "RIP set.", Toast.LENGTH_LONG);
                }
                break;
            }
            default: {
                Toast.makeText(getApplicationContext(), "Something happen!!", Toast.LENGTH_LONG);
                break;
            }
        }
    }
}
