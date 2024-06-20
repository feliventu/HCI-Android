package com.example.homechan.data.remote.model

import com.example.homechan.data.model.Alarm

class RemoteAlarm : RemoteDevice<RemoteAlarmState>() {
    override fun asModel(): Alarm {
        return Alarm(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status)
        )
    }
}