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

    companion object {
        const val PLAY_ACTION = "play"
        const val STOP_ACTION = "stop"
        const val SET_VOLUME_ACTION = "setVolume"
        const val PAUSE_ACTION = "pause"
        const val NEXT_SONG_ACTION = "nextSong"
        const val PREVIOUS_SONG_ACTION = "previousSong"
        const val SET_GENRE_ACTION = "setGenre"
    }


}