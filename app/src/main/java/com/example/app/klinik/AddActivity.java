package com.example.app.klinik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nama_input, gender_input, umur_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nama_input = findViewById(R.id.nama_input);
        gender_input = findViewById(R.id.gender_input);
        umur_input = findViewById(R.id.umur_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBmanager myDB = new DBmanager(AddActivity.this);
                myDB.addAntrean(nama_input.getText().toString().trim(),
                        gender_input.getText().toString().trim(),
                        umur_input.getText().toString().trim());
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
