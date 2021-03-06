package com.lucascarlos.cineticket.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit {

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun moviesApi(): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    fun datesApi(): DatesApi {
        return retrofit.create(DatesApi::class.java)
    }

    fun moviesComingSoonApi(): MoviesComingSoonApi {
        return retrofit.create(MoviesComingSoonApi::class.java)
    }

    fun seatsApi(): SeatsApi {
        return retrofit.create(SeatsApi::class.java)
    }

    fun postTicketApi(): PostTicketApi {
        return retrofit.create(PostTicketApi::class.java)
    }

    fun getTicketApi(): GetTicketApi {
        return retrofit.create(GetTicketApi::class.java)
    }

    companion object {
        private const val BASE_URL = "http://192.168.1.16:3000/"

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

}