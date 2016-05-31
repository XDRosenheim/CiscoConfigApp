package app.ciscoconfig.dk.ciscoconfigapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HomeActivity extends AppCompatActivity {

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




////// TESTING AREA //////
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setTitle("Title");

                        // Set up the input
                        final EditText input = new EditText(getApplicationContext());
                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

                        // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m_Text = input.getText().toString();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();

////// TESTING AREA //////






                        cmd.setMOTD(m_Text);
                        for (String sendMe : cmd.array) {
                            Out.println(sendMe); // Send commands to device.
                            txtConsole.setText(txtConsole.getText().toString() + "\n" + sendMe);
                            viewterminal.fullScroll(View.FOCUS_DOWN);
                        }
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
