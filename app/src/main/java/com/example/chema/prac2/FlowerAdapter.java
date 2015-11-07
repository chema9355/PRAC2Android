package com.example.chema.prac2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.chema.prac2.model.Flower;
import com.squareup.picasso.Picasso;

public class FlowerAdapter extends ArrayAdapter<Flower> {

    Context _contexto;
    private ArrayList<Flower> _flowers;

    public FlowerAdapter(Context context, ArrayList<Flower> flowers) {
        super(context, R.layout.layout_listado_flowers, flowers);
        this._flowers = flowers;
        this._contexto = context;
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) _contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listado_flowers, null);
        }
        Flower flower = this._flowers.get(position);

        Log.i("ADAPTER", flower.toString());

        if (flower != null) {
            TextView tvId = (TextView) convertView.findViewById(R.id.tvFlowerId);
            tvId.setText(String.format("%d", flower.getProductId()));

            TextView tvNombre = (TextView) convertView.findViewById(R.id.tvFlowerNombre);
            tvNombre.setText(flower.getName());

            ImageView ivImagenflower = (ImageView) convertView.findViewById(R.id.ivFlowerPhoto);
            Picasso.with(_contexto).load(flower.getPhoto()).into(ivImagenflower);
        }

        return convertView;
    }


}