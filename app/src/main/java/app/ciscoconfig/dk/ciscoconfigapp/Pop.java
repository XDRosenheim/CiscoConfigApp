package app.ciscoconfig.dk.ciscoconfigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        getWindow().setLayout((int)(width*.8), (int)(height*.6));



        Button PopupOK, PopupCancel;
        EditText TxtConsoleAnswar;

        TxtConsoleAnswar = (EditText)findViewById(R.id.TxtConsoleAnswar);

        final String TextConsoleAnswar = TxtConsoleAnswar.getText().toString();

        PopupOK = (Button)findViewById(R.id.Btn_Popup_OK);
        PopupOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ReturnPopupAnswar = getIntent();
                ReturnPopupAnswar.putExtra("Popup_Svar", TextConsoleAnswar);
                setResult(1, ReturnPopupAnswar);
                finish();

            }
        });
        PopupCancel = (Button)findViewById(R.id.Btn_Popup_Cancel);
        PopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent NoReturnPopupAnswar = getIntent();
                setResult(Activity.RESULT_CANCELED, NoReturnPopupAnswar);
                finish();
            }
        });
    }
}
