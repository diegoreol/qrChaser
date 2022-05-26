package com.example.qrchaserlaptop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsultarRegistro extends AppCompatActivity {

    private TextView resuLogin;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_registro);
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        queue = Volley.newRequestQueue(this);
        // para pedir a php la validacion del registro
        obtenerDatosVolley(username,password);
        resuLogin = (TextView) findViewById(R.id.resuRegistro);
    }

    private void obtenerDatosVolley(final String username,final String password) {
        System.out.println(username);
        System.out.println(password);
        final String url = "http://192.168.162.179/proyecto/registrar.php?username="+username+"&password="+password;
        System.out.println(url);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String respuesta = response.getString("respuesta");
                    if(respuesta.equals("true")){
                        Intent goMain = new Intent(ConsultarRegistro.this, MainActivity.class);
                        goMain.putExtra("username", username);
                        goMain.putExtra("password", password);
                        Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
                        startActivity(goMain);
                    }else{
                        Toast.makeText(getApplicationContext(),"Error al registrarse",Toast.LENGTH_SHORT).show();
                        Intent goMain = new Intent(ConsultarRegistro.this, MainActivity.class);
                        startActivity(goMain);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error de comunicacion",Toast.LENGTH_SHORT).show();

                    }

                });

        //añadir cola de peticiones
        queue.add(request);
    }
}