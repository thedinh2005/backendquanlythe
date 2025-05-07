package com.example.quanlythe.network;

import com.example.quanlythe.model.Card;
import com.example.quanlythe.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    // USER
    @POST("/register")
    Call<Void> registerUser(@Body User user);

    @POST("/login")
    Call<Void> loginUser(@Body User user);

    // CARD
    @GET("/cards/get")
    Call<List<Card>> getCards();

    @POST("/cards/create")
    Call<Card> createCard(@Body Card card);

    @DELETE("/cards/delete")
    Call<Void> deleteCard(@Query("id") long id);
}
