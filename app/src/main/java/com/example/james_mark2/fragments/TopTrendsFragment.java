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
import com.example.james_mark2.mData.Passeios;
import com.example.james_mark2.mRecycler.MyAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopTrendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopTrendsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Passeios> passeios = new ArrayList<>();


    public TopTrendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopTrendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopTrendsFragment newInstance(String param1, String param2) {
        TopTrendsFragment fragment = new TopTrendsFragment();
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
        return inflater.inflate(R.layout.fragment_top_trends, container, false);
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

        carregaTop();
    }

    private void carregaTop(){
        passeios.clear();
        try {
            DBAdapter db = new DBAdapter(getContext());
            db.openDB();
            Cursor c = db.getPasseios();
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String nome = c.getString(1);
                String url = c.getString(2);
                String descricao = c.getString(3);
                String local = c.getString(4);
                Integer categoria = c.getInt(5);

                if(local.equals("Piracicaba")) {
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

            if (passeios.size() > 0) {
                rv.setAdapter(adapter);
            }
            db.closeDB();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}