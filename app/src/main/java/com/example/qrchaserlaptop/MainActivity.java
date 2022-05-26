package com.example.qrchaserlaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //declaraciones de objetos y/o variables
    private TextView texto;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //enlazar vista y botones
        setContentView(R.layout.activity_main);
        texto = (TextView) findViewById(R.id.txTexto);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        btRegistro = (Button) findViewById(R.id.btRegistro);

    }

    public void irCLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Intent goConsultarLogin = new Intent(MainActivity.this, ConsultarLogin.class);
        goConsultarLogin.putExtra("username", username);
        goConsultarLogin.putExtra("password", password);
        startActivity(goConsultarLogin);

    }

    public void irCRegistro(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Intent goConsultarRegistro = new Intent(MainActivity.this, ConsultarRegistro.class);
        goConsultarRegistro.putExtra("username", username);
        goConsultarRegistro.putExtra("password", password);
        startActivity(goConsultarRegistro);
    }

}