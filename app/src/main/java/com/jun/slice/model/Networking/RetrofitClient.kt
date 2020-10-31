package com.jun.slice.model.Networking

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object RetrofitClient{

    private const val BASE_URL = "https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val api:Api= getRetrofit().create(Api::class.java)

}