package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R


enum class AlarmDialogState {
    PASS_DIALOG,
    MAIN_DIALOG,

}

@Preview(showBackground = true)
@Composable
fun AlarmDialog(
    onDismissRequest: () -> Unit = {},
    id: String = null.toString(),

    ) {
    val dialogState = rememberSaveable { mutableStateOf(AlarmDialogState.PASS_DIALOG) }
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
                AlarmDialogState.MAIN_DIALOG -> MainDialog(dialogState)
                AlarmDialogState.PASS_DIALOG -> PassDialog(dialogState)

            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
internal fun PassDialog(dialogState: MutableState<AlarmDialogState>) {
    // Main dialog content
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = "Alarm",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomTextField(
                label = "Password", PasswordVisualTransformation(),
                KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomOutlinedButton(
                label = stringResource(id = R.string.enter),
                onClick = { dialogState.value = AlarmDialogState.MAIN_DIALOG },
            )
        }

    }
}

@Composable
internal fun MainDialog(dialogState: MutableState<AlarmDialogState>) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = "Alarm",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }


        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 10.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedButton(
                label = stringResource(id = R.string.armaway),
                onClick = { },
            )
            CustomOutlinedButton(
                label = stringResource(id = R.string.armstay),
                onClick = { },
            )


        }
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedButton(
                label = stringResource(id = R.string.disarm),
                onClick = { },
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp),
        ) {
            Text(text = stringResource(id = R.string.status))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Active",
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

    }
}




