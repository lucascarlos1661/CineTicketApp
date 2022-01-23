package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("movieTitle")
    val movieTitle: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("movieRoom")
    val movieRoom: String?,
    @SerializedName("movieDate")
    val movieDate: String?,
    @SerializedName("movieTime")
    val movieTime: String?,
    @SerializedName("seats")
    val seats: List<String>,
    @SerializedName("seatsType")
    val seatType: SeatType
)

data class SeatType(
    @SerializedName("entire")
    val entire: String,
    @SerializedName("half")
    val half: String
)
