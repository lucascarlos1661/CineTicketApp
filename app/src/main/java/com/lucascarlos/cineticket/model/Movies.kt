package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Movies (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("runTime")
    val runTime: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("storyLine")
    val storyLine: String,
    @SerializedName("posterUrl")
    val posterUrl: String
)