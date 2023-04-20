package com.example.splashactivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addData extends AppCompatActivity {

    private static final String TAG = "addData";
    private static final String name2="Name";
    private static final String age2="Age";
    private static final String gender1="Gender";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    ImageButton imgbtn;
    EditText name,age;
    ImageView image;
    Button savebtn,viewbtn;
    RadioButton male,female;
    Database DB;
    String gender;
    //byte[] bytar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        imgbtn=findViewById(R.id.imageButton);
        name=findViewById(R.id.editTextTextPersonName2);
        age=findViewById(R.id.editTextNumberPassword);
        image=findViewById(R.id.imageView);
        savebtn=findViewById(R.id.button6);
        viewbtn=findViewById(R.id.button7);
        male=findViewById(R.id.radioButton2);
        female=findViewById(R.id.radioButton3);
        DB=new Database(this);

        image.setImageURI(null);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(addData.this)

                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(10);

                //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent,100);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1, age1;
               // Drawable image1;

                name1 = name.getText().toString().trim();
                age1 = age.getText().toString().trim();
                if(male.isChecked()){
                    gender="male";
                }
                else {
                    gender="female";
                }

                Boolean savedata=DB.saveuserdata(name1,age1,gender);

                if (TextUtils.isEmpty(name1)) {
                    Toast.makeText(addData.this, "Enter Name", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(age1)) {
                    Toast.makeText(addData.this, "Enter Age", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if(savedata==true){
                        Toast.makeText(addData.this, "Save user data", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(addData.this, "Exists User Data", Toast.LENGTH_SHORT).show();
                    }

                }

                Map<String,Object> note =new HashMap<>();
                note.put(name2,name1);
                note.put(age2,age1);
                note.put(gender1,gender);

                db.collection("Census App").document("Data List").set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(addData.this, "NoteSaved", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addData.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG,e.toString());

                            }
                        });
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor WL= DB.gettext();
                if (WL.getCount()==0){
                    Toast.makeText(addData.this, "User data not exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (WL.moveToNext()){
                    buffer.append("Name: "+WL.getString(0)+"\n");
                    buffer.append("Age: "+WL.getString(1)+"\n\n");
                    buffer.append("Gender: "+WL.getString(2)+"\n\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(addData.this);
                builder.setCancelable(true);
                builder.setTitle("User Data");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            Uri uri = data.getData();
            image.setImageURI(uri);
            /*if (requestCode == 100) {
                Bitmap bitmapImage = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmapImage);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytar=stream.toByteArray();
            }*/


        }
    }



























































