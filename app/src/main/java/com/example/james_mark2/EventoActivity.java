package com.example.james_mark2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.james_mark2.mPicasso.PicassoClient;
import com.squareup.picasso.Picasso;

public class EventoActivity extends AppCompatActivity {

    ImageButton imageButtonEvento;
    TextView txtEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        //Instancia componentes dos detalhes do evento
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationIcon(R.drawable.back_arrow);
        imageButtonEvento = findViewById(R.id.imageButtonEvento);
        txtEvento = findViewById(R.id.textViewEvento);
        Intent intent = getIntent();

        //Carrega conteudo do evento
        if(intent!=null){
            String id = getIntent().getStringExtra("Image id");
            String url = getIntent().getStringExtra("Image url");
            String descricao = getIntent().getStringExtra("Descricao");
            try {
                PicassoClient.downloadImage(getBaseContext(),url,imageButtonEvento);
                if(descricao.equals("null")) {
                    txtEvento.setText("");
                }else txtEvento.setText(descricao);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}