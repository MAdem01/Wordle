package com.example.wordle.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WordApiService {
    @GET("word")
    suspend fun getRandomWord(@Query("length") length: Int = 5): List<String>
}