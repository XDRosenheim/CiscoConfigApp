package app.ciscoconfig.dk.ciscoconfigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfIntFE extends Activity {

    EditText TxtInterface, TxtDescription, TxtIp, TxtMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confintfe);

        Intent intent = getIntent();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .5));
        TextView title = (TextView) findViewById(R.id.popup_lbl_title);
        title.setText(intent.getExtras().getString("Title"));

        TxtInterface = (EditText) findViewById(R.id.TxtInterface);
        TxtInterface.setHint(intent.getExtras().getString("Interface"));

        TxtDescription = (EditText) findViewById(R.id.TxtDescription);
        TxtDescription.setHint(intent.getExtras().getString("Description"));

        TxtIp = (EditText) findViewById(R.id.TxtIp);
        TxtIp.setHint(intent.getExtras().getString("IP"));

        TxtMask = (EditText) findViewById(R.id.TxtMask);
        TxtMask.setHint(intent.getExtras().getString("Subnet"));
    }

    public void BtnOK(View v){
        String[] PopUpAnswer = { TxtInterface.getText().toString(),
                TxtDescription.getText().toString(),
                TxtIp.getText().toString(),
                TxtMask.getText().toString()};
        Intent AnswerBack = new Intent();
        AnswerBack.putExtra("PopMultiAnswer", PopUpAnswer);
        setResult(RESULT_OK, AnswerBack);
        finish();
    }

        public void BtnCancel(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}
