package com.example.myapplication.model

import com.example.myapplication.remote.model.RemoteDevice


abstract class Device(
    val id: String?,
    val name: String,
    val type: DeviceType
) {
    abstract fun asRemoteModel(): RemoteDevice<*>
}