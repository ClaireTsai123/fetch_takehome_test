package com.example.fetchtakehometest.data.network

import com.example.fetchtakehometest.data.Item
import retrofit2.http.GET

interface ItemApiService {
    @GET("/hiring.json")
    suspend fun getItems(): List<Item>
}