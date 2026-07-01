package com.example.series;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTitulo, edtNota;
    Button btnAdicionar, btnExcluir, btnPerfil;
    TextView txtUsuario;
    ListView listViewAvaliacoes;

    ArrayList<String> listaAvaliacoes;
    ArrayAdapter<String> adapter;

    int posicaoSelecionada = -1;

    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = getIntent().getStringExtra("usuario");

        txtUsuario = findViewById(R.id.txtUsuario);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtNota = findViewById(R.id.edtNota);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnPerfil = findViewById(R.id.btnPerfil);

        listViewAvaliacoes = findViewById(R.id.listViewAvaliacoes);

        txtUsuario.setText("Olá, " + usuario + "!");

        listaAvaliacoes = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaAvaliacoes
        );

        listViewAvaliacoes.setAdapter(adapter);

        btnAdicionar.setOnClickListener(view -> {

            String titulo = edtTitulo.getText().toString().trim();
            String notaTexto = edtNota.getText().toString().trim();

            if (!titulo.isEmpty() && !notaTexto.isEmpty()) {

                double nota = Double.parseDouble(notaTexto);

                if (nota >= 0 && nota <= 5) {

                    listaAvaliacoes.add("🎬 " + titulo + "\n⭐ Nota: " + nota + "/5");

                    adapter.notifyDataSetChanged();

                    edtTitulo.setText("");
                    edtNota.setText("");

                } else {
                    edtNota.setError("A nota deve estar entre 0 e 5");
                }

            }

        });
        listViewAvaliacoes.setOnItemClickListener((parent, view, position, id) -> posicaoSelecionada = position);

        btnExcluir.setOnClickListener(view -> {

            if (posicaoSelecionada != -1) {

                listaAvaliacoes.remove(posicaoSelecionada);

                adapter.notifyDataSetChanged();

                posicaoSelecionada = -1;

            }

        });

        btnPerfil.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);

            intent.putExtra("usuario", usuario);
            intent.putStringArrayListExtra("avaliacoes", listaAvaliacoes);

            startActivity(intent);

        });

    }
}