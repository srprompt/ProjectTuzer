package com.example.james_mark2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.james_mark2.mPicasso.PicassoClient;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    int cont=0;
    boolean clisque = false;
    //id, cidade, nome_evento, categoria, url_imagem, local, endereço
    String evento [][] = {
            {"1", "Piracicaba", "Curso Boviplan de Gestão Agropecuária", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048941382003327016/BOI.jpg", "Beira Rio Palace Hotel", "Rua Luiz de Queiroz, 51, Centro, Piracicaba, SP"},
            {"2", "Piracicaba", "Porto Certo", "Gastronomia", "https://cdn.discordapp.com/attachments/1008789338412306452/1048944268468822066/image.png", "Restaurante", "Av. Beira Rio, 141 - Centro, Piracicaba - SP, 13400-820"},
            {"3", "Piracicaba", "Alto do Mirante", "Turismo", "https://cdn.discordapp.com/attachments/1008789338412306452/1048947822956912640/image.png", "Elevador Turístico", "Av. Armando de Salles Oliveira - Centro, Piracicaba - SP"},
            {"4", "Piracicaba", "Zoológico", "Turismo", "https://media.discordapp.net/attachments/1008789338412306452/1048949043461627984/image.png", "Zoológico Municipal de Piracicaba", "Av. Mal. Castelo Branco, 426 - Jardim Primavera, Piracicaba - SP, 13412-010"},
            {"5", "Piracicaba", "Várzea Cup", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048950210627387392/146672963_3442767612495847_6830975024017549386_o-1024x799_1.jpg", "Clube Atlético Piracicabano", "Av. Brasília, 571 - Vila Rezende, Piracicaba - SP, 13412-120"},
            {"6", "Piracicaba", "TAMO AQUI", "TAMO AQUI","https://cdn.discordapp.com/attachments/1008789338412306452/1048953244115804251/Ronaldinho-Gaucho.jpg", "IFSP(Campus Piracicaba)", "Rua Diácono Jair de Oliveira, 1005 - Santa Rosa CEP 13414-155 - Piracicaba - SP", "https://cdn.discordapp.com/attachments/1028278901312585798/1048990233338859581/b5088df627046f4a46e9469ad01be4af.jpg"},
            {"7", "Piracicaba", "McDonald's", "Gastronomia","https://cdn.discordapp.com/attachments/1008789338412306452/1048952606548045864/mcdonalds-628x353_2.jpg", "McDonald's Piracicaba", "Av. Armando de Salles Oliveira, 2199 - Centro, Piracicaba - SP, 13414-018"},

            {"8", "Passos", "Dianelli Massas e Doces", "Gastronomia", "https://cdn.discordapp.com/attachments/1008789338412306452/1048950354827542618/image.png", "Confeitaria","R. Dois de Novembro, 151 - Centro, Passos - MG, 37900-128"},
            {"9", "Passos", "Kubanos Lounge e Bar", "Gastronomia", "https://cdn.discordapp.com/attachments/1028278901312585798/1048994215889207326/download.jpg", "Gastropub","Av. Comendador Francisco Avelino Maia, 3415 - Canjeranus, Passos - MG, 37900-001"},
            {"10", "Passos", "Mangutti Sorvetes", "Gastronomia", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995020599988225/mangutti-500x500.jpg", "Sorveteria", "Praça Monsenhor Messias Braganca, 209 - Centro, Passos - MG, 37900-084"},
            {"11", "Passos", "Praça da Matriz", "Turismo", "https://cdn.discordapp.com/attachments/1008789338412306452/1048954345556819968/image.png", "Praça", "R. Dr. Bernardino Vieira - Centro, Passos - MG, 37900-063"},
            {"12", "Passos", "Borogodó Botequim", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048955605223743538/image.png", "Bar", "Av. Dr. Breno Soares Maia, 92 - Belo Horizonte, Passos - MG, 37900-026"},

            {"13", "São Paulo", "Museu do Futebol", "Turismo", "https://cdn.discordapp.com/attachments/1028278901312585798/1048991056340992191/220201-Estadio.jpg", "Museu", "Praça Charles Miller, s/n - Pacaembu, São Paulo - SP, 01234-010"},
            {"14", "São Paulo", "Museu de Arte de São Paulo Assis Chateaubriand", "Evento", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995020826488894/masp-en-la-avenida-paulista.jpg", "Museu de Arte", "Av. Paulista, 1578 - Bela Vista, São Paulo - SP, 01310-200"},
            {"15", "São Paulo", "Ponte Estaiada", "Turismo", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995021640183899/shutterstock_155728031.jpg", "Ponte", "Av. Jornalista Roberto Marinho, 6807 - Jardim Panorama, São Paulo - SP, 04567-003"},
            {"16", "São Paulo", "Coco Bambu - JK", "Gastronomia", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995021375934524/COCOBAMBU_TBF4095.jpg", "Restaurante", "Av. Antônio Joaquim de Moura Andrade, 737 - Vila Nova Conceição, São Paulo - SP, 04507-000"},
            {"17", "São Paulo", "Mercado Municipal de São Paulo", "Turismo", "https://cdn.discordapp.com/attachments/1028278901312585798/1048995021090717706/mercadaosp.jpg", "Mercado", "R. da Cantareira, 306 - Centro Histórico de São Paulo, São Paulo - SP, 01024-900"},
            {"18", "São Paulo", "O Mágico de Oz", "Evento", "https://cdn.discordapp.com/attachments/1008789338412306452/1048958524442542200/image.png", "Teatro", "Av. Brigadeiro Luís Antônio, 931 - Bela Vista, São Paulo - SP, 01317-001"}

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
                TextView txtView_lugar_cidade = findViewById(R.id.txtView_lugar_cidade);
                TextView txtView_enderco = findViewById(R.id.txtView_endereco);
                ImageView imageView = findViewById(R.id.imageView);
                    Picasso.with(this)
                            .load(evento[i][4])
                            .into(imageView);
                txtView_lugar_cidade.setText(evento[i][5] +" - " + evento[i][1]);
                txtView_enderco.setText(evento[i][6]);
            }
        }

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(clisque == false){
                    for (int i =0; i<cont; i++){
                        if(title.equals(evento[i][2]) && title.equals("TAMO AQUI")){
                            Picasso.with(getApplicationContext())
                                    .load(evento[i][7])
                                    .into(imageView);
                            clisque = true;
                        }
                    }
                }else{
                    for (int i =0; i<cont; i++){
                        if(title.equals(evento[i][2]) && title.equals("TAMO AQUI")){
                            Picasso.with(getApplicationContext())
                                    .load(evento[i][4])
                                    .into(imageView);
                            clisque = false;
                        }
                    }
                }

                }
            });

        Button btnTrajeto = findViewById(R.id.btnTrajeto);
        btnTrajeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<cont; i++){
                    if (title.equals(evento[i][2])){
                        String uri = "http://maps.google.com/maps?saddr=" + evento[i][6];
                        System.out.println(evento[i][6]);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(intent);
                    }
                }
            }
        });


    }
}


