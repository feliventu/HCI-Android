package com.example.myapplication.remote.model

import com.google.gson.annotations.SerializedName

class RemoteLampState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("color")
    lateinit var color: String

    @SerializedName("brightness")
    var brightness: Int = 0
}