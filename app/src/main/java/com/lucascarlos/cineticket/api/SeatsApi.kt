package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Seats
import retrofit2.Call
import retrofit2.http.GET

interface SeatsApi {
    @GET("seats")
    fun getSeatsApi(): Call<List<Seats>>
}