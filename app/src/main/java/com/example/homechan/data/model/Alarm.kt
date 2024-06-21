package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteAlarm
import com.example.homechan.data.remote.model.RemoteAlarmState
import com.example.homechan.data.remote.model.RemoteDevice

class Alarm(
    id: String?,
    name: String,
    val status: Status,
) : Device(id, name, DeviceType.ALARM,status) {

    override fun asRemoteModel() : RemoteDevice<RemoteAlarmState> {
        val state = RemoteAlarmState()
        state.status = Status.asRemoteModel(status)

        val model = RemoteAlarm()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }

    companion object {
        const val DISARM_ACTION = "disarm"
        const val ARM_AWAY_ACTION = "armAway"
        const val ARM_STAY_ACTION = "armStay"
    }

}