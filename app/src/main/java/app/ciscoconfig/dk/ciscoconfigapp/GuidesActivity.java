package app.ciscoconfig.dk.ciscoconfigapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class GuidesActivity extends AppCompatActivity {

    Button IsoModel, CiscoModel, RouterConfig, SwitchConfig, ToGoogle,  BacktoMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        IsoModel = (Button)findViewById(R.id.Btn_Guides_IsoModel);
        IsoModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CiscoModel = (Button)findViewById(R.id.Btn_Guides_CiscoModels);
        CiscoModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RouterConfig = (Button)findViewById(R.id.Btn_Guides_RouterConfig);
        RouterConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SwitchConfig = (Button)findViewById(R.id.Btn_Guides_SwitchConfig);
        SwitchConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        ToGoogle = (Button)findViewById(R.id.Btn_Guides_Google);
        ToGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri GoogleUri = Uri.parse("http://www.Google.Com");
                Intent intentGoogle = new Intent(Intent.ACTION_VIEW, GoogleUri);
                startActivity(intentGoogle);
                }

            });












        //Knap til at komme tilbage til Main Menu.
        BacktoMain = (Button)findViewById(R.id.Btn_Guides_BackToMain);
        BacktoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent BackMain = new Intent(GuidesActivity.this, MainActivity.class);
                startActivity(BackMain);
                finish();
            }
        });
    }
}
