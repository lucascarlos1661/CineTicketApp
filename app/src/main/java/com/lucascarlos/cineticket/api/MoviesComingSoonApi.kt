package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Movies
import retrofit2.Call
import retrofit2.http.GET

interface MoviesComingSoonApi {
    @GET("comingSoon")
    fun getMoviesComingSoonApi(): Call<List<Movies>>
}