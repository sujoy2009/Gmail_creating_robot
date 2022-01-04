package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private CardView stop_button;
    private CardView my_auto_tast;
    private CardView bootsetup;
    String simID;
    String gettedpin;
    public static final String SHARED_PREFS_for_pin_bot_app = "sharedPrefsforpin";
    SharedPreferences sharedPreferencesformypin_botapp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_auto_tast = (CardView) findViewById(R.id.my_auto_tast_id);
        stop_button = (CardView) findViewById(R.id.stop_cv_id);
        bootsetup= (CardView) findViewById(R.id.boot_setup_id);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,}, PackageManager.PERMISSION_GRANTED);

        my_auto_tast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sharedPreferencesformypin_botapp = getSharedPreferences(SHARED_PREFS_for_pin_bot_app, MODE_PRIVATE);
                gettedpin = sharedPreferencesformypin_botapp.getString("pin", "none");
                // Toast.makeText(getApplicationContext(),"pin is" +gettedpin,Toast.LENGTH_LONG).show();

                if(((gettedpin.equalsIgnoreCase("8988014402609893524F"))||(gettedpin.equalsIgnoreCase("8988039216122554624F"))||(gettedpin.equalsIgnoreCase("8988039916068793595F")))){
                    startService(new Intent(getApplication(), autobot_background.class));

                }
                else {
                    Toast.makeText(getApplicationContext(),"Not real user",Toast.LENGTH_LONG).show();

                }

            }
        });

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplication(), autobot_background.class));
            }
        });
        bootsetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager tm = (TelephonyManager)
                        getSystemService(Context.TELEPHONY_SERVICE);


                simID = tm.getSimSerialNumber();;
                //Toast.makeText(getApplicationContext(),"sim"+simID,Toast.LENGTH_SHORT).show();

                if((simID.equalsIgnoreCase("8988039916068793595F"))||(simID.equalsIgnoreCase("8988014402609893524F"))||(simID.equalsIgnoreCase("8988039216122554624F"))){
                    sharedPreferencesformypin_botapp = getSharedPreferences(SHARED_PREFS_for_pin_bot_app, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferencesformypin_botapp.edit();


                    editor.putString("pin", simID);
                    Toast.makeText(getApplicationContext(),"Succesfully Setted",Toast.LENGTH_LONG).show();
                    // Toast.makeText(getApplicationContext().this, "setted"+pin, Toast.LENGTH_SHORT).show();



                    editor.apply();




                }
                else {
                    Toast.makeText(getApplicationContext(),"Not Setted"+simID,Toast.LENGTH_LONG).show();

                }



            }
        });
    }


}

