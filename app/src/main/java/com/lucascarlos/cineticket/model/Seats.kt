package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Seats(
    @SerializedName("room")
    val room: String,
    @SerializedName("seats")
    val seats: List<Seat>
)

data class Seat(
    @SerializedName("y")
    val y: String,
    @SerializedName("seat")
    val seat: List<String>
)