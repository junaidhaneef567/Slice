package com.jun.slice.model.Networking

import com.jun.slice.model.data_model.Tweet_JSON
import retrofit2.http.GET

interface Api {

    @GET("tweets")
    suspend fun getTweets(): Tweet_JSON //api call for tweets
}