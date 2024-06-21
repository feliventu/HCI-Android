package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteAc
import com.example.homechan.data.remote.model.RemoteAcState
import com.example.homechan.data.remote.model.RemoteDevice

class Ac(
    id: String?,
    name: String,
    val status: Status,
    val temperature: Int,
    val mode: String,
    val horizontalSwing: String,
    val verticalSwing: String,
    val fanSpeed: String,
    ) : Device(id, name, DeviceType.AC, status) {

        override fun asRemoteModel() : RemoteDevice<RemoteAcState> {
            val state = RemoteAcState()
            state.status = Status.asRemoteModel(status)
            state.temperature = temperature
            state.mode = mode
            state.horizontalSwing = horizontalSwing
            state.verticalSwing = verticalSwing
            state.fanSpeed = fanSpeed

            val model = RemoteAc()
            model.id = id
            model.name = name
            model.setState(state)
            return model
        }
    companion object {
        const val TURN_ON_ACTION = "turnOn"
        const val TURN_OFF_ACTION = "turnOff"
        const val SET_TEMPERATURE_ACTION = "setTemperature"
        const val SET_MODE_ACTION = "setMode"
        const val SET_HORIZONTAL_SWING_ACTION = "setHorizontalSwing"
        const val SET_VERTICAL_SWING_ACTION = "setVerticalSwing"
        const val SET_FAN_SPEED_ACTION = "setFanSpeed"
    }

    }
