package com.example.series;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtNome;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNome = findViewById(R.id.edtNome);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(view -> {

            String nome = edtNome.getText().toString().trim();

            if (!nome.isEmpty()) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("usuario", nome);
                startActivity(intent);

                finish();
            }

        });
    }
}