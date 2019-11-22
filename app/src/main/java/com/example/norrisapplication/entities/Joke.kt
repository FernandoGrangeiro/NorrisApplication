package com.example.norrisapplication.entities

import com.google.gson.annotations.SerializedName

data class Joke(
    val id: String,
    val url: String,
    val value: String,
    @SerializedName("icon_url")
    val iconUrl: String
)