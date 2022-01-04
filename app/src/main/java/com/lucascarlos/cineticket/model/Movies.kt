package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Movies(
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
    val posterUrl: String,
    @SerializedName("rooms")
    val rooms: Array<Rooms>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movies

        if (id != other.id) return false
        if (title != other.title) return false
        if (runTime != other.runTime) return false
        if (genre != other.genre) return false
        if (rating != other.rating) return false
        if (age != other.age) return false
        if (storyLine != other.storyLine) return false
        if (posterUrl != other.posterUrl) return false
        if (!rooms.contentEquals(other.rooms)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + runTime.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + age.hashCode()
        result = 31 * result + storyLine.hashCode()
        result = 31 * result + posterUrl.hashCode()
        result = 31 * result + rooms.contentHashCode()
        return result
    }
}