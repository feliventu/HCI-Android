package com.example.myapplication.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuItemColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LightGray01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(options: Array<String> = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(options[0]) }



    Box(
        modifier = Modifier
            .width(200.dp)
            .height(65.dp)
            .padding(top = 15.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            Surface(
                shape = RoundedCornerShape(13.dp),
                border = BorderStroke(3.dp, LightGray01),
                color = Color.White,

                ) {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors =  OutlinedTextFieldDefaults.colors(),
                    modifier = Modifier.menuAnchor(),
                    textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                )
            }

            ExposedDropdownMenu(
                containerColor = Color.White,
                expanded = expanded,
                onDismissRequest = { expanded = false },

                ) {
                options.forEach { item ->
                    if (item != selectedText){
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        },
                        colors = MenuItemColors(
                            textColor = Color.Black,
                            leadingIconColor = Color.Black,
                            trailingIconColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLeadingIconColor = Color.Black,
                            disabledTrailingIconColor = Color.Black,
                        )
                    )
                }}
            }
        }
    }
}
