package com.example.james_mark2.mPicasso;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.james_mark2.R;
import com.squareup.picasso.Picasso;
public class PicassoClient {
    public static void downloadImage(Context c, String url, ImageButton img){
        //carrega imagem url, coloca placeholder caso não ache para ImageButton
        if(url != null && url.length()>0){
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).fit().centerInside().into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
    public static void downloadImagePerfil(Context c, String url, ImageView img){
        //carrega imagem url, coloca placeholder caso não ache para ImageView
        if(url != null && url.length()>0){
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).fit().centerInside().into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).fit().centerInside().into(img);
        }
    }
}
