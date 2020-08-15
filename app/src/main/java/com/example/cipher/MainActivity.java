package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText Name;
    private TextInputEditText password;
    private TextView info;
    private Button login;
    private Button register;
    private CheckBox remember;
    private int counter=5;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        info =findViewById(R.id.textView);
        login = findViewById(R.id.login);
        register=findViewById(R.id.register);
        remember=findViewById(R.id.checkBox);

         sharedPreferences=getApplicationContext().getSharedPreferences("Database",MODE_PRIVATE);
         editor=sharedPreferences.edit();
        if(sharedPreferences!=null){
           String savedusername= sharedPreferences.getString("Username","Mayank");
           String savedpassword= sharedPreferences.getString("Password","12345");
           RegistrationActivity.credentials=new Credentials(savedusername,savedpassword);
           if(sharedPreferences.getBoolean("RememberCheck",false)){
               Name.setText(savedusername);
               password.setText(savedpassword);
               remember.setChecked(true);
           }
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("RememberCheck",remember.isChecked()); //remember checkbox is checked or not
                editor.apply();
            }
        });
    }

    public void validate(View v){
        String inputName=Name.getText().toString();
        String inputPassword=password.getText().toString();

       if(checkCredentials(inputName,inputPassword)) {
           Intent intent = new Intent(MainActivity.this, CipherActivity.class);
           startActivity(intent);
           Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        }
       else {
            counter--;
            Toast.makeText(this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
            info.setText("Remaining attempts: " + counter);
        }
        if(counter==0)
            login.setEnabled(false);

    }
    private boolean checkCredentials(String Name,String password) {
        if(RegistrationActivity.credentials!=null) {
            if (Name.equals(RegistrationActivity.credentials.getUserName()) && password.equals(RegistrationActivity.credentials.getUserPassword())) {
                return true;
            }
        }
        return false;

    }




}