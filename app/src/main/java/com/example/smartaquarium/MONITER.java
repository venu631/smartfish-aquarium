package com.example.smartaquarium;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartaquarium.databinding.ActivityMoniterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MONITER extends AppCompatActivity {
    ActivityMoniterBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    private android.widget.TextView VIEW;
    private android.widget.Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMoniterBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_moniter);
        setContentView(binding.getRoot());
        VIEW = findViewById(R.id.textView5);
        back=findViewById(R.id.button10);
        String num5 = getIntent().getStringExtra("pn");
        VIEW.setText(num5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MONITER.this,first.class);
                startActivity(intent);

            }
        });

        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog =new ProgressDialog(MONITER.this);
                progressDialog.setMessage("Fetching image ....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String imageID=binding.textView5.getText().toString();
                storageReference= FirebaseStorage.getInstance().getReference("images/"+imageID+".jpg");

                try {
                    File localfile= File.createTempFile("tempfile",".jpg");
                    storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            binding.imageView.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                            Toast.makeText(MONITER.this, "Failed to retrive", Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}