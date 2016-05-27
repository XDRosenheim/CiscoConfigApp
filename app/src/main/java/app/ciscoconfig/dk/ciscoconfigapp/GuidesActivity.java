package app.ciscoconfig.dk.ciscoconfigapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GuidesActivity extends AppCompatActivity {

    Button OsiModel, RouterModel,Switchmodels, RouterConfig, SwitchConfig, ToGoogle, BacktoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        OsiModel = (Button) findViewById(R.id.Btn_Guides_OsiModel);
        OsiModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri OsiModelUri = Uri.parse("http://www.cisco.com/cpress/cc/td/cpress/fund/ith/ith01gb.htm#xtocid166844");
                Intent intentOsiModel = new Intent(Intent.ACTION_VIEW, OsiModelUri);
                startActivity(intentOsiModel);
            }
        });

        RouterModel = (Button) findViewById(R.id.Btn_Guides_RouterModels);
        RouterModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri CiscoModelUri = Uri.parse("http://www.cisco.com/c/en/us/products/routers/product-listing.html");
                Intent intentCisco = new Intent(Intent.ACTION_VIEW, CiscoModelUri);
                startActivity(intentCisco);
            }
        });

        Switchmodels = (Button) findViewById(R.id.Btn_Guides_SwitchModels);
        Switchmodels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri RouterConfUri = Uri.parse("http://www.cisco.com/c/en/us/products/switches/product-listing.html");
                Intent intenRouter = new Intent(Intent.ACTION_VIEW, RouterConfUri);
                startActivity(intenRouter);
            }
        });

        RouterConfig = (Button)findViewById(R.id.Btn_Guides_RouterConfig);
        RouterConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri RouterConfUri = Uri.parse("http://www.cisco.com/c/en/us/td/docs/routers/access/1900/software/configuration/guide/Software_Configuration.html");
                Intent intenRouter = new Intent(Intent.ACTION_VIEW, RouterConfUri);
                startActivity(intenRouter);
            }
        });

        SwitchConfig = (Button) findViewById(R.id.Btn_Guides_SwitchConfig);
        SwitchConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri SwitchConfUri = Uri.parse("http://computernetworkingnotes.com/ccna-study-guide/basic-switch-configuration-guide-with-examples.html");
                Intent intentSwitch = new Intent(Intent.ACTION_VIEW, SwitchConfUri);
                startActivity(intentSwitch);
            }
        });

        ToGoogle = (Button) findViewById(R.id.Btn_Guides_Google);
        ToGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri GoogleUri = Uri.parse("http://google.Com");
                Intent intentGoogle = new Intent(Intent.ACTION_VIEW, GoogleUri);
                startActivity(intentGoogle);
            }
        });

        //Knap til at komme tilbage til Main Menu.
        BacktoMain = (Button) findViewById(R.id.Btn_Guides_BackToMain);
        BacktoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
