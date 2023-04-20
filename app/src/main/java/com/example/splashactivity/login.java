package com.example.splashactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText user,pass;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    CheckBox savelogincheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.txtuser);
        pass=findViewById(R.id.txtpassword);
        login=findViewById((R.id.button));

        login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                sharedPreferences=getSharedPreferences("loginref",MODE_PRIVATE);
                savelogincheckbox=findViewById(R.id.checkBox);
                editor=sharedPreferences.edit();
                String passwordInput = pass.getText().toString().trim();


                if(passwordInput.isEmpty()){
                    pass.setError("Password field can't be empty");
                }else if(passwordInput.length()<5){
                    pass.setError("Password should be more than 5 characters");
                }else{
                    pass.setError(null);
                    editor.putString("password",pass.getText().toString());
                    editor.putBoolean("REGISTERED",true);
                    editor.apply();
                    Intent intent = new Intent(login.this,Register.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

}