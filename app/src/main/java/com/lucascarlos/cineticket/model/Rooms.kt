package com.lucascarlos.cineticket.model

import com.google.gson.annotations.SerializedName

data class Rooms(
    @SerializedName("number")
    val number: String,
    @SerializedName("audio")
    val audio: String,
    @SerializedName("schedules")
    val schedules: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rooms

        if (number != other.number) return false
        if (audio != other.audio) return false
        if (!schedules.contentEquals(other.schedules)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number.hashCode()
        result = 31 * result + audio.hashCode()
        result = 31 * result + schedules.contentHashCode()
        return result
    }
}