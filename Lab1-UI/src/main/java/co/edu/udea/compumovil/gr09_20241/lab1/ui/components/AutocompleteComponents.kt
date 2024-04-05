package co.edu.udea.compumovil.gr09_20241.lab1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AutoCompleteTextField(
    suggestions: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imageVector: ImageVector
){
    var expanded by remember { mutableStateOf(false) }

    Column{
        OutlinedTextField(
            modifier = Modifier
                .onFocusChanged { focusState ->
                    expanded = focusState.isFocused
                },
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            label = { Text(text = label) },
            singleLine = true,
            leadingIcon = { Icon(imageVector = imageVector, contentDescription = "") },
            enabled = suggestions.isNotEmpty()
        )

        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)
            ) {
                suggestions.filter { it.contains(value, ignoreCase = true) }
                    .forEach { suggestion ->
                        DropdownMenuItem(
                            text= { Text(
                                text = suggestion,
                                color = MaterialTheme.colorScheme.background
                            ) },
                            onClick = {
                                onValueChange(suggestion)
                                expanded = false
                            }
                        )
                    }
            }
        }
    }
}