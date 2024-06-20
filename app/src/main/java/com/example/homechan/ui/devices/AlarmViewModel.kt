package com.example.homechan.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homechan.data.DataSourceException
import com.example.homechan.data.model.Alarm
import com.example.homechan.data.model.Error
import com.example.homechan.data.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlarmViewModel(private val repository: DeviceRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AlarmUiState())
    val uiState = _uiState.asStateFlow()

    init{
        collectOnViewModelScope(
            repository.currentDevice
        ) { state, response ->
            if (response is Alarm) {
                state.copy(currentDevice = response)
            } else {
                state
            }
        }
    }

    fun armAway() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Alarm.ARM_AWAY_ACTION, arrayOf(1234)) },
        { state, _ -> state }
    )

    fun armStay() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Alarm.ARM_STAY_ACTION, arrayOf(1234)) },
        { state, _ -> state }
    )

    fun disarm() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Alarm.DISARM_ACTION, arrayOf(1234)) },
        { state, _ -> state }
    )



    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (AlarmUiState, T) -> AlarmUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (AlarmUiState, R) -> AlarmUiState
    ) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiState.update { updateState(it, response).copy(loading = false) }
        }.onFailure { e ->
            _uiState.update { it.copy(loading = false, error = handleError(e)) }
        }
    }

}