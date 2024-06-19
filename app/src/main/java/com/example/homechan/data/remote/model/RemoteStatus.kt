package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Status


object RemoteStatus {
    const val ON = "on"
    const val OFF = "off"
    const val STOPPED = "stopped"
    const val PLAYING = "playing"
    const val PAUSED = "paused"
    const val UNKNOWN = "unknown"


    fun asModel(status: String): Status {
        return when (status) {
            ON -> Status.ON
            OFF -> Status.OFF
            STOPPED -> Status.STOPPED
            PLAYING -> Status.PLAYING
            PAUSED -> Status.PAUSED
            else -> Status.UNKNOWN
        }
    }
}