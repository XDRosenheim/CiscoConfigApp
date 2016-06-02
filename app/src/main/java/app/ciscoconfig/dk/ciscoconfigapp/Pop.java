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

    EditText TxtConsoleAnswar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_edittext);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        TxtConsoleAnswar = (EditText) findViewById(R.id.TxtConsoleAnswar);

    }

    public void BtnOK(View v){
        String PopUpAnswer = TxtConsoleAnswar.getText().toString();
        Intent AnswerBack = new Intent();
        AnswerBack.putExtra("PopAnswer", PopUpAnswer);
        setResult(RESULT_OK, AnswerBack);
        finish();
    }

    public void BtnCancel(View v){

        Toast.makeText(getApplicationContext(), "Ops. Something went wrong. Try again", Toast.LENGTH_SHORT).show();
        finish();
    }
}
