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

public class HomeActivity extends Activity {

    private static final int CodeMotd = 374;
    private static final int CodeHostName = 157;
    private static final int CodeRip = 268;
    private static final int CodeSerialInterface = 130;
    private static final int CodeFastInterface = 565;
    Button btnBack, btnSetHostname, btnSetMotd,
            btnSaveConfig, btnNoIpDomainLookup, btnConfigRip;
    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;

    private ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();

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

        btnSetHostname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GetPopupData = new Intent(getApplicationContext(), Pop_SingleEdittext.class);
                GetPopupData.putExtra("Title", "Set hostname");
                GetPopupData.putExtra("Hint", "Write Hostname here");
                startActivityForResult(GetPopupData, CodeHostName);
            }
        });

        btnSetMotd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GetPopupData = new Intent(getApplicationContext(), Pop_SingleEdittext.class);
                GetPopupData.putExtra("Title", "Set banner");
                GetPopupData.putExtra("Hint", "Write your banner here");
                startActivityForResult(GetPopupData, CodeMotd);
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
                GetPopupData.putExtra("Title", "Set RipV2");
                GetPopupData.putExtra("FirstInput", "Interface");
                GetPopupData.putExtra("SecondInput", "Network");
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
        switch (requestCode) {
            case CodeHostName: {
                if (resultCode == RESULT_OK) {
                    String Svaret = data.getStringExtra("PopAnswer");
                    Svaret.replaceAll(" ","");
                    cmd.setHostName(Svaret);
                    Toast.makeText(getApplicationContext(), "HOSTNAME was set.", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case CodeMotd: {
                if (resultCode == RESULT_OK) {
                    String Svaret = data.getStringExtra("PopAnswer");
                    cmd.setMOTD(Svaret);
                    Toast.makeText(getApplicationContext(), "MOTD was set.", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case CodeRip: {
                if (resultCode == RESULT_OK) {
                    String[] Svaret = data.getStringArrayExtra("PopMultiAnswer");
                    cmd.configureRIPv2(Svaret[0], Svaret[1]);
                    Toast.makeText(getApplicationContext(), "RIP was set.", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case CodeSerialInterface: {
                if (resultCode == RESULT_OK) {
                    String[] Svaret = data.getStringArrayExtra("PopMultiAnswer");
                    cmd.setSerialInterface(Svaret[0], Svaret[1], Svaret[2], Svaret[3], Svaret[4]);
                    Toast.makeText(getApplicationContext(), "SERIAL INTERFACE was set.", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case CodeFastInterface: {
                if (resultCode == RESULT_OK) {
                    String[] Svaret = data.getStringArrayExtra("PopMultiAnswer");
                    cmd.setIpFeastEthernetInterface(Svaret[0], Svaret[1], Svaret[2], Svaret[3]);
                    Toast.makeText(getApplicationContext(), "FAST ETHERNET INTERFACE was set.", Toast.LENGTH_LONG).show();
                }
                break;
            }
            default: {
                Toast.makeText(getApplicationContext(), "Something happen!!", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
