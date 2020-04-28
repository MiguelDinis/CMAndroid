package com.example.cm_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SetFav extends AppCompatActivity {

    TextView labelFaf;
    TextView numberFav;
    SharedPreferences sharedPref;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_fav);
        labelFaf = findViewById(R.id.labelFav);
        numberFav = findViewById(R.id.phoneFav);
        sharedPref = getSharedPreferences("MyData", MODE_PRIVATE);
        id =  sharedPref.getInt("nId",0);
    }

    public void updateFav(View view){
        SharedPreferences.Editor editor = sharedPref.edit();
        String idS = String.valueOf(id);
        editor.putString("fav"+idS,labelFaf.getText().toString());
        editor.putString("n"+idS,numberFav.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
