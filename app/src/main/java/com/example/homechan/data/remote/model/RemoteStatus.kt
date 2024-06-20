package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Status


object RemoteStatus {
    const val ON = "on"
    const val OFF = "off"
    const val STOPPED = "stopped"
    const val PLAYING = "playing"
    const val PAUSED = "paused"
    const val OPENING = "opening"
    const val CLOSING = "closing"
    const val CLOSED = "closed"
    const val OPENED = "opened"
    const val ARMED_AWAY = "armedAway"
    const val ARMED_STAY = "armedStay"
    const val DISARMED = "disarmed"
    const val UNKNOWN = "unknown"


    fun asModel(status: String): Status {
        return when (status) {
            ON -> Status.ON
            OFF -> Status.OFF
            STOPPED -> Status.STOPPED
            PLAYING -> Status.PLAYING
            PAUSED -> Status.PAUSED
            OPENING -> Status.OPENING
            CLOSING -> Status.CLOSING
            CLOSED -> Status.CLOSED
            OPENED -> Status.OPENED
            ARMED_AWAY -> Status.ARMED_AWAY
            ARMED_STAY -> Status.ARMED_STAY
            DISARMED -> Status.DISARMED
            else -> Status.UNKNOWN
        }
    }
}