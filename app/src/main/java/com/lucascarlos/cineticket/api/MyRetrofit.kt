package com.lucascarlos.cineticket.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit {

    private val retrofit: Retrofit
    fun moviesApi(): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    companion object {
        private const val BASE_URL = "http://localhost:3000/"

        var myRetrofit: MyRetrofit? = null

        @get:Synchronized
        val instance: MyRetrofit?
            get() {
                if (myRetrofit == null) {
                    myRetrofit = MyRetrofit()
                }
                return myRetrofit
            }
    }

    init {
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}