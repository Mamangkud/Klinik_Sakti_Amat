package com.example.app.klinik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, nama, gender, umur;
    RecycleViewAdapter(Activity activity, Context context, ArrayList id, ArrayList nama, ArrayList gender, ArrayList umur){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.nama = nama;
        this.gender = gender;
        this.umur = umur;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.nama_txt.setText(String.valueOf(nama.get(position)));
        holder.gender_txt.setText(String.valueOf(gender.get(position)));
        holder.umur_txt.setText(String.valueOf(umur.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("nama_pasien", String.valueOf(nama.get(position)));
                intent.putExtra("jenis_kelamin", String.valueOf(gender.get(position)));
                intent.putExtra("umur", String.valueOf(umur.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, nama_txt, gender_txt, umur_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            nama_txt = itemView.findViewById(R.id.nama_txt);
            gender_txt = itemView.findViewById(R.id.gender_txt);
            umur_txt = itemView.findViewById(R.id.umur_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Mendambahkan animasi pada recycleview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
