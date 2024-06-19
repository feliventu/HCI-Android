package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Ac

class RemoteAc : RemoteDevice<RemoteAcState>(){
    override fun asModel(): Ac {
        return Ac(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            temperature = state.temperature,
            mode = state.mode,
            horizontalSwing = state.horizontalSwing,
            verticalSwing = state.verticalSwing,
            fanSpeed = state.fanSpeed
        )
    }
}