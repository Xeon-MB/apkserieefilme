package com.example.series;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {

    TextView txtPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtPerfil = findViewById(R.id.txtPerfil);

        String usuario = getIntent().getStringExtra("usuario");
        ArrayList<String> avaliacoes = getIntent().getStringArrayListExtra("avaliacoes");

        String texto = "👤 Usuário: " + usuario + "\n\n";

        if (avaliacoes != null && !avaliacoes.isEmpty()) {

            texto += "📋 Minhas Avaliações:\n\n";

            for (String avaliacao : avaliacoes) {
                texto += avaliacao + "\n\n";
            }

        } else {

            texto += "Você ainda não fez nenhuma avaliação.";

        }

        txtPerfil.setText(texto);
    }
}