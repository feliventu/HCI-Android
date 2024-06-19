package com.example.homechan.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteAcState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("temperature")
    var temperature: Int = 0

    @SerializedName("mode")
    lateinit var mode: String

    @SerializedName("horizontalSwing")
    lateinit var horizontalSwing: String

    @SerializedName("verticalSwing")
    lateinit var verticalSwing: String

    @SerializedName("fanSpeed")
    lateinit var fanSpeed: String
}