package com.example.myapplication.components

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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.example.myapplication.R

enum class SpeakerDialogState {
    MAIN_DIALOG,
    VOLUME_DIALOG,
    GENRE_DIALOG // Add more dialog states as needed
}

@Composable
fun SpeakerDialog(
    onDismissRequest: () -> Unit,
    id: String = null.toString(),

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
                SpeakerDialogState.MAIN_DIALOG -> MainDialog(dialogState)
                SpeakerDialogState.VOLUME_DIALOG -> VolumeDialog(dialogState)
                SpeakerDialogState.GENRE_DIALOG -> GenreDialog(dialogState)
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
internal fun GenreDialog(dialogState: MutableState<SpeakerDialogState>) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonIconMedium { dialogState.value = SpeakerDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id =R.string.back),
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
            CustomDropdown( arrayOf("Genre 1", "Genre 2", "Genre 3"))



        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),

            ) {
            Text(text = stringResource(id = R.string.genre))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Pop", modifier = Modifier.padding(end = 10.dp), color = MaterialTheme.colorScheme.tertiary)


        }
    }
}

@Composable
internal fun VolumeDialog(dialogState: MutableState<SpeakerDialogState>) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonIconMedium { dialogState.value = SpeakerDialogState.MAIN_DIALOG }
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
            Box(modifier = Modifier.padding(start = 50.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = { /*TODO*/ },
                    icon = Icons.Filled.Add,

                    )
            }
            Box(modifier = Modifier.padding(end = 50.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = { /*TODO*/ },
                    icon = ImageVector.vectorResource(R.drawable.ic_minus),
                )
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),

            ) {
            Text(text = stringResource(id = R.string.volume))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "1", modifier = Modifier.padding(end = 10.dp), color = MaterialTheme.colorScheme.tertiary)

        }
    }

}

@Composable
internal fun MainDialog(dialogState: MutableState<SpeakerDialogState>) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_speaker),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = "Speaker",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            var checked by rememberSaveable { mutableStateOf(true) }
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.secondary,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedBorderColor = Color.LightGray
                ),
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedButtonIcon(
                onClick = { /*TODO*/ },
                icon = ImageVector.vectorResource(R.drawable.ic_skip),
            )
            CustomOutlinedButtonIcon(
                onClick = { /*TODO*/ },
                icon = ImageVector.vectorResource(R.drawable.ic_play),
            )
            CustomOutlinedButtonIcon(
                onClick = { /*TODO*/ },
                icon = ImageVector.vectorResource(R.drawable.ic_pause),
            )
            CustomOutlinedButtonIcon(
                onClick = { /*TODO*/ },
                icon = ImageVector.vectorResource(R.drawable.ic_skip2),
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
                .padding(top = 10.dp),

            ) {
            Text(text = stringResource(id = R.string.volume))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "1", modifier = Modifier.padding(end = 10.dp), color = MaterialTheme.colorScheme.tertiary)
            CustomButtonIconMini { dialogState.value = SpeakerDialogState.VOLUME_DIALOG }
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),
        ) {
            Text(text = stringResource(id = R.string.genre))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Pop", modifier = Modifier.padding(end = 10.dp), color = MaterialTheme.colorScheme.tertiary)
            CustomButtonIconMini { dialogState.value = SpeakerDialogState.GENRE_DIALOG }
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

