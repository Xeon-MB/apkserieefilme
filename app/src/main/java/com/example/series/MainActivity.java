package com.example.series;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spnFilmes;
    EditText edtNota;
    Button btnAdicionar, btnPerfil;
    TextView txtUsuario;

    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = getIntent().getStringExtra("usuario");

        txtUsuario = findViewById(R.id.txtUsuario);
        spnFilmes = findViewById(R.id.spnFilmes);
        edtNota = findViewById(R.id.edtNota);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnPerfil = findViewById(R.id.btnPerfil);

        txtUsuario.setText("Olá, " + usuario + "!");

        String[] filmes = {
                "Interestelar",
                "Breaking Bad",
                "Dark",
                "The Office",
                "Peaky Blinders",
                "Clube da Luta",
                "O Poderoso Chefão",
                "Matrix",
                "Vingadores: Ultimato",
                "Stranger Things",
                "Round 6",
                "The Batman",
                "Deadpool",
                "Homem-Aranha: Sem Volta Para Casa",
                "Invencível"
        };

        ArrayAdapter<String> adapterFilmes = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                filmes
        );

        adapterFilmes.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spnFilmes.setAdapter(adapterFilmes);

        btnAdicionar.setOnClickListener(view -> {

            String titulo = spnFilmes.getSelectedItem().toString();
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

    }
}