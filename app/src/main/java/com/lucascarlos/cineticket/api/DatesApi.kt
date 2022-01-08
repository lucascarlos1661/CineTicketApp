package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Dates
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DatesApi {
    @GET("dates")
    fun getDates(@Query("movieId") movieId: String): Call<List<Dates>>
}