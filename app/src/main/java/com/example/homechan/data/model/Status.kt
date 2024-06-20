package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteStatus


enum class Status {
    ON, OFF, PLAYING, STOPPED, PAUSED, OPENING, CLOSING, CLOSED, OPENED, ARMED_AWAY, ARMED_STAY, DISARMED, UNKNOWN;

    companion object {
        fun asRemoteModel(value: Status): String {
            return when (value) {
                ON -> RemoteStatus.ON
                OFF -> RemoteStatus.OFF
                PLAYING -> RemoteStatus.PLAYING
                STOPPED -> RemoteStatus.STOPPED
                PAUSED -> RemoteStatus.PAUSED
                OPENING -> RemoteStatus.OPENING
                CLOSING -> RemoteStatus.CLOSING
                CLOSED -> RemoteStatus.CLOSED
                OPENED -> RemoteStatus.OPENED
                ARMED_AWAY -> RemoteStatus.ARMED_AWAY
                ARMED_STAY -> RemoteStatus.ARMED_STAY
                DISARMED -> RemoteStatus.DISARMED
                else -> RemoteStatus.UNKNOWN
            }
        }
    }
}