package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Lamp

class RemoteLamp : RemoteDevice<RemoteLampState>() {

    override fun asModel(): Lamp {
        return Lamp(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            color = state.color,
            brightness = state.brightness
        )
    }
}