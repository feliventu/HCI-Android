package com.example.homechan.data.remote.model


import com.example.homechan.data.model.Device
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class RemoteDevice<T> where T : Any {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("type")
    @Expose(serialize = false)
    lateinit var type: RemoteDeviceType


    @Expose(serialize = false)
    lateinit var state: T
        private set

    @SerializedName("meta")
    var meta: Any? = null

    fun setState(state: T) {
        this.state = state
    }

    abstract fun asModel(): Device
}