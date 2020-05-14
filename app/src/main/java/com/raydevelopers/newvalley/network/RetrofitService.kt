package com.raydevelopers.newvalley.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        private var mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        /**
         * calling create service method of retroft to create the retrofit service
         * @param serviceClass - service class interface
         */

        val networkService = mRetrofit.create(NetworkService::class.java)
    }



}