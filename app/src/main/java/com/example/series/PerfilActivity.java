package com.example.series;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    TextView txtPerfil;
    ListView listAvaliacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtPerfil = findViewById(R.id.txtPerfil);
        listAvaliacoes = findViewById(R.id.listAvaliacoes);

        String usuario = getIntent().getStringExtra("usuario");

        txtPerfil.setText("👤 " + usuario);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Dados.avaliacoes
        );

        listAvaliacoes.setAdapter(adapter);
    }
}