package co.edu.udea.compumovil.gr09_20241.lab1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BasicTextField(
    value: String,
    onValueChange: ((String) -> Unit),
    label: String,
    imageVector: ImageVector,
    readOnly: Boolean
){
    OutlinedTextField(
        modifier = Modifier
            .padding(2.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon =  {
            Icon(imageVector = imageVector, contentDescription = "")
        },
        readOnly = readOnly
    )
}