package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LightGray01

@Composable
fun NewHomeDestination() {
    Column(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {
        Text(text = "New Home", fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 0.dp)
                .padding(top = 15.dp))

        SimpleOutlinedTextFieldSample()
        OutlinedButtonExample(onClick = { /*TODO*/})
    }





}


@Composable
fun SimpleOutlinedTextFieldSample() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Name") },
        shape = RoundedCornerShape(10.dp),
    )
}


@Composable
fun OutlinedButtonExample(onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, LightGray01)) {
        Text("CREATE", color = Color.Black, style = TextStyle(fontWeight = FontWeight.Bold))

    }
}