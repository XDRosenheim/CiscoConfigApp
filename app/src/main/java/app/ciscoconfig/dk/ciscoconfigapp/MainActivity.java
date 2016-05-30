package app.ciscoconfig.dk.ciscoconfigapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //Variabler på Main Activity
    Intent ToConf;
    Intent ToGuide;
    private EditText EditIpAddress = null;
    private EditText EditPortNr = null;
    private Button BtnConnect, BtnGuides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Fundet komponenterne via ID.
        EditIpAddress = (EditText) findViewById(R.id.EditIPAdresse);
        EditPortNr = (EditText) findViewById(R.id.EditPortNr);
        BtnConnect = (Button) findViewById(R.id.BtnConnecting);
        BtnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIPPort(EditIpAddress.getText().toString(), EditPortNr.getText().toString()))
                    try {
                        String IpAddress = EditIpAddress.getText().toString();
                        String PortNr = EditPortNr.getText().toString();
                        if (PortNr.equals("")) PortNr = "23";
                        Toast.makeText(getApplicationContext(), R.string.connect_connecting, Toast.LENGTH_SHORT).show();
                        //Opretter en socket som bruges som Telnet til at connecte til Router.
                        Socket Sock = new Socket(IpAddress, Integer.parseInt(PortNr));
                        Sock.setKeepAlive(true);
                        Singleton.setSocket(Sock);
                        if (Sock.isConnected()) {
                            ToConf = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(ToConf);
                            Toast.makeText(getApplicationContext(), R.string.connect_Connected, Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Could not connect", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        Toast.makeText(getApplicationContext(), "Error: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                else {
                    Toast.makeText(getApplicationContext(), R.string.connect_noEntry, Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView txtGuides = (TextView) findViewById(R.id.TxtGuides);
        BtnGuides = (Button) findViewById(R.id.BtnGuides);
        BtnGuides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Opens the guide activity.
                 */
                ToGuide = new Intent(MainActivity.this, GuidesActivity.class);
                startActivity(ToGuide);
            }
        });

        if (isConnected()) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                BtnGuides.setEnabled(true);
            else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                BtnGuides.setEnabled(true);
            else if (activeNetworkInfo.getType() != ConnectivityManager.TYPE_ETHERNET)
                BtnGuides.setEnabled(false);
            else BtnGuides.setEnabled(true);
        } else {
            BtnGuides.setEnabled(false);
            if (txtGuides != null) {
                txtGuides.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Returns:
     * true - if IP and port are valid.
     * false - if IP is not valid or port is too big or too small.
     */
    private boolean checkIPPort(String ip, String port) {
        try {
            int i = Integer.parseInt(port);
            if (i > 65535) return false;
        } catch (NumberFormatException nfe) { /** Do nothing. Use default port. */}
        try {
            String[] parts = ip.split("\\.");
            if (parts.length != 4) return false;
            for (String s : parts) {
                int i = Integer.parseInt(s);
                if ((i < 1) || (i > 255)) return false;
            }
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Check users network connection. (Not internet, just network)
     * Returns:
     * true - if user has a network connection (WiFi/Mobile/Ethernet)
     * false - if user doesn't have a network connection
     */
    private boolean isConnected() {
        ConnectivityManager CheckNetwork = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = CheckNetwork.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


}
