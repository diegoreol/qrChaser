package com.example.qrchaserlaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private TextView txUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txUsername = (TextView) findViewById(R.id.txUsername);
        String username = getIntent().getStringExtra("username");
        txUsername.setText(username);

    }

    public void pedirProductos(View view){
        Intent goPedirProductos = new Intent(Login.this, PedirProductos.class);
        startActivity(goPedirProductos);

    }

    public void pedirTiendas(View view){
        Intent goPedirTiendas = new Intent(Login.this, PedirTiendas.class);
        startActivity(goPedirTiendas);

    }

}