package app.ciscoconfig.dk.ciscoconfigapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class GuidesActivity extends AppCompatActivity {

    Button OsiModel, CiscoModel, RouterConfig, SwitchConfig, ToGoogle,  BacktoMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        OsiModel = (Button)findViewById(R.id.Btn_Guides_OsiModel);
        OsiModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri OsiModelUri = Uri.parse("http://www.cisco.com/cpress/cc/td/cpress/fund/ith/ith01gb.htm#xtocid166844");
                Intent intentOsiModel = new Intent(Intent.ACTION_VIEW, OsiModelUri);
                startActivity(intentOsiModel);

            }
        });

        CiscoModel = (Button)findViewById(R.id.Btn_Guides_CiscoModels);
        CiscoModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri CiscoModelUri = Uri.parse("http://www.Google.Com");
                Intent intentCisco = new Intent(Intent.ACTION_VIEW, CiscoModelUri);
                startActivity(intentCisco);

            }
        });

        RouterConfig = (Button)findViewById(R.id.Btn_Guides_RouterConfig);
        RouterConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri RouterConfUri = Uri.parse("http://www.Google.Com");
                Intent intenRouter = new Intent(Intent.ACTION_VIEW, RouterConfUri);
                startActivity(intenRouter);

            }
        });

        SwitchConfig = (Button)findViewById(R.id.Btn_Guides_SwitchConfig);
        SwitchConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri SwitchConfUri = Uri.parse("http://www.Google.Com");
                Intent intentSwitch = new Intent(Intent.ACTION_VIEW, SwitchConfUri);
                startActivity(intentSwitch);

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
                finish();
            }
        });
    }
}
