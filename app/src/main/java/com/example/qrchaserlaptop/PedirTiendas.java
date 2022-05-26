package com.example.qrchaserlaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PedirTiendas extends AppCompatActivity {

    private RequestQueue queue;
    private TextView txRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_tiendas);
        txRespuesta = (TextView) findViewById(R.id.txRespuesta);
        queue = Volley.newRequestQueue(this);
        // para pedir a php las tiendas
        obtenerDatosVolley();
    }

    private void obtenerDatosVolley() {
        final String url = "http://192.168.162.179/proyecto/pedirTiendasv3.php";
        System.out.println(url);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray respuesta = response.getJSONArray("tiendas");

                    System.out.println(respuesta.length());
                    /*
                    for(int i = 0; i< respuesta.length() ;i++){
                        System.out.println(respuesta.getString(i));

                    }
                    */
                    JSONObject tienda = respuesta.getJSONObject(0);
                    String id = tienda.getString("id");
                    String ciudad = tienda.getString("ciudad");
                    String localizacion = tienda.getString("localizacion");
                    String QR = tienda.getString("QR");
                    txRespuesta.setText("id :" + id + " : Ciudad " + ciudad + " Coordenadas "
                            + localizacion + " COD QR: " + QR);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PedirTiendas.this, "Error de respuesta", Toast.LENGTH_SHORT).show();
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