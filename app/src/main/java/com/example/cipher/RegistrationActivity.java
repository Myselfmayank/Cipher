package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RegistrationActivity extends AppCompatActivity {
    private TextInputLayout eregName;
    private TextInputLayout eregPassword;
    public static Credentials credentials;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        eregName=findViewById(R.id.regusername);
        eregPassword=findViewById(R.id.regpassword);
        sharedPreferences=getApplicationContext().getSharedPreferences("Database",MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void register(View view){
        String regUsername=eregName.getEditText().getText().toString();
        String regUserpassword=eregPassword.getEditText().getText().toString();

        if(validate(regUsername,regUserpassword)){
           credentials=new Credentials(regUsername,regUserpassword);
           startActivity(new Intent(this,MainActivity.class));
            Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show();

            editor.putString("Username",regUsername); //stores credentials in local database
            editor.putString("Password",regUserpassword); //stores credentials in local database
            editor.apply(); //commits changes and save them to the file
        }
    }
    private boolean validate(String name,String password) {
        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,"Please enter valid input",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}