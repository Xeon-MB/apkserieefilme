package com.example.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtFilme;
    TextView txtUsuario;

    Button btnAnterior, btnProximo;
    Button btnAdicionar;
    Button btnPerfil;

    EditText edtNota;

    ImageView imgFilme;

    Spinner spinnerFilmes;

    String usuario;

    String[] filmes = {
            "Interestelar",
            "Breaking Bad",
            "Dark",
            "The Office",
            "Matrix",
            "Carros 2",
            "Naruto",
            "Tokyo Ghoul",
            "Barbie em Vida de Sereia",
            "Stranger Things",

    };


    int[] imagens = {
            R.drawable.interestelar,
            R.drawable.breaking_bad,
            R.drawable.dark,
            R.drawable.the_office,
            R.drawable.matrix,
            R.drawable.carros_2,
            R.drawable.naruto,
            R.drawable.tokyo_ghoul,
            R.drawable.barbie,
            R.drawable.stranger_things,

    };

    int indice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = getIntent().getStringExtra("usuario");

        txtUsuario = findViewById(R.id.txtUsuario);
        edtNota = findViewById(R.id.edtNota);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnPerfil = findViewById(R.id.btnPerfil);

        imgFilme = findViewById(R.id.imgFilme);
        txtFilme = findViewById(R.id.txtFilme);

        btnAnterior = findViewById(R.id.btnAnterior);
        btnProximo = findViewById(R.id.btnProximo);
        spinnerFilmes = findViewById(R.id.spinnerFilmes);



        txtUsuario.setText("Olá, " + usuario + "!");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                filmes
        );
        spinnerFilmes.setAdapter(adapter);
        spinnerFilmes.setSelection(indice);

        atualizarFilme();

        spinnerFilmes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indice = position;
                atualizarFilme();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdicionar.setOnClickListener(view -> {

            String titulo = filmes[indice];
            String notaTexto = edtNota.getText().toString().trim();

            if (notaTexto.isEmpty()) {
                edtNota.setError("Digite uma nota.");
                return;
            }

            try {

                double nota = Double.parseDouble(notaTexto);

                if (nota < 0 || nota > 5) {
                    edtNota.setError("A nota deve estar entre 0 e 5.");
                    return;
                }

                Dados.avaliacoes.add(
                        "🎬 " + titulo +
                                "\n⭐ Nota: " + nota + "/5"
                );

                edtNota.setText("");

            } catch (NumberFormatException e) {
                edtNota.setError("Digite uma nota válida.");
            }

        });

        btnPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        });

        btnProximo.setOnClickListener(view -> {
            indice++;
            if (indice >= filmes.length) {
                indice = 0;
            }
            spinnerFilmes.setSelection(indice);
            atualizarFilme();
        });

        btnAnterior.setOnClickListener(view -> {
            indice--;
            if (indice < 0) {
                indice = filmes.length - 1;
            }
            spinnerFilmes.setSelection(indice);
            atualizarFilme();
        });
    }


    private void atualizarFilme() {
        imgFilme.setImageResource(imagens[indice]);
        txtFilme.setText(filmes[indice]);
    }
}