package com.example.homechan.ui.destinations

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.homechan.R

@Composable
fun MyRoutineDestination() {

    Text(text = "Routines")
    Text(text = stringResource(id = R.string.welcome))

}