package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Movies
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET("e5c64ffbc5d94264260d")
    fun getMoviesApi(): Call<List<Movies>>
}