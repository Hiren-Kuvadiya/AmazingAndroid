package com.test.myapplication.model

import com.google.gson.annotations.SerializedName


class NewsResponse {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("totalResults")
    var totalResults: Int = -1

    @SerializedName("articles")
    lateinit var articles: ArrayList<Artical>

}

