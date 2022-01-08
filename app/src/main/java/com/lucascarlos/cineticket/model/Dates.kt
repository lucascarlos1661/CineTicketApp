package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("movieId")
    val movieId: String,
    @SerializedName("days")
    val days: List<Days>,
)

data class Days(
    @SerializedName("date")
    val date: String,
    @SerializedName("weekday")
    val weekday: String,
    @SerializedName("rooms")
    val rooms: List<Rooms>
)