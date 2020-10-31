package com.jun.slice.model.Repository

import com.jun.slice.model.Networking.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getTweets() = apiHelper.getTweets()
}