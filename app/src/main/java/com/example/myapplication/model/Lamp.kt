package com.example.myapplication.model

import com.example.myapplication.remote.model.RemoteDevice
import com.example.myapplication.remote.model.RemoteLamp
import com.example.myapplication.remote.model.RemoteLampState


class Lamp(
    id: String?,
    name: String,
    val status: Status,
    val color: String,
    val brightness: Int
) : Device(id, name, DeviceType.LAMP) {

    override fun asRemoteModel(): RemoteDevice<RemoteLampState> {
        val state = RemoteLampState()
        state.status = Status.asRemoteModel(status)
        state.color = color
        state.brightness = brightness

        val model = RemoteLamp()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }

    companion object {
        const val TURN_ON_ACTION = "turnOn"
        const val TURN_OFF_ACTION = "turnOff"
    }
}