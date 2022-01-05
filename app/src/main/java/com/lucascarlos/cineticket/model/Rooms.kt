package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Rooms(
    @SerializedName("number")
    val number: String,
    @SerializedName("audio")
    val audio: String,
    @SerializedName("schedules")
    val schedules: List<String>
)