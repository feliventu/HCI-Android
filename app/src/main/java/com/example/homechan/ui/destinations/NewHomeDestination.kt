package com.example.homechan.ui.destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homechan.R
import com.example.homechan.ui.components.CustomOutlinedButton
import com.example.homechan.ui.components.CustomTextField

@Composable
fun NewHomeDestination() {
    Column(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {
        Text(text = stringResource(id = R.string.new_home), fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 0.dp)
                .padding(top = 15.dp))

        CustomTextField(stringResource(id = R.string.name))
        CustomOutlinedButton(onClick = { /*TODO*/}, stringResource(id = R.string.create))
    }





}




