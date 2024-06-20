package com.example.homechan.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homechan.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CustomTextField(
    label: String = "Name",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var text by remember { mutableStateOf("") }


    var searchText by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = searchText,
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        onValueChange = { newText -> searchText = newText },
        modifier = Modifier.width(250.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary), // Change the color here


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
                    text = label,
                )

            },


            container = {
                Container(
                    enabled = true,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,

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
fun CustomOutlinedButton(onClick: () -> Unit, label: String = "Button", enabled: Boolean = true, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.surface),
        enabled = enabled,
        modifier = modifier
        ) {
        Text(
            label.uppercase(Locale.getDefault()),
            color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,

            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }

}

@Composable
fun CustomOutlinedButtonIcon(
    onClick: () -> Unit = {},
    icon: ImageVector = Icons.Default.Done,
    enabled: Boolean = true,
) {


    IconButton(
        enabled = enabled,
        onClick = { onClick() },
        modifier = Modifier.border(
            4.dp,
            MaterialTheme.colorScheme.surface,
            RoundedCornerShape(25)
        ) // Add this line

    ) {
        Icon(imageVector = icon, contentDescription = "", modifier = Modifier.size(28.dp))
    }


}

@Preview(showBackground = true)
@Composable
fun CustomButtonArrowMini(onClick: () -> Unit = {}) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(25.dp) // Add this line

    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow),
            contentDescription = "", modifier = Modifier.size(28.dp),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun CustomButtonArrowMedium(onClick: () -> Unit = {}) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(25.dp) // Add this line

    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow2),
            contentDescription = "", modifier = Modifier.size(38.dp),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}