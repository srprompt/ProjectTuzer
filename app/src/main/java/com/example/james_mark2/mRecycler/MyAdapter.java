package com.example.james_mark2.mRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.james_mark2.R;
import com.example.james_mark2.mData.Passeios;
import com.example.james_mark2.mPicasso.PicassoClient;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Passeios> passeios;

    public MyAdapter(Context c, ArrayList<Passeios> passeios) {
        this.c = c;
        this.passeios = passeios;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //atribui layout do recyclerview
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //Bind data
        holder.nomeTxt.setText(passeios.get(position).getNome());

        //Image
        PicassoClient.downloadImage(c,passeios.get(position).getUrl(),holder.img);
    }

    @Override
    public int getItemCount() {
        return passeios.size();
    }
}
