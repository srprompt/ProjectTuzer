package com.example.james_mark2.mRecycler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.james_mark2.R;

public class MyHolder  extends RecyclerView.ViewHolder{
    TextView nomeTxt;
    ImageButton img;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        //localiza componentes pelo viewholder
        nomeTxt = itemView.findViewById(R.id.nomeTxt);
        img = itemView.findViewById(R.id.movieImage);
    }
}
