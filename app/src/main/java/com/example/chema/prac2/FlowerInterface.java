package com.example.chema.prac2;

import com.example.chema.prac2.model.Flower;

import retrofit.Call;
import retrofit.Callback;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by CHEMA on 01/11/2015.
 */
public interface FlowerInterface {

    @GET("/feeds/flowers.json")
    Call <List<Flower>> getFlowers();

    @POST("/feeds/flowers.json")
    Call<Flower> createFlower(@Body Flower flower);

}
