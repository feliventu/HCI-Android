package com.example.myapplication.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.LightGray01
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CustomTextField( label: String = "Name") {
    var text by remember { mutableStateOf("") }


    var searchText by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = searchText,
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(Color.White),
        onValueChange = { newText -> searchText = newText },
        modifier = Modifier.width(250.dp)

    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = searchText,
            innerTextField = innerTextField,
            enabled = true,
            singleLine = true,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = stringResource(R.string.name),)

            },
            container = {
                Container(
        enabled = true,
        isError = false,
        interactionSource = interactionSource,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = LightGray01,

        ),
        shape = RoundedCornerShape(13.dp),
        focusedBorderThickness = 4.dp,
        unfocusedBorderThickness = 3.dp,
    )
            }
        )
    }
}


@Composable
fun CustomOutlinedButton(onClick: () -> Unit , label: String = "Button") {
    OutlinedButton(onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, LightGray01),

    ) {
        Text(label, color = Color.Black, style = TextStyle(fontWeight = FontWeight.Bold))
    }
}
@Composable
fun CustomOutlinedButtonIcon(onClick: () -> Unit, icon: ImageVector) {

    OutlinedButton(onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, LightGray01),
        modifier = Modifier.width(50.dp).height(50.dp)
        ) {
        Icon(imageVector = icon, contentDescription ="")
    }
}