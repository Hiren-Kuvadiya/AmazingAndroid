package com.test.myapplication.model

import com.google.gson.annotations.SerializedName

class Artical {

    @SerializedName("author")
    lateinit var author: String

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("publishedAt")
    lateinit var publishedAt: String

    @SerializedName("content")
    lateinit var content: String

    @SerializedName("urlToImage")
    lateinit var urlToImage: String



}