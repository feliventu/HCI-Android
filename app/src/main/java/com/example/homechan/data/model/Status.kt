package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteStatus


enum class Status {
    ON, OFF, PLAYING, STOPPED, UNKNOWN;

    companion object {
        fun asRemoteModel(value: Status): String {
            return when (value) {
                ON -> RemoteStatus.ON
                OFF -> RemoteStatus.OFF
                PLAYING -> RemoteStatus.PLAYING
                STOPPED -> RemoteStatus.STOPPED
                else -> RemoteStatus.UNKNOWN
            }
        }
    }
}