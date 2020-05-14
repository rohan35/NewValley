package com.raydevelopers.newvalley.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkService {
    @GET
    suspend fun getRequest(@Url var1: String): Any
}