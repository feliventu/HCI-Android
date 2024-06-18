package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Status


object RemoteStatus {
    const val ON = "on"
    const val OFF = "off"

    fun asModel(status: String): Status {
        return when (status) {
            ON -> Status.ON
            else -> Status.OFF
        }
    }
}