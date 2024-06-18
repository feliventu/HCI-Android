package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Speaker

class RemoteSpeaker : RemoteDevice<RemoteSpeakerState>(){
    override fun asModel(): Speaker {
        return Speaker(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            volume = state.volume,
            genre = state.genre
        )
    }
}