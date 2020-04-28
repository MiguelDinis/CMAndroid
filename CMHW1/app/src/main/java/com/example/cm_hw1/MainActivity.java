package com.example.cm_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    String phoneNumber;
    String n1, n2, n3;
    TextView phoneNumberView;
    TextView fav1, fav2, fav3;
    ImageButton favButton1, favButton2, favButton3;
    Intent intent;
    SharedPreferences sharedPref;
    String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumberView = findViewById(R.id.numberDisplay);
        phoneNumber = phoneNumberView.getText().toString();
        favButton1 = (ImageButton)findViewById(R.id.fav1);
        favButton2 = (ImageButton)findViewById(R.id.fav2);
        favButton3 = (ImageButton)findViewById(R.id.fav3);
        sharedPref = getSharedPreferences("MyData", MODE_PRIVATE);
        fav1 = findViewById(R.id.favText);
        fav2 = findViewById(R.id.favText1);
        fav3 = findViewById(R.id.favText2);
        fav1.setText(sharedPref.getString("fav1",DEFAULT));
        fav2.setText(sharedPref.getString("fav2",DEFAULT));
        fav3.setText(sharedPref.getString("fav3",DEFAULT));
        n1 = sharedPref.getString("n1",DEFAULT);
        n2 = sharedPref.getString("n2",DEFAULT);
        n3 = sharedPref.getString("n3",DEFAULT);

        intent = new Intent(this, SetFav.class);
        favButton1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setFav(1);
                return true;
            }
        });
        favButton2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setFav(2);
                return true;
            }
        });
        favButton3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setFav(3);
                return true;
            }
        });


    }

    public void clickFav1(View view){
        dialPhoneNumber(n1);
    }

    public void clickFav2(View view){
        dialPhoneNumber(n2);
    }

    public void clickFav3(View view){
        dialPhoneNumber(n3);
    }

    public void setFav(int id){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("nId",id);
        editor.commit();
        startActivity(intent);

    }

    public void call(View view){
        dialPhoneNumber(phoneNumber);
    }

    public void delNumber(View view){
        phoneNumber = "";
        phoneNumberView.setText(phoneNumber);
    }

    private void setPhoneNumber(String number){
        phoneNumber = phoneNumber + number;
        phoneNumberView.setText(phoneNumber);
    }

    public void numberClick(View view){
        Button b = (Button)view;
        String n = b.getText().toString();
        setPhoneNumber(n);
    }



    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
