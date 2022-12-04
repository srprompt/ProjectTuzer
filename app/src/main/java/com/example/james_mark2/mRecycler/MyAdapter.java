package com.example.james_mark2.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.james_mark2.BD.DBAdapter;
import com.example.james_mark2.EventoActivity;
import com.example.james_mark2.R;
import com.example.james_mark2.mData.Favorito;
import com.example.james_mark2.mData.Passeios;
import com.example.james_mark2.mData.Usuario;
import com.example.james_mark2.mPicasso.PicassoClient;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Passeios> passeios;
    Usuario usuario;
    Favorito favorito;
    ArrayList<Favorito> favoritos= new ArrayList<>();

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

        //Imagem
        PicassoClient.downloadImage(c,passeios.get(position).getUrl(),holder.img);

        //Click na imagem
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventoActivity.class);
                intent.putExtra("Image id", String.valueOf(passeios.get(holder.getLayoutPosition()).getId()));
                intent.putExtra("Image url",String.valueOf(passeios.get(holder.getLayoutPosition()).getUrl()));
                intent.putExtra("Descricao",String.valueOf(passeios.get(holder.getLayoutPosition()).getDescricao()));
                view.getContext().startActivity(intent);
            }
        });

        //btn favorito click
        holder.btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DBAdapter db = new DBAdapter(view.getContext());
                    db.openDB();
                    db.addFavorito(passeios.get(holder.getLayoutPosition()).getId(),0);
                    db.closeDB();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    //Recupera total de eventos
    @Override
    public int getItemCount() {
        return passeios.size();
    }
}
