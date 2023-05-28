package com.example.smartaquarium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartaquarium.databinding.ActivityPictureBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PICTURE extends AppCompatActivity {
  ActivityPictureBinding binding;
  DatabaseReference reference;
    private android.widget.TextView VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityPictureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        VIEW = findViewById(R.id.textView7);
        String num5 = getIntent().getStringExtra("mn");
        VIEW.setText(num5);
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=binding.textView7.getText().toString();
                if(!username.isEmpty()){
                    readData(username);
                }else{
                    Toast.makeText(PICTURE.this, "enter user name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //setContentView(R.layout.activity_main);
    }
    private void readData(String username) {
        reference= FirebaseDatabase.getInstance().getReference("users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    if(task.getResult().exists()){

                        Toast.makeText(PICTURE.this, "SUCCESSFULLY READ", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot=task.getResult();
                        String ph=String .valueOf(dataSnapshot.child("ph").getValue());
                        String temperature=String .valueOf(dataSnapshot.child("temperature").getValue());
                        String tds=String .valueOf(dataSnapshot.child("tds").getValue());
                        String ultrasonic=String .valueOf(dataSnapshot.child("ultrasonic").getValue());
                        binding.textView9.setText(ph);
                        binding.textView11.setText(tds);
                        binding.textView13.setText(ultrasonic);
                        binding.textView16.setText(temperature);
                    }else{
                        Toast.makeText(PICTURE.this, "USER DOES NOT EXIST", Toast.LENGTH_SHORT).show();


                    }
                }
                else{
                    Toast.makeText(PICTURE.this, "FAILED TO READ DATA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}