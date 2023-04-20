package com.example.splashactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class listData extends AppCompatActivity {

    Database DB;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        DB=new Database(this);
        showData(view);
    }

    private void showData(View view) {
        Cursor WL= DB.gettext();
        if (WL.getCount()==0){
            Toast.makeText(listData.this, "User data not exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (WL.moveToNext()){
            buffer.append("Name: "+WL.getString(0)+"\n");
            buffer.append("Age: "+WL.getString(1)+"\n\n");
            buffer.append("Gender: "+WL.getString(2)+"\n\n\n");
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(listData.this);
        builder.setCancelable(true);
        builder.setTitle("User Data");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}