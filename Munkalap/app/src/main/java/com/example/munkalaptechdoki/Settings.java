package com.example.munkalaptechdoki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Settings extends AppCompatActivity {

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

    */


    Button Logo_tallozas ;
    Button Excel_tallozas;
    Button Mentes;



    public  EditText Ceges_adatok_nev;
    public  EditText Ceges_adatok_cim;
    public  EditText Ceges_adatok_phone;
    public  EditText Ceges_adatok_email;
    public  EditText Csillagos_megjegyzes;
    public  EditText Keretes_megjegyzes;
    public  Spinner Penz_nem;

    public static CheckBox Excel_hasznalata;
    public static CheckBox Munkalap_1;
    public static CheckBox Munkalap_2;

    public  Spinner Nyelv_valaszto;


    public  String Path;



    private static int RESULT_LOAD_IMAGE = 1;
    private static int RESULT_LOAD_EXCEL = 2;


    public static ImageView Logo_elonezet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Excel_tallozas = findViewById(R.id.szolgaltatasok_tallozasa_settings);


        Logo_tallozas = findViewById(R.id.logo_tallozas);

        Munkalap_1 = findViewById(R.id.Munkalap_verzio1);
        Munkalap_2 = findViewById(R.id.Munkalap_verzio2);

        Ceges_adatok_nev = findViewById(R.id.Ceg_nev_settings);
        Ceges_adatok_cim = findViewById(R.id.Ceg_cim_settings);
        Ceges_adatok_phone = findViewById(R.id.Ceg_phone_settings);
        Ceges_adatok_email = findViewById(R.id.Ceg_email_settings);
        Csillagos_megjegyzes = findViewById(R.id.Csillagosresz_settings);
        Keretes_megjegyzes = findViewById(R.id.Keretesszoveg_settings);


        Logo_elonezet = findViewById(R.id.Logo_elonezet_settings);

        Excel_hasznalata = findViewById(R.id.Excel_tallozas_vnem);

        Mentes = findViewById(R.id.Mentes_settings);

        Nyelv_valaszto = findViewById(R.id.Select_language);
        Penz_nem = findViewById(R.id.Select_penz_nem);
        Penz_nem.setSelection(((ArrayAdapter)Penz_nem.getAdapter()).getPosition(MainActivity.DATA_SAVE.getString(MainActivity._PENZNEM_,MainActivity._DEFAULT_)));

        Nyelv_valaszto.setSelection(((ArrayAdapter)Nyelv_valaszto.getAdapter()).getPosition(MainActivity.DATA_SAVE.getString(MainActivity._NYELV_,MainActivity._DEFAULT_)));
        Log.println(Log.ERROR,"Nyelv: ",MainActivity.DATA_SAVE.getString(MainActivity._NYELV_,MainActivity._DEFAULT_));

        Nyelv_valaszto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(Nyelv_valaszto.getSelectedItem().toString().equals("Hungary") && !Locale.getDefault().toString().equals("hu"))
                {

                    setLocale("hu");
                    MainActivity.DATA_SAVE.edit().putString(MainActivity._NYELV_,"Hungary").apply();
                    recreate();
                }

                if(Nyelv_valaszto.getSelectedItem().toString().equals("English")&& !Locale.getDefault().toString().equals("en"))
                {
                    setLocale("en");
                    MainActivity.DATA_SAVE.edit().putString(MainActivity._NYELV_,"English").apply();
                    recreate();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1000);
            }
        }

            try
        {
            Bitmap logo_temp = BitmapFactory.decodeFile(MainActivity.DATA_SAVE.getString(MainActivity._LOGO_PATH_,MainActivity._DEFAULT_));
            Logo_elonezet.setImageBitmap(logo_temp);

            if(MainActivity.Munkalap_szam.equals("1") || MainActivity.Munkalap_szam.equals(MainActivity._DEFAULT_))
            {
                Munkalap_1.setChecked(true);
                Munkalap_2.setChecked(false);
            }
            if (MainActivity.Munkalap_szam.equals("2"))
            {
                Munkalap_2.setChecked(true);
                Munkalap_1.setChecked(false);
            }
            if(!MainActivity.DATA_SAVE.getString(MainActivity._CEG_NEV_,MainActivity._DEFAULT_).equals(MainActivity._DEFAULT_))
            {
                Ceges_adatok_nev.setText(MainActivity.DATA_SAVE.getString(MainActivity._CEG_NEV_,MainActivity._DEFAULT_));
                Ceges_adatok_cim.setText(MainActivity.DATA_SAVE.getString(MainActivity._CEG_CIM_,MainActivity._DEFAULT_));
                Ceges_adatok_phone.setText(MainActivity.DATA_SAVE.getString(MainActivity._CEG_TEL_,MainActivity._DEFAULT_));
                Ceges_adatok_email.setText(MainActivity.DATA_SAVE.getString(MainActivity._CEG_MAIL_,MainActivity._DEFAULT_));
                Csillagos_megjegyzes.setText(MainActivity.DATA_SAVE.getString(MainActivity._CSILLAG_MEGJEGYZES_,MainActivity._DEFAULT_));
                Keretes_megjegyzes.setText(MainActivity.DATA_SAVE.getString(MainActivity._KERET_MEGJEGYZES_,MainActivity._DEFAULT_));

            }
            if(MainActivity.Excel_hasznalat_checkbox_global.equals("1")||MainActivity.Excel_hasznalat_checkbox_global.equals(MainActivity._DEFAULT_))
            {

                Excel_hasznalata.setChecked(false);
                Excel_tallozas.setVisibility(View.GONE);
            }
            else
            {

                Excel_hasznalata.setChecked(true);
                Excel_tallozas.setVisibility(View.VISIBLE);
            }


        }
        catch (Exception e)
        {


        }


    }



    public void setLocale(String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());


    }


    public void get_logo_path(View v) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);


    }



    public  void  get_services_path(View v)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, RESULT_LOAD_EXCEL);


    }

    public void Hasznaljon_excelt(View v)
    {
        if(Excel_hasznalata.isChecked())
        {
            Excel_tallozas.setVisibility(View.VISIBLE);
            MainActivity.DATA_SAVE.edit().putString(MainActivity._EXCEL_BOOL_,"0").apply();
            MainActivity.Excel_hasznalat_checkbox_global="0";

            Excel_hasznalat_dialog excelhasznalat_dialog = new Excel_hasznalat_dialog();
            excelhasznalat_dialog.show(getSupportFragmentManager(),"my_dialog");

        }
        else
        {
            Excel_tallozas.setVisibility(View.GONE);
            MainActivity.DATA_SAVE.edit().putString(MainActivity._EXCEL_BOOL_,"1").apply();
            MainActivity.Excel_hasznalat_checkbox_global="1";
        }

    }



    public void Munkalap_valtas(View v)
    {
        if(Munkalap_1.isChecked())
        {

            MainActivity.Munkalap_szam="1";
            MainActivity.DATA_SAVE.edit().putString(MainActivity._MUNKALAP_SZAM_,"1").apply();
            Munkalap_2.setChecked(false);
        }
    }
    public void Munkalap_valtas2(View v)
    {

        if (Munkalap_2.isChecked())
        {
            Munkalap_1.setChecked(false);
            MainActivity.Munkalap_szam="2";
            MainActivity.DATA_SAVE.edit().putString(MainActivity._MUNKALAP_SZAM_,"2").apply();

        }
    }

    public void Beallitasok_mentese(View v)
    {
        try
        {

            MainActivity.DATA_SAVE.edit().putString(MainActivity._ELSO_,"1").apply();
            if(!Ceges_adatok_nev.getText().toString().equals(""))
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._CEG_NEV_,Ceges_adatok_nev.getText().toString()).apply();
                MainActivity.Ceges_adatok_nev_global = Ceges_adatok_nev.getText().toString();

            }
            else
            {

                Toast.makeText(this, "Nem lehet üres a név!"+Ceges_adatok_nev.getText().toString(), Toast.LENGTH_SHORT).show();
                return;
            }


            if(!Ceges_adatok_cim.getText().toString().equals(""))
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._CEG_CIM_,Ceges_adatok_cim.getText().toString()).apply();
                MainActivity.Ceges_adatok_cim_global = Ceges_adatok_cim.getText().toString();
            }
            else
            {

                Toast.makeText(this, "Nem lehet üres a cím! "+Ceges_adatok_cim.getText().toString(), Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Ceges_adatok_phone.getText().toString().equals(""))
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._CEG_TEL_,Ceges_adatok_phone.getText().toString()).apply();
                MainActivity.Ceges_adatok_phone_global = Ceges_adatok_phone.getText().toString();
            }
            else
            {
                Toast.makeText(this, "Nem lehet üres a telefonszám!"+Ceges_adatok_phone.getText().toString(), Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Ceges_adatok_email.getText().toString().equals(""))
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._CEG_MAIL_,Ceges_adatok_email.getText().toString()).apply();
                MainActivity.Ceges_adatok_email_global = Ceges_adatok_email.getText().toString();
            }
            else
            {

                Toast.makeText(this, "Nem lehet üres a E-mail"+Ceges_adatok_email.getText().toString(), Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Csillagos_megjegyzes.getText().equals(""))
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._CSILLAG_MEGJEGYZES_,Csillagos_megjegyzes.getText().toString()).apply();
                MainActivity.Csillagos_megjegyzes_global = Csillagos_megjegyzes.getText().toString();

            }
            if(Csillagos_megjegyzes.getText().equals(""))
            {
                Csillagos_megjegyzes.setText(" ");
                MainActivity.DATA_SAVE.edit().putString(MainActivity._CSILLAG_MEGJEGYZES_,Csillagos_megjegyzes.getText().toString()).apply();
                MainActivity.Csillagos_megjegyzes_global = Csillagos_megjegyzes.getText().toString();

            }

            if(!Keretes_megjegyzes.getText().equals(""))
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._KERET_MEGJEGYZES_,Keretes_megjegyzes.getText().toString()).apply();
                MainActivity.Keretes_megjegyzes_global=Keretes_megjegyzes.getText().toString();

            }
            if(Keretes_megjegyzes.getText().equals(""))
            {
                Keretes_megjegyzes.setText(" ");
                MainActivity.DATA_SAVE.edit().putString(MainActivity._KERET_MEGJEGYZES_,Keretes_megjegyzes.getText().toString()).apply();
                MainActivity.Keretes_megjegyzes_global=Keretes_megjegyzes.getText().toString();

            }

            if (Excel_hasznalata.isChecked())
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._EXCEL_PATH_,MainActivity.Excel_path).apply();
                MainActivity.DATA_SAVE.edit().putString(MainActivity._EXCEL_BOOL_,"0").apply();
                Log.println(Log.ERROR,MainActivity._EXCEL_BOOL_,"0");
            }
            else
            {
                MainActivity.DATA_SAVE.edit().putString(MainActivity._EXCEL_BOOL_,"1").apply();
                Log.println(Log.ERROR,MainActivity._EXCEL_BOOL_,"1");
            }

            if(Munkalap_1.isChecked())
            {

                MainActivity.Munkalap_szam="1";
                MainActivity.DATA_SAVE.edit().putString(MainActivity._MUNKALAP_SZAM_,"1").apply();
                Munkalap_2.setChecked(false);
            }
            if(MainActivity.Logo_path.equals("")||MainActivity.Logo_path.equals(null)||MainActivity.Logo_path.equals(MainActivity._DEFAULT_))
            {
                Logo_kotelezo();
                return;
            }
            MainActivity.DATA_SAVE.edit().putString(MainActivity._PENZNEM_,Penz_nem.getSelectedItem().toString()).apply();
            MainActivity.Penz_nem_global = Penz_nem.getSelectedItem().toString();

            Toast.makeText(this, "Sikerült a mentés", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);


        }
        catch (Exception e)
        {
            Toast.makeText(this, "Mentés hiba: "+e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_EXCEL && resultCode == RESULT_OK && null != data)
        {
            //String Fpath = data.getData().getEncodedPath();
            Uri Exceldata = data.getData();

            MainActivity.Excel_path = getRealPathFromURI(Exceldata);
            Log.println(Log.ERROR,"Eleresi: ",MainActivity.Excel_path);
            super.onActivityResult(requestCode, resultCode, data);
        }


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Path = picturePath;



            MainActivity.DATA_SAVE.edit().putString(MainActivity._LOGO_PATH_,picturePath).apply();
            Bitmap logo_temp = BitmapFactory.decodeFile(picturePath);
            Logo_elonezet.setImageBitmap(logo_temp);
            MainActivity.Logo_path = picturePath;

        }
    }

    public void Logo_kotelezo()
    {

            Toast.makeText(this, "Logo feltöltése kötelező (Ha nincs, akkor egy fehér képet tallozzon be.)", Toast.LENGTH_SHORT).show();

    }
    public void onBackPressed()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);

        builder.setMessage("Kilépés előtt mentsen!");
        builder.setCancelable(true);
        builder.setNegativeButton("Vissza", new DialogInterface.OnClickListener()
        {
            @Override
            public  void onClick(DialogInterface dialogInterface, int i)
            {


                dialogInterface.cancel();

            }
        });
        builder.setPositiveButton("Bezárás", new DialogInterface.OnClickListener()
        {
            @Override
            public  void onClick(DialogInterface dialogInterface, int i)
            {

                if(MainActivity.Logo_path.equals("")||MainActivity.Logo_path.equals(null)||MainActivity.Logo_path.equals(MainActivity._DEFAULT_))
                {
                    dialogInterface.cancel();
                    Logo_kotelezo();
                }
                else
                {
                    finish();
                }

            }
        });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
