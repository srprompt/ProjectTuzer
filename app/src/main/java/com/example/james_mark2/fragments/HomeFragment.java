package com.example.james_mark2.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.james_mark2.BD.DBAdapter;
import com.example.james_mark2.R;
import com.example.james_mark2.mData.Passeios;
import com.example.james_mark2.mRecycler.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Passeios> passeios = new ArrayList<>();

    RecyclerView rv;
    MyAdapter adapter;
    ImageButton categoria1;
    ImageButton categoria2;
    ImageButton categoria3;
    ImageButton categoria4;
    ImageButton categoria5;
    ImageButton categoria6;
    boolean bancoCarregado = false;



    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //instancia o recycler view pelo home fragment
        rv = view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //instancia botoes categoria
        categoria1 = view.findViewById(R.id.btnCategoria1);
        categoria2 = view.findViewById(R.id.btnCategoria2);
        categoria3 = view.findViewById(R.id.btnCategoria3);
        categoria4 = view.findViewById(R.id.btnCategoria4);
        categoria5 = view.findViewById(R.id.btnCategoria5);
        categoria6 = view.findViewById(R.id.btnCategoria6);



        //adapter
        adapter = new MyAdapter(getContext(), passeios);
        rv.setAdapter(adapter);

        if(!bancoCarregado) {
            retrieve(); //Busca cadastros no banco
            if(passeios.size()<=0){
            carregaBancoInicial();
            bancoCarregado=true;
            retrieve();
            }
        }




        //acaoes click categoria
        categoria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaCategoria(1);
            }
        });

        categoria2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaCategoria(2);
            }
        });

        categoria3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaCategoria(3);
            }
        });

        categoria4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaCategoria(4);
            }
        });

        categoria5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaCategoria(5);
            }
        });

        categoria6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaCategoria(6);
            }
        });



    }

    //Salva Eventos no banco
    private void save(String name, String url, String descricao,String local, String categoria){
        DBAdapter db = new DBAdapter(getContext());
        db.openDB();
        long result=db.add(name,url,descricao,local, categoria);
        if(result!=1){
            Toast.makeText(getContext(),"Não foi possivel salvar.",Toast.LENGTH_SHORT).show();
        }
        db.closeDB();
    }

    //Recupera Eventos do banco
    private void retrieve(){
        try {
            passeios.clear();
            DBAdapter db = new DBAdapter(getContext());
            db.openDB();
            Cursor c = db.getPasseios();
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String nome = c.getString(1);
                String url = c.getString(2);
                String descricao = c.getString(3);
                String local = c.getString(4);
                int categoria = c.getInt(5);

                Passeios ps = new Passeios();
                ps.setId(id);
                ps.setNome(nome);
                ps.setUrl(url);
                ps.setDescricao(descricao);
                ps.setLocal(local);
                ps.setCategoria(categoria);

                passeios.add(ps);
            }

            if (passeios.size() > 0) {
                rv.setAdapter(adapter);
            }
            db.closeDB();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //Recupera categorias dos eventos
    private void carregaCategoria(int categoriaId){
        try {
            passeios.clear();
            DBAdapter db = new DBAdapter(getContext());
            db.openDB();
            Cursor c = db.getPasseiosCategoria(categoriaId);
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String nome = c.getString(1);
                String url = c.getString(2);
                String descricao = c.getString(3);
                int categoria = c.getInt(4);


                if(categoria==categoriaId) {
                    Passeios ps = new Passeios();
                    ps.setId(id);
                    ps.setNome(nome);
                    ps.setUrl(url);
                    ps.setDescricao(descricao);
                    ps.setCategoria(categoria);
                    passeios.add(ps);
                }
            }

            if (passeios.size() > 0) {
                rv.setAdapter(adapter);
            }
            db.closeDB();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //Gera carga inicial do banco de dados
    private void carregaBancoInicial(){

        save("Porto Certo","https://cdn.discordapp.com/attachments/1008789338412306452/1048944268468822066/image.png","A empresa Porto Certo Emporio, Bar E Restaurante Ltda, localizada no bairro Centro, em Piracicaba-SP foi fundada em 2020. A atividade principal da empresa é Comércio Varejista de Bebidas.\n" +
                "Endereço: Avenida Beira Rio, 1045 - Centro\n" +"Piracicaba - SP\n" + "\n" + "CEP: 13400-820\n\n" + "Horários: \n" +
                "\nSegunda-feira: 11:30 às 22:30\n" +"Terça-feira: Não abre\n" +"Quarta-feira: 11:30 às 23:00\n" + "Quinta-feira: 11:30 às 23:00\n" +
                "Sexta-feira: 11:30 às 23:00\n" + "Sábado: 11:30 às 23:00\n" + "Domingo: 12:00 às 22:00","Piracicaba","3");

        save("Alto do Mirante","https://cdn.discordapp.com/attachments/1008789338412306452/1048947822956912640/image.png","Atração turística em Piracicaba, São Paulo.\n" +
                "Localizado sob o Rio Piracicaba, o mirante de 24 metros de altura oferece uma vista de 360º sobre a cidade.\n" +
                "\n" + "Endereço: Av. Armando de Salles Oliveira - Centro, Piracicaba - SP\n\n" +
                "Horário: Abre sáb. às 09:30\n\n" + "Telefone: (19) 3403-2635","Piracicaba","5");

        save("Zoológico Municipal de Piracicaba","https://cdn.discordapp.com/attachments/1008789338412306452/1048949043461627984/image.png",
                "Zoológico em Piracicaba, São Paulo.\n" +
                "Um aviário, um habitat de répteis e felinos grandes em áreas fechadas, além de parquinhos e parede de escalada.\n" +
                "\nEndereço: Av. Mal. Castelo Branco, 426 - Jardim Primavera, Piracicaba - SP, 13412-010\n" +
                "\nHorários: \n" + "domingo: 09:00–16:00\n" + "segunda-feira: Fechado\n" +
                "terça-feira: 09:00–16:00\n" + "quarta-feira: 09:00–16:00\n" +
                "quinta-feira: 09:00–16:00\n" + "sexta-feira: 09:00–16:00\n" +
                "sábado\t09:00–16:00\n\n" + "Telefone: (19) 3421-3425","Piracicaba","5");

        save("Dianelli Massas e Doces","https://cdn.discordapp.com/attachments/1008789338412306452/1048950354827542618/image.png",
                "Confeitaria\n\n" + "Endereço: R. Dois de Novembro, 151 - Centro, Passos - MG, 37900-128\n\n" +
                        "Horários: \n" + "domingo: 08:00–13:00\n" + "segunda-feira: Fechado\n" +
                        "terça-feira: 08:00–18:00\n" + "quarta-feira: 08:00–18:00\n" + "quinta-feira: 08:00–18:00\n" +
                        "sexta-feira: 08:00–18:00\n" + "sábado: 08:00–15:00\n\n" + "Telefone: (35) 98882-9466", "Passos","3");

        save("Praça da Matriz","https://cdn.discordapp.com/attachments/1008789338412306452/1048954345556819968/image.png",
                "Praça em Passos, Minas Gerais\n\n" + "Endereço: R. Dr. Bernardino Vieira - Centro, Passos - MG, 37900-063\n" +
                "\nHorários: \n" + "Todos os dias com atendimento 24 horas\n", "Passos","5");

        save("Borogodó Botequim","https://cdn.discordapp.com/attachments/1008789338412306452/1048955605223743538/image.png",
                "Bar\n" + "Opções de serviço: Refeição no local\n\n" +
                        "Endereço: Av. Dr. Breno Soares Maia, 92 - Belo Horizonte, Passos - MG, 37900-026\n\n" +
                        "Horários: \n" +"domingo: 18:00–00:00\n" + "segunda-feira: Fechado\n" + "terça-feira: 18:00–00:00\n" +
                        "quarta-feira: 18:00–00:00\n" + "quinta-feira: 18:00–00:00\n" + "sexta-feira: 18:00–02:00\n" +
                        "sábado: 18:00–02:00\n\n" + "Telefone: (35) 99936-3743","Passos","3");
        save("Museu do Futebol","https://cdn.discordapp.com/attachments/1008789338412306452/1048957236325666826/image.png",
                "Museu em São Paulo\n" +
                        "Museu do Futebol é um espaço voltado para os mais diferentes assuntos envolvendo a prática, a história e curiosidades do futebol brasileiro e mundial. Wikipédia\n" +
                        "\nLocalizado em: Estádio Municipal Paulo Machado de Carvalho\n\n" +
                        "Endereço: Praça Charles Miller, s/n - Pacaembu, São Paulo - SP, 01234-010\n" +
                        "Horário: \n" + "domingo: 09:00–17:00\n" + "segunda-feira: Fechado\n" + "terça-feira: 09:00–21:00\n" +
                        "quarta-feira: 09:00–17:00\n" + "quinta-feira: 09:00–17:00\n" + "sexta-feira: 09:00–17:00\n" +
                        "sábado: 09:00–17:00\n\n" + "Telefone: (11) 3664-3848\n" + "Visitantes: 419.363 (2014)\n" +
                        "Fundação: 2008","São Paulo","6");

        save("O Mágico de Oz","https://cdn.discordapp.com/attachments/1008789338412306452/1048958524442542200/image.png",
                "Baseado na obra de Lian Frank Baum. O espetáculo Mágico de Óz conta a clássica história de Dorothy, uma garota que tem sua casa levada"+
                        " por um ciclone, se perde em um mundo fantástico, e para saber o caminho de volta precisa encontrar o Grande Mágico de Oz. Com a ajuda da"+
                        " Bruxa boa do Norte, e de seus amigos Espantalho, Homem de Lata e Leão, Dorothy segue pela estrada de tijolos amarelos em direção à Cidade" +
                        " das Esmeraldas. Porém, a terrível Bruxa Má do Oeste promete atrapalhar os planos da garota com feitiços, magias e gargalhadas.\n" +
                        "\nEndereço: Av. Brigadeiro Luís Antônio, 931 - Bela Vista, São Paulo - SP, 01317-001\n\n" +
                        "Telefone: (11) 3105-3129","São Paulo","4");

        save("Wet'n Wild","https://cdn.discordapp.com/attachments/1008789338412306452/1048976058973683742/image.png",
                "O Réveillon 2023 - Wet'n Wild \n\n" + "Horário da festa: das 21h às 04h.\n" + "Endereço: Rodovia dos Bandeirantes, s/n, Zona Rural - Itupeva, SP\n\n" +
                        "Pool Party\n\n" + "- Piscina de ondas aberta para pular as sete ondas;\n" + "- Taça de espumante para brinde;\n" +
                        "- Open Bar: cerveja(Amstel), água e refrigerante\n\n" + "Camarote Azul\n\n" + "- Acesso a Pool Party;\n" +
                        "- Piscina de ondas aberta para pular as sete ondas;\n" + "- Taça de espumante para brinde;\n" + "- Open Bar: cerveja(Amstel), água e refrigerante\n\n" +
                        "Camarote Verde\n\n" + "- Acesso a Pool Party;\n" + "- Piscina de ondas aberta para pular as sete ondas;\n" + "- Taça de espumante para brinde;\n" +
                        "- Open Bar: cerveja(Heineken), água e refrigerante",
                "Itupeva","2");
    }
}