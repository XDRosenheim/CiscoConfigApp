package app.ciscoconfig.dk.ciscoconfigapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

public class EthernetSetup extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipsetup);

        // TODO:

    }

    public boolean checkIP(String ip) {
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
}
