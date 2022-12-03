package com.example.james_mark2.fragments;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james_mark2.BD.DBAdapter;
import com.example.james_mark2.R;
import com.example.james_mark2.mData.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText nomeLogin;
    private EditText senhaLogin;
    private Button loginBtn;
    private Spinner spinnerEstado;
    private EditText editTextNome;
    private EditText editTextCidade;
    private EditText editTextEmail;
    private EditText editTextDataNasc;
    private Spinner spinnerSexo;
    private ImageView imageViewUsario;
    private FloatingActionButton menuEditar;
    private Button btnSalvar;
    private FloatingActionButton menuPerfil;
    private int id_sexo,id_estado;

    Usuario usuario = new Usuario();
    ArrayList<Usuario> usuarios = new ArrayList<>();


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        spinnerEstado = view.findViewById(R.id.spinnerEstado);
        editTextNome = view.findViewById(R.id.editTextNome);
        editTextDataNasc = view.findViewById(R.id.editTextDataNasc);
        editTextCidade = view.findViewById(R.id.editTextCidade);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        spinnerSexo = view.findViewById(R.id.spinnerSexo);
        imageViewUsario = view.findViewById(R.id.imageViewUsuario);
        menuEditar = view.findViewById(R.id.btnMenuEditar);
        btnSalvar = view.findViewById(R.id.btnSalvar);
        menuPerfil = view.findViewById(R.id.btnMenuLogin);

        habilitaDesabilitaCampos(false);

       // carregaperfil();

        retrieve();
        //preencheCampos();

        //login perfil
        menuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });


        //Habilitar edição perfil

        menuEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habilitaDesabilitaCampos(true);



            }
        });


        //Salvar perfil no banco
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                habilitaDesabilitaCampos(false);

/*
                usuario.setNome(editTextNome.getText().toString());
                usuario.setCidade(editTextCidade.getText().toString());
                usuario.setDataNasc(editTextDataNasc.getText().toString());
                usuario.setEmail(editTextEmail.getText().toString());
                usuario.setSexo(id_sexo);
                usuario.setEstado(id_estado);
                usuarios.add(usuario);*/
                DBAdapter db = new DBAdapter(getContext());
                db.openDB();
                long result=db.addPerfil(editTextNome.getText().toString(),"",editTextDataNasc.getText().toString(),editTextCidade.getText().toString(),
                        id_estado,editTextEmail.getText().toString(),id_sexo);
                if(result!=1 || editTextNome.getText().toString().length()<=0){
                    Toast.makeText(getContext(),"Não foi possivel salvar.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"Salvo com sucesso",Toast.LENGTH_SHORT).show();

                }
                db.closeDB();
            }
        });


        //Adapter spinner sexo
        String[] sexo = new String[]{
                "Sexo",
                "Masculino",
                "Feminino",
                "Outro"};
        final List<String> list_sexo = new ArrayList<>(Arrays.asList(sexo));
        ArrayAdapter<String> spinnerArrayAdapterSexo = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line, list_sexo) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textViewSexo = (TextView) view;
                if (position == 0) {
                    textViewSexo.setTextColor(Color.GRAY);
                } else {
                    textViewSexo.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapterSexo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerSexo.setAdapter(spinnerArrayAdapterSexo);

        spinnerSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_sexo = position;
               // usuario.setSexo(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Fim adapter sexo

        //Adapter spinner estado
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                Arrays.asList(getResources().getStringArray(R.array.estados))
        ) {
            @Override
            public boolean isEnabled(int position){
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }
            @Override
            public View getDropDownView(
                    int position, View convertView,
                    @NonNull ViewGroup parent) {

                // Get the item view
                View view = super.getDropDownView(
                        position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    textView.setTextColor(Color.GRAY);
                }
                else { textView.setTextColor(Color.BLACK); }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_estado = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerEstado.setAdapter(spinnerArrayAdapter);
        //fim adapter estado

    }

    private void habilitaDesabilitaCampos(boolean controle){
        editTextNome.setEnabled(controle);
        editTextEmail.setEnabled(controle);
        editTextCidade.setEnabled(controle);
        editTextDataNasc.setEnabled(controle);
        spinnerEstado.setEnabled(controle);
        spinnerSexo.setEnabled(controle);
        btnSalvar.setEnabled(controle);
    }

    private void displayDialog(){
        Dialog d = new Dialog(getContext());
        d.setTitle("Save To DB");
        d.setContentView(R.layout.display_login);

        nomeLogin = d.findViewById(R.id.nomeLoginEditTxt);
        senhaLogin = d.findViewById(R.id.senhaLoginEditTxt);
        loginBtn = d.findViewById(R.id.saveLoginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              retrieve();
              usuario.setNomeLogin(nomeLogin.getText().toString());
                preencheCampos();
                d.hide();
            }
        });

        d.show();
    }


    private void retrieve(){
        //usuario.setNomeLogin(nomeLogin.getText().toString());
        try {
            usuarios.clear();
            DBAdapter db = new DBAdapter(getContext());
            db.openDB();
            Cursor c = db.getPerfil();
            while (c.moveToNext()){
                int id = c.getInt(0);
                String nome = c.getString(1);
                String url = c.getString(2);
                String email = c.getString(3);
                String dataNasc = c.getString(4);
                String cidade = c.getString(5);
                int estado = c.getInt(6);
                int sexo = c.getInt(7);

                    Usuario us = new Usuario();
                    us.setId(id);
                    us.setNome(nome);
                    us.setUrl(url);
                    us.setDataNasc(dataNasc);
                    us.setCidade(cidade);
                    us.setEstado(estado);
                    us.setEmail(email);
                   us.setSexo(sexo);

                    usuarios.add(us);

                    if(usuarios.size()>0){
                        //preencheCampos();
                    }
                    db.closeDB();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private void preencheCampos(){

        for(int i=0;i<usuarios.size();i++){
           if(usuarios.get(i).getNome().equals(String.valueOf(usuario.getNomeLogin()))){
               usuario.setId(i);
               editTextNome.setText(usuarios.get(i).getNome());
               editTextEmail.setText(usuarios.get(i).getEmail());
               editTextDataNasc.setText(usuarios.get(i).getDataNasc());
               editTextCidade.setText(usuarios.get(i).getCidade());
               spinnerEstado.setSelection(usuarios.get(i).getEstado());
               spinnerSexo.setSelection(usuarios.get(i).getSexo());
               //url
           }
        }

    }
}