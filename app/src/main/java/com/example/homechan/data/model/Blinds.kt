package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteBlinds
import com.example.homechan.data.remote.model.RemoteBlindsState
import com.example.homechan.data.remote.model.RemoteDevice

class Blinds(
    id: String?,
    name: String,
    val status: Status,
    val level: Int,
    val currentLevel: Int,
): Device(id, name, DeviceType.BLINDS) {

    override fun asRemoteModel(): RemoteDevice<RemoteBlindsState> {
        val state = RemoteBlindsState()
        state.status = Status.asRemoteModel(status)
        state.level = level
        state.currentLevel = currentLevel

        val model = RemoteBlinds()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }

    companion object{
        const val CLOSE_ACTION = "close"
        const val OPEN_ACTION = "open"
        const val SET_LEVEL_ACTION = "setLevel"

    }

}