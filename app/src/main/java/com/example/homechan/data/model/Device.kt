package com.example.homechan.data.model

import com.example.homechan.data.remote.model.RemoteDevice


abstract class Device(
    val id: String?,
    val name: String,
    val type: DeviceType,
    val statusAux: Status
) {
    abstract fun asRemoteModel(): RemoteDevice<*>
}