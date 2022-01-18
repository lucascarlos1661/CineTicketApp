package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Ticket
import retrofit2.Call
import retrofit2.http.GET

interface GetTicketApi {
    @GET("tickets")
    fun getTicketsApi(): Call<List<Ticket>>
}