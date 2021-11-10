package com.example.munkalaptechdoki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    public static String Logo_path;
    public static String Excel_path;
    public static String Ceges_adatok_nev_global;
    public static String Ceges_adatok_cim_global;
    public static String Ceges_adatok_phone_global;
    public static String Ceges_adatok_email_global;
    public static String Csillagos_megjegyzes_global;
    public static String Keretes_megjegyzes_global;
    public static String Excel_hasznalat_checkbox_global;
    public static String Munkalap_szam;
    public static String Nyelv_global;
    public static String Penz_nem_global;



    public static SharedPreferences DATA_SAVE;



    //MENTÉS NEVEK!!!

    public static String _DEFAULT_= "default";
    public static String _ELSO_ = "ELSO";
    public static String _LOGO_PATH_ = "LOGO_PATH";
    public static String _EXCEL_PATH_ = "EXCEL_PATH";
    public static String _CEG_NEV_ = "CEG_NEV";
    public static String _CEG_CIM_ = "CEG_CIM";
    public static String _CEG_TEL_ = "CEG_TEL";
    public static String _CEG_MAIL_ = "CEG_MAIL";
    public static String _EXCEL_BOOL_ = "EXCEL_BOOL";
    public static String _CSILLAG_MEGJEGYZES_ = "CSILLAG_MEGJEGYZES";
    public static String _KERET_MEGJEGYZES_ ="KERET_MEGJEGYZES";
    public static String _MUNKALAP_SZAM_ = "MUNKALAP_SZAM";
    public static String _NEV_HISTORY_ = "NEV_HISTORY";
    public static String _CIM_HISTORY_ = "CIM_HISTORY";
    public static String _EMAIL_HISTORY_= "EMAIL_HISTORY";
    public static String _TELEFON_HISTORY_ = "TELEFON_HISTORY";
    public static String _SERIAL_HISTORY_ = "SERIAL_HISTORY";
    public static String _EGYEB_SZ_NEV_ = "EGYEB_SZ_NEV";
    public static String _EGYEB_SZ_SERIAL_ = "EGYEB_SZ_SERIAL";
    public static String _NYELV_ = "NYELV";
    public static String _PENZNEM_ = "PENZNEM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DATA_SAVE = getPreferences(MODE_PRIVATE);
        loadLocale();

        setContentView(R.layout.activity_main);






/*
1 Logo_path
2 Excel_path
3 CEG_NEV
4 CEG_CIM
5 CEG_TEL
6 CEG_MAIL
7 EXCEL_BOOL 0 TRUE 1 FALSE
8 CSILLAG_MEGJEGYZES
9 KERET_MEGJEGYZES
10 ELSO
11 MUNKALAP_SZAM



*/


        try
        {
            if(DATA_SAVE.getString(_ELSO_,_DEFAULT_).equals(_DEFAULT_))
            {



                Intent intent = new Intent(this,Settings.class);
                startActivity(intent);
            }




            Logo_path = MainActivity.DATA_SAVE.getString(_LOGO_PATH_,_DEFAULT_);

            Excel_path = MainActivity.DATA_SAVE.getString(_EXCEL_PATH_,_DEFAULT_);

            Ceges_adatok_nev_global =(DATA_SAVE.getString(_CEG_NEV_,_DEFAULT_));
            Ceges_adatok_cim_global =(DATA_SAVE.getString(_CEG_CIM_,_DEFAULT_));
            Ceges_adatok_phone_global =(DATA_SAVE.getString(_CEG_TEL_,_DEFAULT_));
            Ceges_adatok_email_global= (DATA_SAVE.getString(_CEG_MAIL_,_DEFAULT_));
            Excel_hasznalat_checkbox_global= (DATA_SAVE.getString(_EXCEL_BOOL_,_DEFAULT_));
            Csillagos_megjegyzes_global= (DATA_SAVE.getString(_CSILLAG_MEGJEGYZES_,_DEFAULT_));
            Keretes_megjegyzes_global= (DATA_SAVE.getString(_KERET_MEGJEGYZES_,_DEFAULT_));
            Munkalap_szam= (DATA_SAVE.getString(_MUNKALAP_SZAM_,_DEFAULT_));
            Penz_nem_global = (DATA_SAVE.getString(_PENZNEM_,_DEFAULT_));
            switch(Penz_nem_global)
            {
                case "HUF":
                {
                    Penz_nem_global = "Ft";
                }
                case "USD":
                {
                    Penz_nem_global = "$";

                }
                case "EUR":
                {
                    Penz_nem_global = "€";

                }

            }





        }catch (Exception e)
        {
            Log.println( Log.DEBUG,"gyerünk már","oks");
        }



    }

    public void LoadLanguage(String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        //recreate();

    }

    public void loadLocale()
    {
        try{
            Nyelv_global= (DATA_SAVE.getString(_NYELV_,_DEFAULT_));

            if(Nyelv_global.equals("Hungary"))
            {
                LoadLanguage("hu");
            }
            if(Nyelv_global.equals("English"))
            {
                LoadLanguage("en");
            }

        }
        catch (Exception e)
        {
            Log.println(Log.ERROR,"Nyelv",e.toString());

        }

    }
    public void Munkalap_button(View v)
    {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1000);
            } else {
                Intent intent = new Intent(this,Munkalap.class);
                startActivity(intent);
            }
        } else {

        }

    }

    public void Beallitasok_button(View v)
    {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1000);
            } else {
                finish();
                Intent intent = new Intent(this,Settings.class);
                startActivity(intent);

            }
        } else {

        }


    }
    public void Tobber_akar(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://techdoki.hu/"));
        startActivity(browserIntent);

    }
    public void Facebook_link(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/techdoki/"));
        startActivity(browserIntent);

    }

    public void Weboldal(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://techdoki.hu/"));
        startActivity(browserIntent);

    }


    public void Close_button(View v)
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Biztos be akarja zárni?");
        builder.setCancelable(true);
        builder.setNegativeButton("Mégse", new DialogInterface.OnClickListener()
        {
            @Override
            public  void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Bezár", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    @Override
    public void onBackPressed()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Biztos be akarja zárni?");
        builder.setCancelable(true);
        builder.setNegativeButton("Mégse", new DialogInterface.OnClickListener()
        {
            @Override
            public  void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Bezár", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
