package app.ciscoconfig.dk.ciscoconfigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;

/**
 * Created by XDRosenheim
 * Creation date 31-05-2016
 */
public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_edittext);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Button PopupOK, PopupCancel;
        final EditText TxtConsoleAnswar;

        final Intent ReturnAnswer = new Intent();
        TxtConsoleAnswar = (EditText) findViewById(R.id.TxtConsoleAnswar);
        String Value = ReturnAnswer.getStringExtra("OldValue");


        PopupOK = (Button) findViewById(R.id.Btn_Popup_OK);
        PopupOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReturnAnswer.putExtra("PopupAnswer", TxtConsoleAnswar.getText().toString());
                setResult(0, ReturnAnswer);
                finish();

                /*
                    ConfigureCommandBlocks cmd = new ConfigureCommandBlocks();
                    cmd.setMOTD(TxtConsoleAnswar.getText().toString());
                    Toast.makeText(getApplicationContext(), "VIRKER DET ??", Toast.LENGTH_LONG).show();
                    finish();




                    cmd.setHostName(TxtConsoleAnswar.getText().toString());
                    PrintWriter Out = Singleton.getOut();
                    for (String str : cmd.array) {
                        Out.println(str);


                }
                finish();
                */
            }
        });
        PopupCancel = (Button) findViewById(R.id.Btn_Popup_Cancel);
        PopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ops. Something went wrong. Try again", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
