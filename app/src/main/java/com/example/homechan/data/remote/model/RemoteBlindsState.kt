package com.example.homechan.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteBlindsState {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("level")
    var level: Int = 0

    @SerializedName("currentLevel")
    var currentLevel: Int = 0

}