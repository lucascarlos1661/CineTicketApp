package com.lucascarlos.cineticket.api

import com.lucascarlos.cineticket.model.Ticket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PostTicketApi {
    @Headers("Content-Type: application/json")
    @POST("tickets")
    fun addTicket(@Body ticketData: Ticket): Call<Ticket>
}