package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Blinds

class RemoteBlinds : RemoteDevice<RemoteBlindsState>(){

    override fun asModel(): Blinds {
        return Blinds(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            level = state.level,
            currentLevel = state.currentLevel
        )
    }
}