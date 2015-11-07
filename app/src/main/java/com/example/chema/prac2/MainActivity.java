package com.example.chema.prac2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.chema.prac2.model.Flower;
import com.example.chema.prac2.model.RepositorioFlowers;


import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;



public class MainActivity extends AppCompatActivity {

    public static final String ENDPOINT ="http://services.hanselandpetal.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RepositorioFlowers repositorio = new RepositorioFlowers(this);

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowerInterface api = retrofit.create(FlowerInterface.class);

        Call<List<Flower>> call_async = api.getFlowers();

        call_async.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Response<List<Flower>> response, Retrofit retrofit) {

                for (Flower f : response.body())

                {
                    repositorio.add(new Flower(f.getName(), f.getCategory(), f.getInstructions(),
                            f.getPrice(), "http://services.hanselandpetal.com/photos/" +f.getPhoto()));

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        final ArrayList<Flower> flowers = repositorio.getAll();
        FlowerAdapter adaptador = new FlowerAdapter(this, flowers);
        ListView lvListadoFlowers = (ListView) findViewById(R.id.lvListadoFlowers);
        lvListadoFlowers.setAdapter(adaptador);

        lvListadoFlowers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, ShowFlower.class);
                Flower f = flowers.get(position);
                intent.putExtra("MOSTRAR_Flower", f);
                startActivity(intent);
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // case 1:
            case R.id.action_post:
                Intent intent = new Intent (MainActivity.this, DoPost.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
