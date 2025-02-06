package com.example.databaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    ImageButton btnInsert, btnShow;
    TextView insert,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      btnInsert = findViewById(R.id.insertBtn);
      btnShow = findViewById(R.id.ShowBtn);
      insert = findViewById(R.id.tvInsert);
      show = findViewById(R.id.tvShow);
      Intent intent1 = new Intent(HomeActivity.this, InputForm.class);
      btnInsert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(intent1);
          }
      });
      insert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(intent1);
          }
      });
      Intent intent2 = new Intent(HomeActivity.this, MainActivity.class);
      btnShow.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(intent2);
          }
      });
      show.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(intent2);
          }
      });
    }
}