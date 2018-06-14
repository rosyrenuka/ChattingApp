package com.example.hp.chattingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Contact_Detail extends AppCompatActivity {

    ImageView contact_profile;
    TextView contact_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__detail);
        contact_profile=findViewById(R.id.profile_image1);
        contact_name=findViewById(R.id.profile_name);


//
//          Intent intent=getIntent();
//          int ans1=intent.getIntExtra("im",0);
//          contact_profile.setImageResource(ans1);


         //contact_profile.setImageResource(sharedIntent.getIntExtra(R.id.contact_image,0));

    }
}
