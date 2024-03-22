package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.BasicTextField
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.HeaderText
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.LabelledDatePicker
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.LabelledRadioGroup



@Composable
fun PersonalDataScreen(
    viewModel: DataViewModel
){
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        PersonalDataPortrait()
    }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        PersonalDataLandscape()
    }
}

@Composable
fun PersonalDataPortrait() {
    val genderOptions = listOf(
        stringResource(R.string.male),
        stringResource(R.string.female),
        stringResource(R.string.other)
    )

    val scholarityLevel = listOf(
        stringResource(R.string.primary),
        stringResource(R.string.secondary),
        stringResource(R.string.university),
        stringResource(R.string.other),
    )

    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(genderOptions.first()) }
    var dateOfBirth by remember { mutableStateOf("") }
    var scholarity by remember { mutableStateOf(scholarityLevel.first()) }

    Column (

    ) {
        // Header
        HeaderText(label = stringResource(R.string.personal_information))

        // Name Input
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            label = stringResource(R.string.name),
            imageVector = Icons.Default.Person,
            readOnly = false
        )

        // Last Name Input
        BasicTextField(
            value = lastname,
            onValueChange = { lastname = it },
            label = stringResource(R.string.last_name),
            imageVector = Icons.Default.Person,
            readOnly = false
        )

        //  RadioGroup for Gender
        LabelledRadioGroup(
            label = stringResource(R.string.gender),
            items = genderOptions,
            selection = gender,
            onItemClick =   { clickedItem ->
                gender = clickedItem
            }
        )

        LabelledDatePicker(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it }
        )

    }
}

@Composable
fun PersonalDataLandscape(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalScreenPreview(){
    MaterialTheme {
        PersonalDataPortrait()
    }
}

@Preview
@Composable
fun PersonalLandscapePreview(){
    MaterialTheme {
        PersonalDataLandscape()
    }
}