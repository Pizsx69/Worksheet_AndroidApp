package com.example.munkalaptechdoki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;



import android.os.Bundle;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;



public class Signature extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button Alairas_elfogadva;
    TextView ASZF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           verifyStoragePermissions(this);
           setContentView(R.layout.alairas);


           ASZF = findViewById(R.id.signature_pad_description);
           ASZF.setText(MainActivity.Keretes_megjegyzes_global);

            mSignaturePad = (SignaturePad)findViewById(R.id.signature_pad);
            mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
                @Override
                public void onStartSigning() {

                }
//
                @Override
                public void onSigned() {

                    Alairas_elfogadva.setEnabled(true);
                    mClearButton.setEnabled(true);
                }
//
                @Override
                public void onClear() {

                    mClearButton.setEnabled(false);
                }
            });
//
            mClearButton =  findViewById(R.id.clear_button);
            Alairas_elfogadva =  findViewById(R.id.Alairas_elfogadva);

//
           mClearButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mSignaturePad.clear();
               }
           });
//

        Alairas_elfogadva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                if (addJpgSignatureToGallery2(signatureBitmap)) {
                    if(Munkalap.ALAIRAS_CHECKER==0)
                    {
                        Toast.makeText(Signature.this, getText(R.string.Munkavegzo_alairas_save), Toast.LENGTH_SHORT).show();
                    }
                    if (Munkalap.ALAIRAS_CHECKER==1)
                    {
                        Toast.makeText(Signature.this, getText(R.string.Megrendelo_alairasa_mentve), Toast.LENGTH_SHORT).show();
                    }

                    mSignaturePad.clear();
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Signature.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException, BadElementException {
        Bitmap newBitmap;
        newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);

        stream.close();


    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            //File photo = new File(getAlbumStorageDir("Megrendelo_"), String.format("Megrendelo_%d.jpg", System.currentTimeMillis()));
            //saveBitmapToJPG(signature, photo);
            //scanMediaFile(photo);

            //Munkalap.Megrendelo_alairasa_global = photo.toString();

            ByteArrayOutputStream Stream = new ByteArrayOutputStream();
            signature.compress(Bitmap.CompressFormat.PNG,100,Stream);
            Munkalap.megrendelo_alatiras_kep = null;
            byte[] bytearray = Stream.toByteArray();
            Munkalap.megrendelo_alatiras_kep = Image.getInstance(bytearray);



            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean addJpgSignatureToGallery2(Bitmap signature) {
        boolean result = false;
        try {
            //File photo = new File(getAlbumStorageDir("Munkavegzo"), String.format("Munkavegzo%d.jpg", System.currentTimeMillis()));
            //saveBitmapToJPG(signature, photo);
            //scanMediaFile(photo);
            //Munkalap.Munkavegzo_alairasa_global = photo.toString();
            if(Munkalap.ALAIRAS_CHECKER == 0)
            {
                ByteArrayOutputStream Stream = new ByteArrayOutputStream();
                signature.compress(Bitmap.CompressFormat.PNG,100,Stream);
                Munkalap.munkavegzo_alatiras_kep = null;
                byte[] bytearray = Stream.toByteArray();
                Munkalap.munkavegzo_alatiras_kep = Image.getInstance(bytearray);

            }
            if(Munkalap.ALAIRAS_CHECKER == 1)
            {
                ByteArrayOutputStream Stream = new ByteArrayOutputStream();
                signature.compress(Bitmap.CompressFormat.PNG,100,Stream);
                Munkalap.megrendelo_alatiras_kep = null;
                byte[] bytearray = Stream.toByteArray();
                Munkalap.megrendelo_alatiras_kep = Image.getInstance(bytearray);

            }


            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        Signature.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addSvgSignatureToGallery2(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
