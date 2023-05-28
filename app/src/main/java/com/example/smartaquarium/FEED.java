package com.example.smartaquarium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartaquarium.databinding.ActivityFeedBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FEED extends AppCompatActivity {
    private android.widget.TextView VIEW;
    private android.widget.Button feed;
    ActivityFeedBinding binding;
    DatabaseReference databaseReference;
    String num = String.valueOf(1);
    private android.widget.Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        feed = findViewById(R.id.button2);
        VIEW = findViewById(R.id.textView2);
        back=findViewById(R.id.button8);
        String num5 = getIntent().getStringExtra("fn");
        VIEW.setText(num5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FEED.this,loginactivity.class);
                startActivity(intent);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.textView2.getText().toString();
                String feed = String.valueOf(1);
                if (!username.isEmpty()) {
                    updateData(username, feed);
                } else {
                    Toast.makeText(FEED.this, "error in feeding", Toast.LENGTH_SHORT).show();
                }


            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.textView2.getText().toString();
                String feed = String.valueOf(1);
                if (!username.isEmpty()) {
                    updateData1(username, feed);
                } else {
                    Toast.makeText(FEED.this, "error in cancelfeeding", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void updateData1(String userName, String feed) {
        HashMap User = new HashMap();
        User.put("c_FEED", feed);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {
                    // binding.userName.setText("");
                    //binding.editTextTextPersonName2.setText("");
                    //binding.editTextTextPersonName3.setText("");
                    //binding.editTextTextPersonName4.setText("");
                    Toast.makeText(FEED.this, "FEED CANCELLED", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(FEED.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void updateData(String userName, String FEED) {
        HashMap User = new HashMap();
        User.put("feed", FEED);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {
                    // binding.userName.setText("");
                    //binding.editTextTextPersonName2.setText("");
                    //binding.editTextTextPersonName3.setText("");
                    //binding.editTextTextPersonName4.setText("");
                    Toast.makeText(FEED.this, "FEEDED", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(FEED.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}