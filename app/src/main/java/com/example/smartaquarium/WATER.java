package com.example.smartaquarium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartaquarium.databinding.ActivityWaterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WATER extends AppCompatActivity {
    ActivityWaterBinding binding;
    DatabaseReference databaseReference;
    String num = String.valueOf(1);
    private android.widget.TextView VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        binding = ActivityWaterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        VIEW = findViewById(R.id.textView4);
        String num5 = getIntent().getStringExtra("pn");
        VIEW.setText(num5);
        binding.button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.textView4.getText().toString();
                String feed = String.valueOf(1);
                if (!username.isEmpty()) {
                    updateData(username, feed);
                } else {
                    Toast.makeText(WATER.this, "error in feeding", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void updateData(String username, String feed) {
        HashMap User = new HashMap();
        User.put("water_OUT", feed);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(username).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {
                    // binding.userName.setText("");
                    //binding.editTextTextPersonName2.setText("");
                    //binding.editTextTextPersonName3.setText("");
                    //binding.editTextTextPersonName4.setText("");
                    Toast.makeText(WATER.this, "WATER OUT ACTIVATED", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(WATER.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}