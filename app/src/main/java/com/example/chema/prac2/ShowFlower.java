package com.example.chema.prac2;


        import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.ImageView;
        import android.widget.TextView;
        import com.example.chema.prac2.model.Flower;
        import com.squareup.picasso.Picasso;

public class ShowFlower extends AppCompatActivity {

    TextView tvId, tvNombre, tvCategoria, tvInstrucciones;
    ImageView ivFLower;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mostrar_flower);

        contexto = getApplicationContext();

        // Mostrar el icono back
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Flower flower = getIntent().getParcelableExtra("MOSTRAR_Flower");

        // Vistas
        tvId               = (TextView)  findViewById(R.id.tvMostrarFlowerId);
        tvNombre           = (TextView)  findViewById(R.id.tvMostrarFlowerNombre);
        tvCategoria        = (TextView)  findViewById(R.id.tvMostrarFlowerCategoria);
        tvInstrucciones           = (TextView)  findViewById(R.id.tvMostrarFlowerInstrucciones);
        ivFLower       = (ImageView) findViewById(R.id.ivMostrarFlor);


        // Asignar valores a las vistas
        tvId.setText("Identificador: " +String.format("%d", flower.getProductId()));
        tvNombre.setText("Nombre: " +flower.getName());
        tvCategoria.setText("Categoria: " + flower.getCategory());
        tvInstrucciones.setText("Instrucciones: "+flower.getInstructions());

        if (flower.getPhoto() == null)
            ivFLower.setImageResource(R.drawable.sinfoto);
        else if (!hayRed())
            ivFLower.setImageResource(R.drawable.sinconexion);
        else {
             Picasso.with(contexto).load(flower.getPhoto()).into(ivFLower);
        }

        setResult(RESULT_OK);
    }

    /**
     * Determina si hay conexión a la red (wifi ó movil)
     * @return bool resultado
     */
    public boolean hayRed() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean conectividad = false;

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null ) {
            boolean hayWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            boolean hayMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            conectividad = hayWifi || hayMobile;
        }

        return conectividad;
    }
}