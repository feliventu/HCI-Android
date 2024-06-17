package com.example.myapplication.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

@Composable
fun MyRoutineDestination() {

    Text(text = "Routines")
    Text(text = stringResource(id = R.string.welcome))

}