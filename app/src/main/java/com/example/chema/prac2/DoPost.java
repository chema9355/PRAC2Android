package com.example.chema.prac2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chema.prac2.MainActivity;
import com.example.chema.prac2.model.Flower;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class DoPost extends AppCompatActivity {

    public static final String ENDPOINT ="http://services.hanselandpetal.com";

    EditText id;
    EditText nombre;
    EditText categoria;
    EditText instrucciones;
    EditText precio;
    EditText foto;
    Button enviar;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        id = (EditText) findViewById(R.id.tvId);
        nombre = (EditText) findViewById(R.id.tvNombre);
        categoria = (EditText) findViewById(R.id.tvCategoria);
        instrucciones = (EditText) findViewById(R.id.tvInstrucciones);
        precio = (EditText) findViewById(R.id.tvPrecio);
        foto = (EditText) findViewById(R.id.tvPhoto);
        enviar = (Button) findViewById(R.id.btEnviar);
        info = (TextView) findViewById(R.id.info);
    }

//        enviar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//
//


        public void enviar(View v){
        Toast toast = Toast.makeText(getApplicationContext(), "Flor enviada.",Toast.LENGTH_LONG);
            info.setText("Flor enviada");


            Retrofit retrofit =  new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                FlowerInterface api = retrofit.create(FlowerInterface.class);
                Flower flower = new Flower(Integer.parseInt(id.getText().toString()), nombre.getText().toString(), categoria.getText().toString(), instrucciones.getText().toString(), Double.parseDouble(precio.getText().toString()), foto.getText().toString());

             Call<Flower> call = api.createFlower(flower);
        call.enqueue(new Callback<Flower>() {
            @Override
            public void onResponse(Response<Flower> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
     });
    }

    }