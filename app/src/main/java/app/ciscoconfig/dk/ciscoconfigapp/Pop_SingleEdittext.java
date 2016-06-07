package app.ciscoconfig.dk.ciscoconfigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pop_SingleEdittext extends Activity {

    EditText TxtConsoleAnswar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_single_edittext);
        Intent intent = getIntent();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .4));
        TextView title = (TextView) findViewById(R.id.popup_lbl_title);
        title.setText(intent.getExtras().getString("Title"));
        TxtConsoleAnswar = (EditText) findViewById(R.id.TxtConsoleAnswar);
        TxtConsoleAnswar.setHint(intent.getExtras().getString("Hint"));
    }

    public void BtnOK(View v){
        String PopUpAnswer = TxtConsoleAnswar.getText().toString();
        Intent AnswerBack = new Intent();
        AnswerBack.putExtra("PopAnswer", PopUpAnswer);
        setResult(RESULT_OK, AnswerBack);
        finish();
    }

    public void BtnCancel(View v){
        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }
}
