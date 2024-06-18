package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteDevice
import com.example.homechan.data.remote.model.RemoteSpeaker
import com.example.homechan.data.remote.model.RemoteSpeakerState

class Speaker(
    id: String?,
    name: String,
    val status: Status,
    val volume: Int,
    val genre: String,

    ) : Device(id, name, DeviceType.SPEAKER) {
    override fun asRemoteModel(): RemoteDevice<RemoteSpeakerState> {
        val state = RemoteSpeakerState()
        state.status = Status.asRemoteModel(status)
        state.volume = volume
        state.genre = genre

        val model = RemoteSpeaker()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }


}