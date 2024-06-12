package com.example.myapplication

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun MyRoutineDestination() {

    Text(text = "Routines")
    Text(text = stringResource(id =R.string.welcome))

}