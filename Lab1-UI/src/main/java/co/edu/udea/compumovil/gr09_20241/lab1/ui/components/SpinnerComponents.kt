package co.edu.udea.compumovil.gr09_20241.lab1.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource

@Composable
fun SpinnerSelection (
    items: List<String>,
    selection: String,
    onSelectionChange: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = !expanded }){
            if (selection.equals("")){
                Text (stringResource(R.string.select) + "...")
            }else{
                Text (selection)
            }

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                           Text(text = item)
                    },
                    onClick = {
                        expanded = false
                        onSelectionChange(item)
                    }
                )
            }
        }
    }
}

@Composable
fun LabelledSpinnerSelector(
    label: String,
    items: List<String>,
    selection: String,
    onSelectionChange: (String) -> Unit,
    imageVector: ImageVector
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(imageVector= imageVector, contentDescription = "")
        Text(text = label)
        SpinnerSelection(
            items = items,
            selection = selection,
            onSelectionChange = onSelectionChange
        )
    }
}