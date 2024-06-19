package com.example.homechan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homechan.R
import com.example.homechan.data.model.Device
import com.example.homechan.data.model.DeviceType
import com.example.homechan.data.model.Lamp
import com.example.homechan.data.model.Speaker
import com.example.homechan.data.model.Status
import com.example.homechan.ui.devices.SpeakerViewModel
import com.example.homechan.ui.getViewModelFactory
import kotlinx.coroutines.launch

enum class SpeakerDialogState {
    MAIN_DIALOG,
    VOLUME_DIALOG,
    GENRE_DIALOG // Add more dialog states as needed
}

@Composable
fun SpeakerDialog(
    onDismissRequest: () -> Unit,
    device: Device,
    snackbarHostState: SnackbarHostState = SnackbarHostState()
) {

    val dialogState = rememberSaveable { mutableStateOf(SpeakerDialogState.MAIN_DIALOG) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            when (dialogState.value) {
                SpeakerDialogState.MAIN_DIALOG -> MainDialog(dialogState, snackbarHostState, device)
                SpeakerDialogState.VOLUME_DIALOG -> VolumeDialog(
                    dialogState,
                    snackbarHostState,
                    device
                )

                SpeakerDialogState.GENRE_DIALOG -> GenreDialog(
                    dialogState,
                    snackbarHostState,
                    device
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
internal fun GenreDialog(
    dialogState: MutableState<SpeakerDialogState>,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    device: Device
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val speakerViewModel: SpeakerViewModel = viewModel(factory = getViewModelFactory())
        val uiSpeakerState by speakerViewModel.uiState.collectAsState()
        val speaker = device as Speaker
        uiSpeakerState.currentDevice = speaker

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = SpeakerDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )


        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 15.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {

            var supportedGenres = arrayOf(
                R.string.pop,
                R.string.rock,
                R.string.classical,
                R.string.country,
                R.string.dance,
                R.string.latina
            )

            var supportedGenresAux = arrayOf(
                "pop",
                "rock",
                "classical",
                "country",
                "dance",
                "latina"
            )

            CustomDropdown(supportedGenresAux)


        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),

            ) {
            Text(text = stringResource(id = R.string.genre))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = speaker.genre,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )


        }
    }
}

@Composable
internal fun VolumeDialog(
    dialogState: MutableState<SpeakerDialogState>,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    device: Device
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val speakerViewModel: SpeakerViewModel = viewModel(factory = getViewModelFactory())
        val uiSpeakerState by speakerViewModel.uiState.collectAsState()
        val speaker = device as Speaker
        uiSpeakerState.currentDevice = speaker

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = SpeakerDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )


        }

        Row(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 15.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            val snackbarLabel = stringResource(R.string.volume_updated)
            Box(modifier = Modifier.padding(start = 50.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = {
                        speakerViewModel.setVolume(speaker.volume + 1)
                        scope.launch {
                            snackbarHostState.showSnackbar(snackbarLabel, withDismissAction = true)
                        }
                    },
                    icon = Icons.Filled.Add,
                    enabled = speaker.volume != 10,

                    )
            }
            Box(modifier = Modifier.padding(end = 50.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = {
                        speakerViewModel.setVolume(speaker.volume - 1)
                        scope.launch {
                            snackbarHostState.showSnackbar(snackbarLabel, withDismissAction = true)
                        }
                    },
                    icon = ImageVector.vectorResource(R.drawable.ic_minus),
                    enabled = speaker.volume != 0
                )
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),
        ) {
            Text(text = stringResource(id = R.string.volume))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = speaker.volume.toString(),
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )

        }
    }

}

@Composable
internal fun MainDialog(
    dialogState: MutableState<SpeakerDialogState>,
    snackbarHostState: SnackbarHostState = SnackbarHostState(), device: Device
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val speakerViewModel: SpeakerViewModel = viewModel(factory = getViewModelFactory())
        val uiSpeakerState by speakerViewModel.uiState.collectAsState()
        val speaker = device as Speaker
        uiSpeakerState.currentDevice = speaker

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_speaker),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = device.name,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            val snackbarLabel = stringResource(R.string.device_on)

            var switchState by remember { mutableStateOf(uiSpeakerState.currentDevice?.status != Status.STOPPED) }
            LaunchedEffect(uiSpeakerState.currentDevice?.status) {
                switchState = uiSpeakerState.currentDevice?.status != Status.STOPPED
            }

//            Switch(
//                checked = switchState,
//                onCheckedChange = { isChecked ->
//                    switchState = isChecked
//                    if (isChecked) {
//                        if (device.type == DeviceType.SPEAKER) {
//                            uiSpeakerState.currentDevice = device as Speaker
//                            speakerViewModel.play()
//                        }
//
//
//                    } else {
//
//                        if (device.type == DeviceType.SPEAKER) {
//                            uiSpeakerState.currentDevice = device as Speaker
//                            speakerViewModel.stop()
//                        }
//
//
//                    }
//
//                    if (switchState) {
//                        scope.launch {
//                            snackbarHostState.showSnackbar(
//                                snackbarLabel,
//                                withDismissAction = true
//                            )
//                        }
//                    }
//
//
//                },
//                colors = SwitchDefaults.colors(
//                    checkedThumbColor = Color.White,
//                    checkedTrackColor = MaterialTheme.colorScheme.secondary,
//                    uncheckedThumbColor = Color.White,
//                    uncheckedTrackColor = Color.LightGray,
//                    uncheckedBorderColor = Color.LightGray
//                ),)
        }
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 15.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedButtonIcon(
                onClick = { speakerViewModel.previousSong() },
                icon = ImageVector.vectorResource(R.drawable.ic_skip),
                enabled = speaker.status != Status.STOPPED
            )
            CustomOutlinedButtonIcon(
                onClick = { speakerViewModel.play() },
                icon = ImageVector.vectorResource(R.drawable.ic_play),
                enabled = speaker.status != Status.STOPPED && speaker.status != Status.PLAYING
            )
            CustomOutlinedButtonIcon(
                onClick = { speakerViewModel.pause() },
                icon = ImageVector.vectorResource(R.drawable.ic_pause),
                enabled = speaker.status != Status.STOPPED && speaker.status != Status.PAUSED
            )
            CustomOutlinedButtonIcon(
                onClick = { speakerViewModel.nextSong() },
                icon = ImageVector.vectorResource(R.drawable.ic_skip2),
                enabled = speaker.status != Status.STOPPED
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),
        ) {
            Text(text = stringResource(id = R.string.song))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Taylor swift",
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp),

            ) {
            Text(text = stringResource(id = R.string.volume))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = speaker.volume.toString(),
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
            CustomButtonArrowMini { dialogState.value = SpeakerDialogState.VOLUME_DIALOG }
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp),
        ) {
            Text(text = stringResource(id = R.string.genre))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = speaker.genre,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
            CustomButtonArrowMini { dialogState.value = SpeakerDialogState.GENRE_DIALOG }
        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun SpeakerDialogPreview() {
//    SpeakerDialog(
//        onDismissRequest = { },
//    )
//}

