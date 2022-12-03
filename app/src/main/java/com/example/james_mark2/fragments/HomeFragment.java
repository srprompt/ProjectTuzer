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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
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
        //usar funcao save pra adicionar no banco.
        //save("teste","https://blogdeviagens.com.br/wp-content/uploads/2021/04/5_passeios_em_joao_pessoa.jpg"); Exemplo adicionar

        //carregaBancoInicial(); // chamar apenas uma vez *descomentar caso o banco esteja vazio (n sei se o banco é compartilhado

        retrieve(); //Depois de que o banco tiver alguma carga, só chamar essa pra pegar as imagens e texto

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

    private void save(String name, String url, String descricao,String local, String categoria){
        DBAdapter db = new DBAdapter(getContext());
        db.openDB();
        long result=db.add(name,url,descricao,local, categoria);
        if(result!=1){
            Toast.makeText(getContext(),"Não foi possivel salvar.",Toast.LENGTH_SHORT).show();
        }
        db.closeDB();
    }

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

    //Essa função só está aqui para efetuar uma carga de conteudo inicial enquanto não é possivel adicionar algo pelo aplicativo
    //É necessario chamar apenas uma vez, realizar uma chamada na primeira execução caso o banco esteja sem conteudo e comentar
    //depois para não duplicar itens no banco
    private void carregaBancoInicial(){
        //Exemplo adicionando as imagens que estavam antes
        save("Passeio 1","https://cdn.culturagenial.com/imagens/cristo-redentor-3-cke.jpg","Descricao evento 1","Piracicaba","1");
        save("Passeio 2","https://h8f7z4t2.stackpathcdn.com/wp-content/uploads/2015/10/pontos-turisticos-em-roma-640x449.jpg", "Descricao evento 2","São Paulo","1");
        save("Passeio 3","https://classic.exame.com/wp-content/uploads/2016/09/size_960_16_9_taj-mahal7.jpg?quality=70&strip=info&w=960", "Descricao evento 3","Piracicaba","2");
        save("Passeio 4","https://www.remessaonline.com.br/blog/wp-content/uploads/2022/06/pontos-turisticos-mais-visitados-do-mundo-1170x780.jpg.optimal.jpg", "Descricao evento 4","São Paulo","3");
    }
}