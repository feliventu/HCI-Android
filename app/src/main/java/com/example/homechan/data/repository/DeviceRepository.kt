package com.example.homechan.data.repository


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.homechan.NotificationHelper
import com.example.homechan.R
import com.example.homechan.data.model.Device
import com.example.homechan.data.model.Lamp
import com.example.homechan.data.remote.DeviceRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DeviceRepository(
    private val remoteDataSource: DeviceRemoteDataSource,
    private val context: Context
) {
    private var localDevices: List<Device> = emptyList()

    val devices: Flow<List<Device>> =
        remoteDataSource.devices
            .map { it.map { jt -> jt.asModel() } }
            .onEach { newDevices ->
                checkForDeviceStatusChanges(newDevices, context)
                localDevices = newDevices
            }
    val currentDevice = devices.map { it.firstOrNull { jt -> jt is Lamp } }

    suspend fun getDevice(deviceId: String): Device {
        return remoteDataSource.getDevice(deviceId).asModel()
    }

    suspend fun addDevice(device: Device): Device {
        return remoteDataSource.addDevice(device.asRemoteModel()).asModel()
    }

    suspend fun modifyDevice(device: Device): Boolean {
        return remoteDataSource.modifyDevice(device.asRemoteModel())
    }

    suspend fun deleteDevice(deviceId: String): Boolean {
        return remoteDataSource.deleteDevice(deviceId)
    }

    suspend fun executeDeviceAction(
        deviceId: String,
        action: String,
        parameters: Array<*> = emptyArray<Any>()
    ): Array<*> {
        return remoteDataSource.executeDeviceAction(deviceId, action, parameters)
    }


    private fun checkForDeviceStatusChanges(newDevices: List<Device>, context: Context) {
        for (newDevice in newDevices) {
            val localDevice = localDevices.find { it.id == newDevice.id }
            if (localDevice != null && localDevice.statusAux != newDevice.statusAux) {
                // Notify device status change
                val notificationHelper = NotificationHelper.getInstance(context)
                val notificationId = newDevice.id.hashCode()
                notificationHelper.sendNotification(id =notificationId,title = newDevice.name, message = context.getString(R.string.device_updated))
            }
        }
    }

}