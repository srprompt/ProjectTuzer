package com.example.james_mark2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.james_mark2.mPicasso.PicassoClient;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    int cont=0;
    boolean clisque = false;
    //id, cidade, nome_evento, categoria, imagem
    String evento [][] = {
            {"1", "Piracicaba", "Curso Boviplan de Gestão Agropecuária", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048941382003327016/BOI.jpg"},
            {"2", "Piracicaba", "Porto Certo", "Gastronomia", "https://cdn.discordapp.com/attachments/1008789338412306452/1048944268468822066/image.png"},
            {"3", "Piracicaba", "Alto do Mirante", "Turismo", "https://cdn.discordapp.com/attachments/1008789338412306452/1048947822956912640/image.png"},
            {"4", "Piracicaba", "Zoológico", "Turismo", "https://media.discordapp.net/attachments/1008789338412306452/1048949043461627984/image.png"},
            {"5", "Piracicaba", "Várzea Cup", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048950210627387392/146672963_3442767612495847_6830975024017549386_o-1024x799_1.jpg"},
            {"6", "Piracicaba", "TAMO AQUI", "TAMO AQUI","https://cdn.discordapp.com/attachments/1008789338412306452/1048953244115804251/Ronaldinho-Gaucho.jpg", "https://cdn.discordapp.com/attachments/1028278901312585798/1048990233338859581/b5088df627046f4a46e9469ad01be4af.jpg"},
            {"7", "Piracicaba", "McDonald's", "Gastronomia","https://cdn.discordapp.com/attachments/1008789338412306452/1048952606548045864/mcdonalds-628x353_2.jpg"},

            {"8", "Passos", "Dianelli Massas e Doces", "Gastronomia", "https://cdn.discordapp.com/attachments/1008789338412306452/1048950354827542618/image.png"},
            {"9", "Passos", "Kubanos Lounge e Bar", "Gastronomia", "https://cdn.discordapp.com/attachments/1028278901312585798/1048994215889207326/download.jpg"},
            {"10", "Passos", "Mangutti Sorvetes", "Gastronomia", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995020599988225/mangutti-500x500.jpg"},
            {"11", "Passos", "Praça da Matriz", "Turismo", "https://cdn.discordapp.com/attachments/1008789338412306452/1048954345556819968/image.png"},
            {"12", "Passos", "Borogodó Botequim", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048955605223743538/image.png"},

            {"13", "São Paulo", "Museu do Futebol", "Turismo", "https://cdn.discordapp.com/attachments/1028278901312585798/1048991056340992191/220201-Estadio.jpg"},
            {"14", "São Paulo", "Museu de Arte de São Paulo Assis Chateaubriand", "Evento", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995020826488894/masp-en-la-avenida-paulista.jpg"},
            {"15", "São Paulo", "Ponte Estaiada", "Turismo", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995021640183899/shutterstock_155728031.jpg"},
            {"16", "São Paulo", "Coco Bambu - JK", "Gastronomia", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995021375934524/COCOBAMBU_TBF4095.jpg"},
            {"17", "São Paulo", "Mercado Municipal de São Paulo", "Turismo", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995021090717706/mercadaosp.jpg"},
            {"18", "São Paulo", "O Mágico de Oz", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048958524442542200/image.png"}

    };




    TextView markertxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        markertxt = findViewById(R.id.marker);

        String title = getIntent().getStringExtra("title");
        markertxt.setText(title);

        for(int i=0; i<evento.length;i++){
            if((evento[i] != null)){
                cont++;
            }
        }

        for(int i=0; i<cont; i++){
            if(title.equals(evento[i][2])){
                ImageView imageView = findViewById(R.id.imageView);
                    Picasso.with(this)
                            .load(evento[i][4])
                            .into(imageView);
            }
        }

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(clisque == false){
                    for (int i =0; i<9; i++){
                        if(title.equals(evento[i][2])){
                            Picasso.with(getApplicationContext())
                                    .load(evento[i][5])
                                    .into(imageView);
                            clisque = true;
                        }
                    }
                }else{
                    for (int i =0; i<9; i++){
                        if(title.equals(evento[i][2])){
                            Picasso.with(getApplicationContext())
                                    .load(evento[i][4])
                                    .into(imageView);
                            clisque = false;
                        }
                    }
                }

                }
            });


    }
}


