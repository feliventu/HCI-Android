package com.example.homechan.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homechan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(options: Array<String> = arrayOf(stringResource(id = R.string.no_data)),
                   selectedOption: String = options[0],
                   onSelectionChange: (String) -> Unit,
                   ) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedOption) }

    val interactionSource = remember { MutableInteractionSource() }


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
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.surface),
                color = MaterialTheme.colorScheme.inversePrimary,

                ) {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors =  OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.menuAnchor(),
                    textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                )
            }

            ExposedDropdownMenu(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
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
                            onSelectionChange(item)
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        },
                        colors = MenuItemColors(
                            textColor = MaterialTheme.colorScheme.primary,
                            leadingIconColor = MaterialTheme.colorScheme.primary,
                            trailingIconColor = MaterialTheme.colorScheme.primary,
                            disabledTextColor = MaterialTheme.colorScheme.primary,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
                        ),

                    )
                }}
            }
        }
    }
}
