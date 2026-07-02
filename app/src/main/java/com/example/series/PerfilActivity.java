package com.example.series;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

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
        listAvaliacoes.setOnItemLongClickListener((parent, view, position, id) -> {

            new androidx.appcompat.app.AlertDialog.Builder(PerfilActivity.this)
                    .setTitle("Excluir avaliação")
                    .setMessage("Deseja excluir esta avaliação?")
                    .setPositiveButton("Sim", (dialog, which) -> {

                        Dados.avaliacoes.remove(position);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(
                                PerfilActivity.this,
                                "Avaliação removida!",
                                Toast.LENGTH_SHORT
                        ).show();

                    })
                    .setNegativeButton("Cancelar", null)
                    .show();

            return true;
        });
    }
}