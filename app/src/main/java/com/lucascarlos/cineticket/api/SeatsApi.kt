package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Seats
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SeatsApi {
    @GET("movieSeats")
    fun getSeatsApi(@Query("room") room: String): Call<List<Seats>>
}