package com.example.splashactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Homepage extends AppCompatActivity {

    Button change_colour,add_data,list_data;
    int defaultcolour;
    RelativeLayout mainlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        change_colour=findViewById(R.id.button3);
        add_data=findViewById(R.id.button4);
        list_data=findViewById(R.id.button5);
        mainlayout=findViewById(R.id.main_layout);

        defaultcolour=ContextCompat.getColor(Homepage.this,R.color.buttonColor);

        change_colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { open_color_picker();}
        });

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Homepage.this,addData.class);
                startActivity(intent);
                finish();

            }
        });
        /*  list_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Homepage.this, listData.class);
                startActivity(intent3);
                finish();
            }
        });*/

    }

    public void open_color_picker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultcolour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {

                defaultcolour = color;
                mainlayout.setBackgroundColor(defaultcolour);
            }
        });
        ambilWarnaDialog.show();
    }
}