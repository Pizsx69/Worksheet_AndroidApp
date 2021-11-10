package com.example.munkalaptechdoki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.AutoCompleteTextView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;





import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.widget.TextView;

import org.apache.commons.collections4.splitmap.AbstractIterableGetMapDecorator;
import org.apache.poi.hpsf.Array;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import java.util.Iterator;
import java.util.TimeZone;

import static com.itextpdf.text.FontFactory.getFont;


public class Munkalap extends AppCompatActivity {


    public static final int STORAGE_CODE = 1000;
    public static int ALAIRAS_CHECKER = 0; // 0 munkát végző -- 1 megrendelő

    //history

    public String Nev_history;
    public ArrayList<String> Nev_history_ = new ArrayList<>();

    public String Cim_history;
    public ArrayList<String> Cim_history_ = new ArrayList<String>();

    public String Email_history;
    public ArrayList<String> Email_history_ = new ArrayList<String>();

    public String Telefon_history;
    public ArrayList<String> Telefon_history_ = new ArrayList<String>();

    public String Serial_history;
    public ArrayList<String> Serial_history_ = new ArrayList<String>();

    public String Egyeb_szolgalatas_nev_history;
    public ArrayList<String> Egyeb_szolgaltatas_nev_history_= new ArrayList<String>();

    public String Egyeb_szolgalatas_serial_history;
    public ArrayList<String> Egyeb_szolgaltatas_serial_history_ = new ArrayList<String>();










    ///history


   // public static final String  fontpath = Environment.getExternalStorageDirectory() + "/Munkalap/MyFont-Regular.ttf";
    public static final String  fontpath = "/assets/times.ttf";

    public static Image megrendelo_alatiras_kep;
    public static Image munkavegzo_alatiras_kep;

    public Button mSzolgaltatas_hozzad;

    AutoCompleteTextView  mTextNev;
    AutoCompleteTextView mTextCim;
    AutoCompleteTextView mTextEmail;
    AutoCompleteTextView mTextPhone;

    EditText mTextSzolg_ar;
    EditText mTextSzolg_db;
    AutoCompleteTextView mTextSzolg_Serial;
    EditText mTextAtadta;

    EditText mTextMegjegyzes;

    AutoCompleteTextView Egyeb_szolgaltatas_neve;
    EditText Egyeb_szolgaltatas_db;
    AutoCompleteTextView Egyeb_szolgaltatas_serial;
    EditText Egyeb_szolgaltatas_ar;


    CheckBox Megj_check;
    CheckBox Atv_check;
    CheckBox Egyeb_check;

    ConstraintLayout Egyeb_Szolgalatasok;
    Spinner spinner;
    Spinner Statusz_spinner;
    Spinner Egyseg;
    Spinner Egyseg_2;

    List<String> list_szolgaltatas_neve;
    List<String> list_szolgaltatas_ara;
    ArrayAdapter<String> SpinnerAdapter;

    List<String> Szolgaltatas_fo_lista;
    List<String> Egyeb_szolgaltatas_fo_lista;


    public static int SZOLGALTATASOK_COUNT = 5;

    int OSSZEG;






public void History_beallitas()
{
    Nev_history = MainActivity.DATA_SAVE.getString(MainActivity._NEV_HISTORY_,MainActivity._DEFAULT_);

    if(!Nev_history.equals(MainActivity._DEFAULT_))
    {
        String[] Nev_history_Array = Nev_history.split(",");
        try
        {
            for (int i = 0; i< Nev_history_Array.length;i++)
            {
                Nev_history_.add(Nev_history_Array[i]);
            }
        }
        catch (Exception e)
        {
        }
    }

    Cim_history = MainActivity.DATA_SAVE.getString(MainActivity._CIM_HISTORY_,MainActivity._DEFAULT_);
    if(!Cim_history.equals(MainActivity._DEFAULT_))
    {
        String[] Cim_history_Array = Cim_history.split(",");
        try
        {
            for(int i = 0; i < Cim_history_Array.length;i++)
            {
                Cim_history_.add(Cim_history_Array[i]);
            }
        }
        catch (Exception e)
        {}

    }

    Email_history = MainActivity.DATA_SAVE.getString(MainActivity._EMAIL_HISTORY_,MainActivity._DEFAULT_);
    if(!Email_history.equals(MainActivity._DEFAULT_))
    {
        String[] Email_history_Array = Email_history.split(",");
        try
        {
            for(int i = 0; i < Email_history_Array.length;i++)
            {
                Email_history_.add(Email_history_Array[i]);
            }
        }
        catch (Exception e)
        {}
    }
    Telefon_history = MainActivity.DATA_SAVE.getString(MainActivity._TELEFON_HISTORY_,MainActivity._DEFAULT_);
    if(!Telefon_history.equals(MainActivity._DEFAULT_))
    {
        String[] Telefon_history_Array = Telefon_history.split(",");
        try
        {
            for(int i = 0; i < Telefon_history_Array.length;i++)
            {
                Telefon_history_.add(Telefon_history_Array[i]);
            }
        }
        catch (Exception e)
        {}
    }

    Serial_history = MainActivity.DATA_SAVE.getString(MainActivity._SERIAL_HISTORY_,MainActivity._DEFAULT_);
    if(!Serial_history.equals(MainActivity._DEFAULT_))
    {
        String[] Serial_history_Array = Serial_history.split(",");
        try
        {
            for(int i = 0; i < Serial_history_Array.length;i++)
            {
                Serial_history_.add(Serial_history_Array[i]);
            }
        }
        catch (Exception e)
        {}
    }

    Egyeb_szolgalatas_nev_history = MainActivity.DATA_SAVE.getString(MainActivity._EGYEB_SZ_NEV_,MainActivity._DEFAULT_);
    if(!Egyeb_szolgalatas_nev_history.equals(MainActivity._DEFAULT_))
    {
        String[] Egyeb_sz_nev_history_Array = Egyeb_szolgalatas_nev_history.split(",");
        try
        {
            for(int i = 0; i < Egyeb_sz_nev_history_Array.length;i++)
            {
                Egyeb_szolgaltatas_nev_history_.add(Egyeb_sz_nev_history_Array[i]);
            }
        }
        catch (Exception e)
        {}
    }
    Egyeb_szolgalatas_serial_history = MainActivity.DATA_SAVE.getString(MainActivity._EGYEB_SZ_SERIAL_,MainActivity._DEFAULT_);
    if(!Egyeb_szolgalatas_serial_history.equals(MainActivity._DEFAULT_))
    {
        String[] Egyeb_sz_serial_history_Array = Egyeb_szolgalatas_serial_history.split(",");
        try
        {
            for(int i = 0; i < Egyeb_sz_serial_history_Array.length;i++)
            {
                Egyeb_szolgaltatas_serial_history_.add(Egyeb_sz_serial_history_Array[i]);
            }
        }
        catch (Exception e)
        {}
    }
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        History_beallitas();



        setContentView(R.layout.activity_munkalap);

        mSzolgaltatas_hozzad = findViewById(R.id.szolgaltatas_hozzad);

        mTextNev=findViewById(R.id.nev_edittext);
        ArrayAdapter<String> adapter_nev = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, Nev_history_);
        mTextNev.setThreshold(1);
        mTextNev.setAdapter(adapter_nev);


        mTextCim=findViewById(R.id.cim_edittext);
        ArrayAdapter<String> adapter_cim = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,Cim_history_);
        mTextCim.setThreshold(1);
        mTextCim.setAdapter(adapter_cim);


        mTextEmail=findViewById(R.id.email_edittext);
        ArrayAdapter<String> adapter_email = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,Email_history_);
        mTextEmail.setThreshold(1);
        mTextEmail.setAdapter(adapter_cim);


        mTextPhone = findViewById(R.id.phone_edittext);
        ArrayAdapter<String> adapter_phone = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,Telefon_history_);
        mTextPhone.setThreshold(1);
        mTextPhone.setAdapter(adapter_phone);

        mTextSzolg_Serial = findViewById(R.id.szolgaltatas_serial);
        ArrayAdapter<String> adapter_serial = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,Serial_history_);
        mTextSzolg_Serial.setThreshold(1);
        mTextSzolg_Serial.setAdapter(adapter_serial);


        mTextSzolg_ar = findViewById(R.id.szolgaltatas_ar);
        mTextSzolg_db = findViewById(R.id.szolgaltatas_mennyiseg);
        mTextAtadta = findViewById(R.id.atadta_kellekek);
        mTextAtadta.setVisibility(View.GONE);
        mTextMegjegyzes = findViewById(R.id.megjegyzes_edittext);
        mTextMegjegyzes.setVisibility(View.GONE);
        Egyeb_Szolgalatasok=findViewById(R.id.Egyeb_szolgaltatasok);
        Egyeb_Szolgalatasok.setVisibility(View.GONE);

        Egyeb_szolgaltatas_neve = findViewById(R.id.Egyeb_szolgaltatas_neve);
        ArrayAdapter<String> adapter_egyeb_sz_nev = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,Egyeb_szolgaltatas_nev_history_);
        Egyeb_szolgaltatas_neve.setThreshold(1);
        Egyeb_szolgaltatas_neve.setAdapter(adapter_egyeb_sz_nev);

        Egyeb_szolgaltatas_ar = findViewById(R.id.egyeb_szolgaltatas_ar);
        Egyeb_szolgaltatas_db = findViewById(R.id.egyeb_szolgaltatas_mennyiseg);
        Egyeb_szolgaltatas_serial = findViewById(R.id.egyeb_szolgaltatas_serial);
        ArrayAdapter<String> adapter_egyeb_sz_serial = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,Egyeb_szolgaltatas_serial_history_);
        Egyeb_szolgaltatas_serial.setThreshold(1);
        Egyeb_szolgaltatas_serial.setAdapter(adapter_egyeb_sz_serial);

        Megj_check = findViewById(R.id.checkBox_Megjegyzes);
        Atv_check=findViewById(R.id.checkBox2_Atvedtem);
        Egyeb_check = findViewById(R.id.checkBox3_Egyeb);

        spinner = findViewById(R.id.szolgaltatasok_spinner);
        Statusz_spinner = findViewById(R.id.statusz_spinner);
        Egyseg = findViewById(R.id.Egyseg);
        Egyseg_2= findViewById(R.id.egyseg_2);


        Szolgaltatas_fo_lista = new ArrayList<String>();
        list_szolgaltatas_neve = new ArrayList<String>();
        list_szolgaltatas_ara = new ArrayList<String>();
        Egyeb_szolgaltatas_fo_lista = new ArrayList<String>();





        if(MainActivity.Excel_hasznalat_checkbox_global.equals("0"))
        {
            Excelolvasas();
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                mTextSzolg_ar.setText(list_szolgaltatas_ara.get(spinner.getSelectedItemPosition()).toString());
                mTextSzolg_db.setText("1");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        if(MainActivity.Excel_hasznalat_checkbox_global.equals("1") || MainActivity.Excel_hasznalat_checkbox_global.equals("default"))
        {

            spinner.setVisibility(View.GONE);
            Egyseg.setVisibility(View.GONE);
            mTextSzolg_db.setVisibility(View.GONE);
            mTextSzolg_Serial.setVisibility(View.GONE);
            mTextSzolg_ar.setVisibility(View.GONE);

            Egyeb_check.setChecked(true);
            Egyeb_Szolgalatasok.setVisibility(View.VISIBLE);
            mSzolgaltatas_hozzad.setVisibility(View.GONE);

        }
        if(MainActivity.Excel_hasznalat_checkbox_global.equals("0"))
        {
            Excelolvasas();
            mSzolgaltatas_hozzad.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            Egyseg.setVisibility(View.VISIBLE);
            mTextSzolg_db.setVisibility(View.VISIBLE);
            mTextSzolg_Serial.setVisibility(View.VISIBLE);
            mTextSzolg_ar.setVisibility(View.VISIBLE);

            Egyeb_check.setChecked(false);
            Egyeb_Szolgalatasok.setVisibility(View.GONE);
        }


    }

    public void spinner_feltoltes()
    {

        SpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list_szolgaltatas_neve){
            //By using this method we will define how
            // the text appears before clicking a spinner
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#2ea3f2"));
                return v;
            }
            //By using this method we will define
            //how the listview appears after clicking a spinner
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#2ea3f2"));
                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                return v;
            }
        };
        SpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Set Adapter in the spinner
        spinner.setAdapter(SpinnerAdapter);

    }

    public void Megjegyzes_checkbox_event(View v)
    {
        if(Megj_check.isChecked())
        {
            mTextMegjegyzes.setVisibility(View.VISIBLE);
        }
        else
        {
            mTextMegjegyzes.setVisibility(View.GONE);

        }

    }
    public  void Atadta_checkbox_event(View v)
    {
        if(Atv_check.isChecked())
        {
            mTextAtadta.setVisibility(View.VISIBLE);
        }
        else
        {
            mTextAtadta.setVisibility(View.GONE);
        }

    }
    public void Egyeb_szolgaltatas_event(View v)
    {
        if(Egyeb_check.isChecked())
        {
            Egyeb_Szolgalatasok.setVisibility(View.VISIBLE);

        }
        else
        {
            Egyeb_Szolgalatasok.setVisibility(View.GONE);

        }

    }




    public void munkalap_mentes(View v) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, STORAGE_CODE);
            } else {
                if(MainActivity.Munkalap_szam.equals("1")) {
                    SavePDF();
                }
                else {
                    SavePDF_v2();
                }
            }
        } else {

        }
    }
    public void Szolgaltatas_hozzaadas(View v){
    if(!mTextSzolg_db.getText().toString().equals("")
    && !mTextSzolg_ar.getText().toString().equals("")
    && !mTextSzolg_Serial.getText().toString().equals(""))
    {
        Szolgaltatas_fo_lista.add(spinner.getSelectedItem().toString());
        Szolgaltatas_fo_lista.add(mTextSzolg_db.getText().toString());
        Szolgaltatas_fo_lista.add(Egyseg.getSelectedItem().toString());
        Szolgaltatas_fo_lista.add(mTextSzolg_ar.getText().toString());
        Szolgaltatas_fo_lista.add(mTextSzolg_Serial.getText().toString());

        Toast.makeText(Munkalap.this, getText(R.string.Szolgaltatas_hozzaadva), Toast.LENGTH_SHORT).show();
    }
    else
    {
        if(Egyeb_szolgaltatas_neve.getText().toString()!="" )
        {
            Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_nev_ures), Toast.LENGTH_SHORT).show();
        }
        if(Egyeb_szolgaltatas_db.getText().toString()!="" )
        {
            Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_egyseg_ures), Toast.LENGTH_SHORT).show();
        }
        if(Egyeb_szolgaltatas_ar.getText().toString()!="" )
        {
            Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_ara_ures), Toast.LENGTH_SHORT).show();
        }
        if(Egyeb_szolgaltatas_serial.getText().toString()!="" )
        {
            Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_serial_number_ures) , Toast.LENGTH_SHORT).show();
        }
    }

    }
    public void Egyeb_szolgaltatas_hozzaadas(View v)
    {
        if(Egyeb_szolgaltatas_neve.getText().toString()!="" &&
                Egyeb_szolgaltatas_db.getText().toString()!="" &&
                Egyeb_szolgaltatas_ar.getText().toString()!="" &&
                Egyeb_szolgaltatas_serial.getText().toString()!="")
        {

            Szolgaltatas_fo_lista.add(Egyeb_szolgaltatas_neve.getText().toString());
            Szolgaltatas_fo_lista.add(Egyeb_szolgaltatas_db.getText().toString());
            Szolgaltatas_fo_lista.add(Egyseg_2.getSelectedItem().toString());
            Szolgaltatas_fo_lista.add(Egyeb_szolgaltatas_ar.getText().toString());
            Szolgaltatas_fo_lista.add(Egyeb_szolgaltatas_serial.getText().toString());

            Egyeb_szolgaltatas_neve.setText("");
            Egyeb_szolgaltatas_db.setText("");
            Egyeb_szolgaltatas_ar.setText("");
            Egyeb_szolgaltatas_serial.setText("");

            Toast.makeText(Munkalap.this, getText(R.string.Szolgaltatas_hozzaadva), Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(Egyeb_szolgaltatas_neve.getText().toString()!="" )
            {
                Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_nev_ures), Toast.LENGTH_SHORT).show();
            }
            if(Egyeb_szolgaltatas_db.getText().toString()!="" )
            {
                Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_egyseg_ures), Toast.LENGTH_SHORT).show();
            }
            if(Egyeb_szolgaltatas_ar.getText().toString()!="" )
            {
                Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_ara_ures), Toast.LENGTH_SHORT).show();
            }
            if(Egyeb_szolgaltatas_serial.getText().toString()!="" )
            {
                Toast.makeText(Munkalap.this, getText(R.string.szolgaltatas_serial_number_ures), Toast.LENGTH_SHORT).show();
            }

        }



    }
    public  void Excelolvasas() {

        try {
            POIFSFileSystem myFileSystem = new POIFSFileSystem(new File(MainActivity.Excel_path));
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno =0;

            while (rowIter.hasNext()) {

                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            list_szolgaltatas_neve.add(myCell.toString());

                        }else if (colno==1) {
                            myCell.setCellType(CellType.STRING);
                            list_szolgaltatas_ara.add(myCell.toString());



                        }
                        colno++;

                    }

                }
                rowno++;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString() , Toast.LENGTH_LONG).show();
            Log.println(Log.ERROR,"Nem megy: ",MainActivity.Excel_path);
        }
        spinner_feltoltes();

    }

    private void SavePDF() {
        Document doc = new Document();
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mTextNev.getText() + ".pdf";


        try {
            PdfWriter.getInstance(doc, new FileOutputStream(mFilePath));
            doc.open();
            //HEADER
            Image image = Image.getInstance(MainActivity.Logo_path);



            doc.addAuthor("TechDoki Munkalap");
            //para = new Paragraph();
            PdfPTable HEADER_table = new PdfPTable(2);
            HEADER_table.setWidthPercentage(100);

            Font SajatFontUres = getFont(fontpath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 3);
            Font SajatFont = getFont(fontpath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
            Font SajatFont_vastag = getFont(fontpath,BaseFont.IDENTITY_H, 12, Font.BOLD);


            PdfPCell HEADER_cegadatok = new PdfPCell(new Phrase(new Paragraph(
                    MainActivity.Ceges_adatok_nev_global+"\n"+
                            MainActivity.Ceges_adatok_cim_global+"\n"+
                            MainActivity.Ceges_adatok_phone_global+"\n"+
                            MainActivity.Ceges_adatok_email_global,SajatFont)));


            HEADER_cegadatok.setUseVariableBorders(true);
            HEADER_cegadatok.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            HEADER_cegadatok.setBorderColorLeft(BaseColor.WHITE);
            //HEADER_cegadatok.setBorderColorBottom(BaseColor.BLACK);
            HEADER_cegadatok.setBorderColorBottom(BaseColor.WHITE);
            HEADER_cegadatok.setFixedHeight(55);

            PdfPCell HEADER_ceglogo= new PdfPCell(image);
            HEADER_ceglogo.setUseVariableBorders(true);
            HEADER_ceglogo.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            HEADER_ceglogo.setBorderColorLeft(BaseColor.WHITE);
            //HEADER_ceglogo.setBorderColorBottom(BaseColor.BLACK);
            HEADER_ceglogo.setBorderColorBottom(BaseColor.WHITE);
            HEADER_ceglogo.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            HEADER_ceglogo.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            HEADER_ceglogo.setFixedHeight(55);






            PdfPCell HEADER_ures= new PdfPCell(new Phrase(new Paragraph(" ",SajatFontUres)));
            HEADER_ures.setUseVariableBorders(true);
            HEADER_ures.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            HEADER_ures.setBorderColorLeft(BaseColor.WHITE);
            HEADER_ures.setBorderColorBottom(BaseColor.BLACK);
            //HEADER_ures.setBorderColorBottom(BaseColor.WHITE);
            HEADER_ures.setFixedHeight(5);




            PdfPCell HEADER_ures2= new PdfPCell(new Phrase(new Paragraph(" ",SajatFontUres)));
            HEADER_ures2.setUseVariableBorders(true);
            HEADER_ures2.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            HEADER_ures2.setBorderColorLeft(BaseColor.WHITE);
            HEADER_ures2.setBorderColorBottom(BaseColor.BLACK);
            HEADER_ures2.setFixedHeight(5);


            HEADER_table.addCell(HEADER_cegadatok);
            HEADER_table.addCell(HEADER_ceglogo);
            HEADER_table.addCell(HEADER_ures);
            HEADER_table.addCell(HEADER_ures2);
            doc.add(HEADER_table);


            //HEADER CLOSE

            //MUNKALAP
            Font munkalap_font = getFont(fontpath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 30);
            //Font munkalap_font=new Font(Font.FontFamily.TIMES_ROMAN,30.0f,Font.NORMAL,BaseColor.BLACK);
            Paragraph _munkalap_text=new Paragraph(getText(R.string.Munkalap_title).toString(),munkalap_font);

            _munkalap_text.setAlignment(Paragraph.ALIGN_CENTER);

            doc.add(_munkalap_text);

            //MUNKALAP CLOSE


            //SZEMELYES ADATOK

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable Szemelyes_adat = new PdfPTable(2);
            Szemelyes_adat.setWidthPercentage(100);



            PdfPCell Szemelyes_adat_nev = new PdfPCell(new Phrase(new Paragraph(getText(R.string.customer)+": "+mTextNev.getText(),SajatFont)));
            Szemelyes_adat_nev.setVerticalAlignment(Paragraph.ALIGN_CENTER);
            PdfPCell Szemelyes_adat_cim = new PdfPCell(new Phrase(new Paragraph(getText(R.string.Ugyfel_cim)+": "+mTextCim.getText(),SajatFont)));
            Szemelyes_adat_cim.setVerticalAlignment(Paragraph.ALIGN_CENTER);
            PdfPCell Szemelyes_adat_email = new PdfPCell(new Phrase(new Paragraph(getText(R.string.Ugyfel_email)+": "+mTextEmail.getText(),SajatFont)));
            Szemelyes_adat_email.setVerticalAlignment(Paragraph.ALIGN_CENTER);
            PdfPCell Szemelyes_adat_phone = new PdfPCell(new Phrase(new Paragraph(getText(R.string.Ugyfel_tel)+": "+mTextPhone.getText(),SajatFont)));
            Szemelyes_adat_phone.setVerticalAlignment(Paragraph.ALIGN_CENTER);


            Szemelyes_adat.addCell(Szemelyes_adat_nev);
            Szemelyes_adat.addCell(Szemelyes_adat_cim);
            Szemelyes_adat.addCell(Szemelyes_adat_email);
            Szemelyes_adat.addCell(Szemelyes_adat_phone);

            doc.add(Szemelyes_adat);

            //SZEMELYES ADATOK CLOSE





            //SZOLGÁLTATÁSOK TÁBLA

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable SZOLGT_table = new PdfPTable(5); // 4
            SZOLGT_table.setWidthPercentage(100);

            PdfPCell SZOLGR_SZOLGALTATAS_ = new PdfPCell(new Phrase(new Paragraph(getText(R.string.Szolgaltatas_title).toString(),SajatFont_vastag)));
            SZOLGR_SZOLGALTATAS_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            SZOLGR_SZOLGALTATAS_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell SZOLGR_DB_ = new PdfPCell(new Phrase(new Paragraph(getText(R.string.Mennyiseg_string).toString(),SajatFont_vastag)));
            SZOLGR_DB_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            SZOLGR_DB_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell SZOLGR_AR_ = new PdfPCell(new Phrase(new Paragraph(getText(R.string.Egyseg_ar_title).toString(),SajatFont_vastag)));
            SZOLGR_AR_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            SZOLGR_AR_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell SZOLGR_EGYSEG_ = new PdfPCell(new Phrase(new Paragraph(getText(R.string.ar_string).toString(),SajatFont_vastag)));
            SZOLGR_EGYSEG_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            SZOLGR_EGYSEG_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell SZOLGR_SERIAL_ = new PdfPCell(new Phrase(new Paragraph(getText(R.string.sorozat_string).toString(),SajatFont_vastag)));
            SZOLGR_SERIAL_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            SZOLGR_SERIAL_.setVerticalAlignment(Paragraph.ALIGN_CENTER);


            SZOLGT_table.addCell(SZOLGR_SZOLGALTATAS_);
            SZOLGT_table.addCell(SZOLGR_DB_);
            SZOLGT_table.addCell(SZOLGR_AR_);
            SZOLGT_table.addCell(SZOLGR_EGYSEG_);
            SZOLGT_table.addCell(SZOLGR_SERIAL_);



            for (int i =0; i<Szolgaltatas_fo_lista.size();i+=SZOLGALTATASOK_COUNT)
            {
                PdfPCell Szolg_neve_ = new PdfPCell(new Phrase(new Paragraph(Szolgaltatas_fo_lista.get(i),SajatFont)));
                PdfPCell Szolg_db_ = new PdfPCell(new Phrase(new Paragraph(Szolgaltatas_fo_lista.get(i+1)+" "+Szolgaltatas_fo_lista.get(i+2),SajatFont)));
                PdfPCell Szolg_ar_ = new PdfPCell();
                PdfPCell Szolg_egyseg = new PdfPCell(new Phrase(new Paragraph(Szolgaltatas_fo_lista.get(i+3)+" "+MainActivity.Penz_nem_global)));
                if (Szolgaltatas_fo_lista.get(i + 1) != "0" && Szolgaltatas_fo_lista.get(i + 1) != "1") {
                    int szorzo = Integer.parseInt(Szolgaltatas_fo_lista.get(i+1));
                    int ar = Integer.parseInt(Szolgaltatas_fo_lista.get(i+3));
                    int osszeg = szorzo*ar;
                    OSSZEG+=osszeg;
                    Szolg_ar_ = new PdfPCell(new Phrase(new Paragraph(String.valueOf(osszeg)+" "+MainActivity.Penz_nem_global,SajatFont)));

                }
                else
                {
                    Szolg_ar_ = new PdfPCell(new Phrase(new Paragraph(Szolgaltatas_fo_lista.get(i+3)+" "+MainActivity.Penz_nem_global,SajatFont)));
                    OSSZEG+=Integer.parseInt(Szolgaltatas_fo_lista.get(i+3));
                }



                PdfPCell Szolg_Serial_ = new PdfPCell(new Phrase(new Paragraph(Szolgaltatas_fo_lista.get(i+4),SajatFont)));

                Szolg_neve_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Szolg_neve_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

                Szolg_db_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Szolg_db_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

                Szolg_egyseg.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Szolg_egyseg.setVerticalAlignment(Paragraph.ALIGN_CENTER);

                Szolg_ar_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Szolg_ar_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

                Szolg_Serial_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Szolg_Serial_.setVerticalAlignment(Paragraph.ALIGN_CENTER);

                SZOLGT_table.addCell(Szolg_neve_);
                SZOLGT_table.addCell(Szolg_db_);
                SZOLGT_table.addCell(Szolg_egyseg);
                SZOLGT_table.addCell(Szolg_ar_);
                SZOLGT_table.addCell(Szolg_Serial_);
            }




            doc.add(SZOLGT_table);



            //SZOLGÁLTATÁSOK TÁBLA CLOSE


            //ÁTADÁSVÉTE:
            if(Atv_check.isChecked())
            {
                doc.add(new Paragraph(" "));
                PdfPTable Atadta = new PdfPTable(1);
                Atadta.setWidthPercentage(100);

                PdfPCell Atadta_text = new PdfPCell(new Phrase(new Paragraph(getText(R.string.atvettem)+":",SajatFont_vastag)));
                Atadta_text.setVerticalAlignment(Paragraph.ALIGN_CENTER);
                Atadta_text.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Atadta_text.setFixedHeight(20);

                PdfPCell Adas_kellekek = new PdfPCell(new Phrase(new Paragraph(mTextAtadta.getText().toString(),SajatFont)));
                Adas_kellekek.setFixedHeight(30);

                Atadta.addCell(Atadta_text);
                Atadta.addCell(Adas_kellekek);

                doc.add(Atadta);
            }



            //ÁTADÁSVÉTEL CLOSE


            //MEGJEGYZÉS
            if(Megj_check.isChecked())
            {
                doc.add(new Paragraph(" "));
                PdfPTable Megjegyzes = new PdfPTable(1);
                Megjegyzes.setWidthPercentage(100);

                PdfPCell Megjegyzes_text = new PdfPCell(new Phrase(new Paragraph(getText(R.string.comment).toString(),SajatFont_vastag)));
                Megjegyzes_text.setVerticalAlignment(Paragraph.ALIGN_CENTER);
                Megjegyzes_text.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                Megjegyzes_text.setFixedHeight(20);

                PdfPCell Megjegyzes_szoveg = new PdfPCell(new Phrase(new Paragraph(mTextMegjegyzes.getText().toString(),SajatFont)));

                Megjegyzes_szoveg.setFixedHeight(30);

                Megjegyzes.addCell(Megjegyzes_text);
                Megjegyzes.addCell(Megjegyzes_szoveg);
                doc.add(Megjegyzes);

                //STÁTUSZ!

                mTextMegjegyzes.setVisibility(View.VISIBLE);

            }

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            Paragraph Statusz_szoveg=new Paragraph(Statusz_spinner.getSelectedItem().toString(),SajatFont_vastag);
            Statusz_szoveg.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(Statusz_szoveg);
            //STÁTUSZ CLOSE
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));


            //ÖSSZEG

            Paragraph Osszeg  = new Paragraph( getString(R.string.Osszesen)+" "+OSSZEG+" "+MainActivity.Penz_nem_global ,SajatFont_vastag);
            Osszeg.setAlignment(Paragraph.ALIGN_RIGHT);

            doc.add(Osszeg);

            //ÖSSZEG END
            //ALÁÍRÁS ÉS ELFOGADÁS.

            Font Szemelyes_Adat_Font = getFont(fontpath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);
            ///Paragraph nem_szamla = new Paragraph("*a feltüntetett ár alanyi adómentes, bruttó összeg.\nJelen munkalap nem minősül könyvviteli bizonylatnak vagy számlának\n ",Szemelyes_Adat_Font);
            Paragraph nem_szamla = new Paragraph(MainActivity.Csillagos_megjegyzes_global,Szemelyes_Adat_Font);
            doc.add(nem_szamla);
            doc.add(new Paragraph(" "));
            PdfPTable Aszf_es_adatvedelem = new PdfPTable(1);
            Aszf_es_adatvedelem.setWidthPercentage(100);
            //PdfPCell Szerzodes_szoveg = new PdfPCell(new Phrase(new Paragraph("Elolvastam és elfogadom az Általános Szerződési Feltételeket és a Adatvédelmi Szabályzatban foglaltakat.",Szemelyes_Adat_Font)));
            PdfPCell Szerzodes_szoveg = new PdfPCell(new Phrase(new Paragraph(MainActivity.Keretes_megjegyzes_global,Szemelyes_Adat_Font)));
            Szerzodes_szoveg.setVerticalAlignment(Paragraph.ALIGN_CENTER);
            Aszf_es_adatvedelem.addCell(Szerzodes_szoveg);
            doc.add(Aszf_es_adatvedelem);


            PdfPTable Megrendeltem_ = new PdfPTable(1);
            Megrendeltem_.setWidthPercentage(100);
            PdfPCell Megrendeltem_szoveg = new PdfPCell(new Phrase(new Paragraph(getText(R.string.a_szolgaltatast_megrendeltem)+": ",Szemelyes_Adat_Font)));
            Megrendeltem_szoveg.setVerticalAlignment(Paragraph.ALIGN_CENTER);
            Megrendeltem_.addCell(Megrendeltem_szoveg);
            doc.add(Megrendeltem_);
            doc.add(new Paragraph(" "));



            //Image Megrendelo_kep_alairas = Image.getInstance(Megrendelo_alairasa_global);
            //Image Munkavegzo_kep_alairas = Image.getInstance(Munkavegzo_alairasa_global);
            PdfPTable Alairasok = new PdfPTable(2);
            Alairasok.setWidthPercentage(100);
            PdfPCell Megrendelo_alairas = new PdfPCell(megrendelo_alatiras_kep);
            Megrendelo_alairas.setUseVariableBorders(true);
            Megrendelo_alairas.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            Megrendelo_alairas.setBorderColorLeft(BaseColor.WHITE);
            Megrendelo_alairas.setBorderColorBottom(BaseColor.WHITE);
            Megrendelo_alairas.setFixedHeight(40);
            Megrendelo_alairas.setVerticalAlignment(Paragraph.ALIGN_BOTTOM);
            Megrendelo_alairas.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell Munkat_elvegzo_alairas = new PdfPCell(munkavegzo_alatiras_kep);
            Munkat_elvegzo_alairas.setUseVariableBorders(true);
            Munkat_elvegzo_alairas.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            Munkat_elvegzo_alairas.setBorderColorLeft(BaseColor.WHITE);
            Munkat_elvegzo_alairas.setBorderColorBottom(BaseColor.WHITE);
            Munkat_elvegzo_alairas.setFixedHeight(40);
            Munkat_elvegzo_alairas.setVerticalAlignment(Paragraph.ALIGN_BOTTOM);
            Munkat_elvegzo_alairas.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell Megrendelo_text_ = new PdfPCell(new Phrase(new Paragraph(".....................\n"+getText(R.string.megrendelo_alairasa),SajatFont)));
            Megrendelo_text_.setUseVariableBorders(true);
            Megrendelo_text_.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            Megrendelo_text_.setBorderColorLeft(BaseColor.WHITE);
            Megrendelo_text_.setBorderColorBottom(BaseColor.WHITE);

            Megrendelo_text_.setVerticalAlignment(Paragraph.ALIGN_TOP);
            Megrendelo_text_.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            PdfPCell Munkat_vegzo_text = new PdfPCell(new Phrase(new Paragraph(".....................\n"+getText(R.string.Munkavegzo_alairasa),SajatFont)));
            Munkat_vegzo_text.setUseVariableBorders(true);
            Munkat_vegzo_text.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            Munkat_vegzo_text.setBorderColorLeft(BaseColor.WHITE);
            Munkat_vegzo_text.setBorderColorBottom(BaseColor.WHITE);

            Munkat_vegzo_text.setVerticalAlignment(Paragraph.ALIGN_TOP);
            Munkat_vegzo_text.setHorizontalAlignment(Paragraph.ALIGN_CENTER);



            Alairasok.addCell(Megrendelo_alairas);
            Alairasok.addCell(Munkat_elvegzo_alairas);
            Alairasok.addCell(Megrendelo_text_);
            Alairasok.addCell(Munkat_vegzo_text);
            doc.add(Alairasok);


            PdfPTable NagyDatum =new PdfPTable(1);
            NagyDatum.setWidthPercentage(100);

            PdfPCell Datum = new PdfPCell(new Phrase(new Paragraph(getCurrentDate()+"\n ",SajatFont)));
            Datum.setUseVariableBorders(true);
            Datum.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            Datum.setBorderColorLeft(BaseColor.WHITE);
            Datum.setBorderColorBottom(BaseColor.BLACK);
            Datum.setVerticalAlignment(Paragraph.ALIGN_CENTER);
            Datum.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            doc.add(new Paragraph(" "));
            NagyDatum.addCell(Datum);
            doc.add(NagyDatum);



            //ALÁÍRÁS ÉS ELFOGADÁS CLOSE


            doc.close();


            StringBuilder nev_sb = new StringBuilder();
            StringBuilder cim_sb = new StringBuilder();
            StringBuilder email_sb = new StringBuilder();
            StringBuilder telefon_sb = new StringBuilder();
            StringBuilder serial_sb = new StringBuilder();
            StringBuilder egyeb_sz_nev_sb = new StringBuilder();
            StringBuilder egyeb_sz_serial_sb = new StringBuilder();


            Nev_history_.add(mTextNev.getText().toString());
            Cim_history_.add(mTextCim.getText().toString());
            Email_history_.add(mTextEmail.getText().toString());
            Telefon_history_.add(mTextPhone.getText().toString());
            Serial_history_.add(mTextSzolg_Serial.getText().toString());
            Egyeb_szolgaltatas_nev_history_.add(Egyeb_szolgaltatas_neve.getText().toString());
            Egyeb_szolgaltatas_serial_history_.add(Egyeb_szolgaltatas_serial.getText().toString());


            if(Nev_history_.contains(MainActivity._DEFAULT_))
                Nev_history_.remove(MainActivity._DEFAULT_);
            if(Cim_history_.contains(MainActivity._DEFAULT_))
                Cim_history_.remove(MainActivity._DEFAULT_);
            if(Email_history_.contains(MainActivity._DEFAULT_))
                Email_history_.remove(MainActivity._DEFAULT_);
            if(Telefon_history_.contains(MainActivity._DEFAULT_))
                Telefon_history_.remove(MainActivity._DEFAULT_);
            if(Serial_history_.contains(MainActivity._DEFAULT_))
                Serial_history_.remove(MainActivity._DEFAULT_);
            if(Egyeb_szolgaltatas_nev_history_.contains(MainActivity._DEFAULT_))
                Egyeb_szolgaltatas_nev_history_.remove(MainActivity._DEFAULT_);
            if(Egyeb_szolgaltatas_serial_history_.contains(MainActivity._DEFAULT_))
                Egyeb_szolgaltatas_serial_history_.remove(MainActivity._DEFAULT_);



            for (int i= 0;i<Nev_history_.size();i++)
            {
                nev_sb.append(Nev_history_.get(i)).append(",");
            }
            for (int i= 0;i<Cim_history_.size();i++)
            {
                cim_sb.append(Cim_history_.get(i)).append(",");
            }
            for (int i= 0;i<Email_history_.size();i++)
            {
                email_sb.append(Email_history_.get(i)).append(",");
            }
            for (int i= 0;i<Telefon_history_.size();i++)
            {
                email_sb.append(Telefon_history_.get(i)).append(",");
            }
            for (int i= 0;i<Serial_history_.size();i++)
            {
                serial_sb.append(Serial_history_.get(i)).append(",");
            }
            for (int i= 0;i<Egyeb_szolgaltatas_nev_history_.size();i++)
            {
                egyeb_sz_nev_sb.append(Egyeb_szolgaltatas_nev_history_.get(i)).append(",");
            }
            for (int i= 0;i<Egyeb_szolgaltatas_serial_history_.size();i++)
            {
                egyeb_sz_serial_sb.append(Egyeb_szolgaltatas_serial_history_.get(i)).append(",");
            }



            MainActivity.DATA_SAVE.edit().putString(MainActivity._NEV_HISTORY_,nev_sb.toString()).apply();
            MainActivity.DATA_SAVE.edit().putString(MainActivity._CIM_HISTORY_,cim_sb.toString()).apply();
            MainActivity.DATA_SAVE.edit().putString(MainActivity._EMAIL_HISTORY_,email_sb.toString()).apply();
            MainActivity.DATA_SAVE.edit().putString(MainActivity._TELEFON_HISTORY_,telefon_sb.toString()).apply();
            MainActivity.DATA_SAVE.edit().putString(MainActivity._SERIAL_HISTORY_,serial_sb.toString()).apply();
            MainActivity.DATA_SAVE.edit().putString(MainActivity._EGYEB_SZ_NEV_,egyeb_sz_nev_sb.toString()).apply();
            MainActivity.DATA_SAVE.edit().putString(MainActivity._EGYEB_SZ_SERIAL_,egyeb_sz_serial_sb.toString()).apply();


            Toast.makeText(this, mFileName + ".pdf "+getText(R.string.Mentes_sikerult), Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {

            Toast.makeText(this, getText(R.string.Mentes_hiba)+":  "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
    private void SavePDF_v2()
    {
        Document doc = new Document();
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mTextNev.getText() + ".pdf";


        try {
            PdfWriter.getInstance(doc, new FileOutputStream(mFilePath));
            doc.open();
            //HEADER

            Image image = Image.getInstance(MainActivity.Logo_path);

            doc.addAuthor("TechDoki Munkalap");


            Font Ceg_font= getFont(fontpath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 30);
            Paragraph _cegnev=new Paragraph(MainActivity.Ceges_adatok_nev_global,Ceg_font);
            _cegnev.setAlignment(Paragraph.ALIGN_LEFT);

            Paragraph munkalap_text=new Paragraph("Általános munkalap",Ceg_font);
            munkalap_text.setAlignment(Paragraph.ALIGN_LEFT);


             doc.close();
             Toast.makeText(this, mFileName + ".pdf Mentés sikerült.", Toast.LENGTH_SHORT).show();




        } catch (Exception e) {
            Toast.makeText(this, e.getMessage()+"asd", Toast.LENGTH_SHORT).show();
        }
        //new File(Munkavegzo_alairasa_global.toString()).delete();
        //new File(Megrendelo_alairasa_global.toString()).delete();


    }
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        TimeZone tz = TimeZone.getTimeZone("Pacific/Wake");

        return  dateFormat.format(Calendar.getInstance(tz).getTime());
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Jogosultság megtagadva", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void Munkat_vegezte_alairas(View v) {

        ALAIRAS_CHECKER = 0;
        Intent intent = new Intent(this, Signature.class);
        startActivity(intent);
    }

    public void Megrendelo_alairas(View v) {

        ALAIRAS_CHECKER = 1;
        Intent intent = new Intent(this, Signature.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Munkalap.this);

        builder.setMessage( getText(R.string.biztos_be_akarja_zarni)+"\n"+getText(R.string.nincs_mentesre_lehetoseg));

        builder.setCancelable(true);
        builder.setNegativeButton(getText(R.string.Megse), new DialogInterface.OnClickListener()
        {
            @Override
            public  void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton(getText(R.string.Bezar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
