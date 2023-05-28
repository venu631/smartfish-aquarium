package com.example.smartaquarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class first extends AppCompatActivity {
    private android.widget.Button FEED;
    private android.widget.Button MONITER;
    private android.widget.Button WATER;
    private android.widget.Button PICTURE;
    private android.widget.TextView view;
    //public static final String username="num";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        FEED=findViewById(R.id.button);
        MONITER=findViewById(R.id.button7);
        WATER=findViewById(R.id.WATER);
        PICTURE=findViewById(R.id.picture);
        view=findViewById(R.id.textView3);
        String num = getIntent().getStringExtra("value");

        //String name=num;
        String num1 = getIntent().getStringExtra("value1");
       // String num2 = getIntent().getStringExtra("value2");
        view.setText(num);
       // final String username=view.getText().toString();
        FEED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i9 = new Intent(first.this, FEED.class);
                i9.putExtra("fn",num);
                startActivity(i9);
               // finish();
            }
        });
        MONITER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(first.this, PICTURE.class);
                i2.putExtra("mn",num1);
                startActivity(i2);
               // finish();

            }
        });
        WATER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(first.this, WATER.class);
                i3.putExtra("pn",num1);
                startActivity(i3);
                //finish();

            }
        });
        PICTURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(first.this, MONITER.class);
                i6.putExtra("pn",num);
                startActivity(i6);
                //finish();

            }
        });
    }
}