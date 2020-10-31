package com.jun.slice.model.data_model

data class Tweet_data(
    val  name:String,
    val handle:String,
    val favoriteCount:Int,
    val retweetCount:Int,
    val profileImageUrl:String,
    val text:String
)