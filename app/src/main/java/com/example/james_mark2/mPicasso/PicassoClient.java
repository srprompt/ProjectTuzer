package com.example.james_mark2.mPicasso;

import android.content.Context;
import android.widget.ImageButton;

import com.example.james_mark2.R;
import com.squareup.picasso.Picasso;
public class PicassoClient {
    public static void downloadImage(Context c, String url, ImageButton img){
        //carrega imagem url, coloca placeholder caso não ache
        if(url != null && url.length()>0){
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
}
