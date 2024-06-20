package com.example.homechan.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteAlarmState {
    @SerializedName("status")
    lateinit var status: String
}