package com.example.splashactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    Integer count=0;
    EditText userpw;
    Button login;
    String password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        login=findViewById(R.id.button2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userpw=findViewById(R.id.editTextTextPersonName);
                sp=getSharedPreferences("loginref", MODE_PRIVATE);
                password=sp.getString("password","");
                if(password.equals(userpw.getText().toString())){
                    Intent intent= new Intent(Register.this,Homepage.class);
                    startActivity(intent);
                    finish();

                }else{
                    count++;
                    Toast.makeText(Register.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    if (count >=3){
                        Register.this.finish();
                        System.exit(0);
                    }
                }


            }
        });

    }
}