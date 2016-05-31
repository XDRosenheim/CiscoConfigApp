package app.ciscoconfig.dk.ciscoconfigapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GuidesActivity extends AppCompatActivity {

    Button OsiModel, RouterModel, Switchmodels, RouterConfig, SwitchConfig, ToGoogle, BacktoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        //Knap der føre brugeren til en side med OSI Modellen.
        OsiModel = (Button) findViewById(R.id.Btn_Guides_OsiModel);
        OsiModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri OsiModelUri = Uri.parse("http://www.cisco.com/cpress/cc/td/cpress/fund/ith/ith01gb.htm#xtocid166844");
                Intent intentOsiModel = new Intent(Intent.ACTION_VIEW, OsiModelUri);
                startActivity(intentOsiModel);
            }
        });

        //Knap der føre brugeren til en side med Router Models.
        RouterModel = (Button) findViewById(R.id.Btn_Guides_RouterModels);
        RouterModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri RouterModelUri = Uri.parse("http://www.cisco.com/c/en/us/products/routers/product-listing.html");
                Intent intentRouterModel = new Intent(Intent.ACTION_VIEW, RouterModelUri);
                startActivity(intentRouterModel);
            }
        });

        //Knap der føre brugeren til en side med Switch Models.
        Switchmodels = (Button) findViewById(R.id.Btn_Guides_SwitchModels);
        Switchmodels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri SwitchModelUri = Uri.parse("http://www.cisco.com/c/en/us/products/switches/product-listing.html");
                Intent intenSwitchModel = new Intent(Intent.ACTION_VIEW, SwitchModelUri);
                startActivity(intenSwitchModel);
            }
        });

        //Knap Der føre brugeren til en side med Router Configuration.
        RouterConfig = (Button) findViewById(R.id.Btn_Guides_RouterConfig);
        RouterConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri RouterConfUri = Uri.parse("https://www.cisco.com/en/US/docs/routers/access/800/850/software/configuration/guide/routconf.pdf");
                Intent intenRouter = new Intent(Intent.ACTION_VIEW, RouterConfUri);
                startActivity(intenRouter);
            }
        });

        //Knap Der føre brugeren til en side med Switch Configuration.
        SwitchConfig = (Button) findViewById(R.id.Btn_Guides_SwitchConfig);
        SwitchConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri SwitchConfUri = Uri.parse("https://www.pantz.org/software/ios/ioscommands.html#IOSCommands");
                Intent intentSwitch = new Intent(Intent.ACTION_VIEW, SwitchConfUri);
                startActivity(intentSwitch);
            }
        });

        //Knap der føre brugeren til Google.com
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
