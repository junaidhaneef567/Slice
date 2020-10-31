package com.jun.slice.model.data_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tweet_JSON {

    lateinit var success:String
    @SerializedName("data")
    @Expose
    val data: ArrayList<Tweet_data>? = null
}