package com.jun.slice.model.Networking

class ApiHelper(private val api: Api) {

    suspend fun getTweets() = api.getTweets()

}