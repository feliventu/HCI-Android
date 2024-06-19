package com.example.homechan.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homechan.data.DataSourceException
import com.example.homechan.data.model.Blinds
import com.example.homechan.data.model.Error
import com.example.homechan.data.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlindsViewModel(private val repository: DeviceRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(BlindsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.currentDevice
        ) { state, response ->
            if (response is Blinds) {
                state.copy(currentDevice = response)
            } else {
                // Handle the case where response is not a Speaker
                state
            }
        }
    }



    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (BlindsUiState, T) -> BlindsUiState
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
        updateState: (BlindsUiState, R) -> BlindsUiState
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