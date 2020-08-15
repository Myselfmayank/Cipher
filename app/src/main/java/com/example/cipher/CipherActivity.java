package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CipherActivity extends AppCompatActivity {
    private EditText text;
    private EditText text1;

    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ @1234567890";
    String key = "347651820HQDYVTKFUOMPCIASRxznlwebgjhqdyvtkfuompciasr@ 9XZNLWEBGJ";
    String s1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);
        text = findViewById(R.id.txt1);
        text1 = findViewById(R.id.txt2);

    }



    public void Encrypt(View v) {

        for (char c : text.getText().toString().toCharArray()) {
            int index = alphabet.indexOf(String.valueOf(c));
            if (alphabet.indexOf(String.valueOf(c)) != (-1))
                s1 += key.charAt(index);
            else
                s1 += c;
        }

        text1.setText(s1);
        s1 = "";
    }
        public void Decrypt (View v){

            for (char c : text.getText().toString().toCharArray()) {
                int index = key.indexOf(String.valueOf(c));
                if (key.indexOf(String.valueOf(c)) != (-1))
                    s1 += alphabet.charAt(index);
                else
                    s1 += c;
            }
            text1.setText(s1);
            s1 = "";
        }
        //method to send context on whatsapp
        public void send(View v)  {
        String decrypted_code=text1.getText().toString();
        boolean installed= appInstallCheck("com.whatsapp");
        if(installed){
            Intent i= new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://api.whatsapp.com/send?text="+decrypted_code));
            startActivity(i);
        }else {
            Toast.makeText(this,"WhatsApp not installed",Toast.LENGTH_SHORT).show();
        }

        }
        //method to check whatsApp is installed or not
        private boolean appInstallCheck(String url){
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,packageManager.GET_ACTIVITIES);
            app_installed= true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed=false;
        }
        return app_installed;
        }

}