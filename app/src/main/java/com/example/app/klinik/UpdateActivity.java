package com.example.app.klinik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nama_input, gender_input, umur_input;
    Button update_button, delete_button;

    String id, nama, gender, umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nama_input = findViewById(R.id.nama_input2);
        gender_input = findViewById(R.id.gender_input2);
        umur_input = findViewById(R.id.umur_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // Pertama akan memanggil fungsi ini untuk mendapatkan value intent sebelumnya
        getAndSetDataUpdate();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Untuk mengupdate data yang telah di edit
                DBmanager myDB = new DBmanager(UpdateActivity.this);
                nama = nama_input.getText().toString().trim();
                gender = gender_input.getText().toString().trim();
                umur = umur_input.getText().toString().trim();
                myDB.updateData(id, nama, gender, umur);

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }

        });


    }
// Mengeset data yang data yang akan di update
    void getAndSetDataUpdate(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nama_pasien") &&
                getIntent().hasExtra("jenis_kelamin") &&
                        getIntent().hasExtra("umur")){
            // Mendapatkan nilai value pada put extra
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama_pasien");
            gender = getIntent().getStringExtra("jenis_kelamin");
            umur = getIntent().getStringExtra("umur");

            // Mengubah text nama dan gender
            nama_input.setText(nama);
            gender_input.setText(gender);
            umur_input.setText(umur);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    // Popup sebagai validasi terakhir untuk user
    void validation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + nama + " ?");
        builder.setMessage("Apakah anda yakin menghapus ini?" + nama + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBmanager myDB = new DBmanager(UpdateActivity.this);
                myDB.deleteData(id);
                finish();

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
