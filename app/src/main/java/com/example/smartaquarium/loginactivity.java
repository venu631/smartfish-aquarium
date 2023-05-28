package com.example.smartaquarium;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginactivity extends AppCompatActivity {
    private android.widget.Button login;
    private android.widget.Button signup;
    private android.widget.EditText email;
    private android.widget.EditText password;
    private android.widget.EditText user;
    private FirebaseAuth firebaseAuth;
    private android.widget.Button resendlink;
    private android.widget.TextView view;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://smartfishaquariumiot-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        firebaseAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.button);
        signup = findViewById(R.id.button7);
        email = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPersonName);
        resendlink = findViewById(R.id.button3);
        user=findViewById(R.id.editTextTextPersonName5);








        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginactivity.this, username.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usertxt=user.getText().toString();
                final String passwordtxt=password.getText().toString();







                String strEmail = email.getText().toString();
                String strPassword = password.getText().toString();

                if (!TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strPassword)) {
                    firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.hasChild(usertxt)){
                                                final String getpassword= snapshot.child(usertxt).child("password").getValue(String.class);

                                                if(getpassword.equals(passwordtxt)){
                                                    Toast.makeText(loginactivity.this, "LOGED IN", Toast.LENGTH_SHORT).show();
                                                    Intent i9 = new Intent(loginactivity.this, first.class);
                                                    i9.putExtra("value",usertxt);
                                                    i9.putExtra("value1",usertxt);
                                                    //i9.putExtra("value2",usertxt);
                                                    startActivity(i9);
                                                    finish();









                                                }
                                                else{
                                                    Toast.makeText(loginactivity.this, "The username and password not match", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                            else{
                                                Toast.makeText(loginactivity.this, "xxxxThe username and password not match", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                } else {
                                    resendlink.setVisibility(View.VISIBLE);
                                    resendlink.setClickable(true);
                                    Toast.makeText(loginactivity.this, "Please Verify Your Email and Continue", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(loginactivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    Toast.makeText(loginactivity.this, "Email and Password can not be empty ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        resendlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(loginactivity.this, "link has sent to your Email ,Please verify and Continue", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

}

