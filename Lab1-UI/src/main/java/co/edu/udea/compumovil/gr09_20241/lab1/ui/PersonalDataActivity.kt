package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.BasicTextField
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.LabelledDatePicker
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.LabelledRadioGroup


@Composable
fun PersonalDataScreen(
    dataViewModel: DataViewModel = viewModel()
){
    val formUiState by dataViewModel.uiState.collectAsState()

    // Configuration check
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        PersonalDataPortrait(
            formUiState,
            dataViewModel
        )
    }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        PersonalDataLandscape()
    }
}

@Composable
fun PersonalDataPortrait(
    formUiState: FormUiState,
    dataViewModel: DataViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(2.dp)
    ) {
        // Header
        Text(text = stringResource(R.string.personal_information))

        // Name Input
        BasicTextField(
            value = formUiState.name,
            onValueChange = { dataViewModel.setName(it) },
            label = stringResource(R.string.name),
            imageVector = Icons.Default.Person,
            readOnly = false
        )

        // Last Name Input
        BasicTextField(
            value = formUiState.lastName,
            onValueChange = { dataViewModel.setLastName(it) },
            label = stringResource(R.string.last_name),
            imageVector = Icons.Default.Person,
            readOnly = false
        )

        //  RadioGroup for Gender
        LabelledRadioGroup(
            label = stringResource(R.string.gender),
            items = DataSource.genderOptions.map { id -> context.resources.getString(id) },
            selection = formUiState.gender,
            onItemClick =   { dataViewModel.setGender(it)}
        )

        LabelledDatePicker(
            value = formUiState.birth,
            onValueChange = { dataViewModel.setBirth(it) }
        )

    }
}

@Composable
fun PersonalDataLandscape(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalScreenPreview(){
    val dataViewModel = DataViewModel()
    val formUiState by dataViewModel.uiState.collectAsState()
    MaterialTheme {
        PersonalDataPortrait(
            formUiState,
            dataViewModel
        )
    }
}

@Preview
@Composable
fun PersonalLandscapePreview(){
    MaterialTheme {
        PersonalDataLandscape()
    }
}