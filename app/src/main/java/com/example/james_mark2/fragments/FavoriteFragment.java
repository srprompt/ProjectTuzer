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

import com.example.james_mark2.BD.DBAdapter;
import com.example.james_mark2.R;
import com.example.james_mark2.mData.Favorito;
import com.example.james_mark2.mData.Passeios;
import com.example.james_mark2.mData.Usuario;
import com.example.james_mark2.mRecycler.MyAdapter;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Passeios> passeios = new ArrayList<>();
    ArrayList<Favorito> favoritos = new ArrayList<>();
    ArrayList<Usuario> usuarios;
    Usuario usuario = new Usuario();
    private int idUsuario;


    public FavoriteFragment() {

    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //instancia o recycler view pelo toptrends fragment
        rv = view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(getContext(), passeios);
        rv.setAdapter(adapter);

        carregaFavoritos();
    }

    private void carregaFavoritos() {
        favoritos.clear();
        DBAdapter db = new DBAdapter(getContext());
        db.openDB();
        Cursor c = db.getFavoritos();
        while (c.moveToNext()){
            int idFavorito = c.getInt(0);
            int idPasseio = c.getInt(1);
            int idUsuario = c.getInt(2);

            Favorito favorito = new Favorito();
            favorito.setIdFavorito(idFavorito);
            favorito.setIdPasseio(idPasseio);
            favorito.setIdUsuario(idUsuario);
            favoritos.add(favorito);

            if(favoritos.size()>0){
                try {
                    passeios.clear();
                    DBAdapter db2 = new DBAdapter(getContext());
                    db.openDB();
                    Cursor c2 = db.getPasseios();
                    while (c2.moveToNext()) {
                        int id = c2.getInt(0);
                        String nome = c2.getString(1);
                        String url = c2.getString(2);
                        String descricao = c2.getString(3);
                        String local = c2.getString(4);
                        int categoria = c2.getInt(5);

                        for (int pos=0; pos < favoritos.size(); pos++) {
                            if (favoritos.get(pos).getIdPasseio() == id) {
                                Passeios ps = new Passeios();
                                ps.setId(id);
                                ps.setNome(nome);
                                ps.setUrl(url);
                                ps.setDescricao(descricao);
                                ps.setLocal(local);
                                ps.setCategoria(categoria);

                                passeios.add(ps);
                            }
                        }
                    }

                    if (passeios.size() > 0) {


                            rv.setAdapter(adapter);

                    }
                    db2.closeDB();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            db.closeDB();
        }
    }
}