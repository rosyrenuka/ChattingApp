package com.example.hp.chattingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

public class Success extends AppCompatActivity {

    EditText userName;
    EditText Year;
    RadioButton male;
    RadioButton female;
    TextView phoneNumber;
    Button loginApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        userName = findViewById(R.id.username);
        Year = findViewById(R.id.year);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        phoneNumber = findViewById(R.id.phone_number);
        loginApp = findViewById(R.id.go);

        TakeInput();

    }

    private void TakeInput() {

        //username, year,gender,phonenumber;

        String username= userName.getText().toString();
        if(username.length()<=2){
            Toast.makeText(Success.this,"UserName must be minimum of 3 characters",Toast.LENGTH_SHORT).show();
            userName.setText(null);
        }

        String y= String.valueOf(Year.getText());
        int year=Integer.parseInt(y);

        if(year<1960 || year>2010) {
            Toast.makeText(this, "invalid age", Toast.LENGTH_SHORT).show();
            Year.setText(0);
        }

        year=2018-year;

        String gender="";
      if(male.isSelected()){
           gender="male";
      }
      if(female.isSelected()){
          gender="female";
      }

      if(!male.isSelected()&& !female.isSelected() ){
          Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show();
      }


        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("username",username);
        editor.putInt("age",year);
        editor.putString("gender",gender);
        editor.putString("contact",phoneNumber.getText().toString());
        editor.apply();



        loginApp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

//              takeInput();

              Intent phoneIntent = new Intent(Success.this,MainActivity.class);
              startActivity(phoneIntent);

          }
      });





    }


    @Override
    protected void onResume() {
        super.onResume();
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {

                phoneNumber.setText(String.format("User phone %s",account.getPhoneNumber()==null ? "":account.getPhoneNumber().toString()));
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }
    }

