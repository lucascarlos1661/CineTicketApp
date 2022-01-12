package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Seats(
    @SerializedName("y")
    val y: String,
    @SerializedName("seat")
    val seat: List<String>
)