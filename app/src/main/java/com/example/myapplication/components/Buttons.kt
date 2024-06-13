package com.example.myapplication.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.LightGray01

@Composable
fun CustomTextField( label: String = "Name") {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        shape = RoundedCornerShape(10.dp),
    )
}


@Composable
fun CustomOutlinedButton(onClick: () -> Unit , label: String = "Button") {
    OutlinedButton(onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, LightGray01)
    ) {
        Text(label, color = Color.Black, style = TextStyle(fontWeight = FontWeight.Bold))

    }
}