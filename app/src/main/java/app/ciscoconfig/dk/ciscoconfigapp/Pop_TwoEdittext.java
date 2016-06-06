package app.ciscoconfig.dk.ciscoconfigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pop_TwoEdittext extends Activity {

    EditText FirstInput, SecondInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_two_eddittext);

        Intent intent = getIntent();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));
        TextView title = (TextView) findViewById(R.id.txtTelnetPassword);
        title.setText(intent.getStringExtra("Title"));
        FirstInput = (EditText) findViewById(R.id.FirstInput);
        SecondInput = (EditText) findViewById(R.id.SecondInput);
        FirstInput.setHint(intent.getStringExtra("FirstInput"));
        SecondInput.setHint(intent.getStringExtra("SecondInput"));
    }

    public void BtnOK(View v){
        String[] PopUpAnswer = { FirstInput.getText().toString(), SecondInput.getText().toString() };
        Intent AnswerBack = new Intent();
        AnswerBack.putExtra("PopMultiAnswer", PopUpAnswer);
        setResult(RESULT_OK, AnswerBack);
        finish();
    }

    public void BtnCancel(View v){
        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }
}