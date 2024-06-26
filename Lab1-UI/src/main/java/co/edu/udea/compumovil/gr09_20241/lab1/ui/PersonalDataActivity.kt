package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState
import co.edu.udea.compumovil.gr09_20241.lab1.logUserData
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.TextFieldDatePicker
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.LabelledRadioGroup
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.TextFieldSpinnerSelector

@Composable
fun PersonalDataScreen(

    dataViewModel: DataViewModel = viewModel(),
    onNextButtonClicked: () -> Unit
){
    val formUiState by dataViewModel.uiState.collectAsState()

    // Configuration check
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        PersonalDataPortrait(
            formUiState,
            dataViewModel,
            onNextButtonClicked
        )
    }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        PersonalDataLandscape(
            formUiState,
            dataViewModel,
            onNextButtonClicked
        )
    }
}

@Composable
fun PersonalDataPortrait(
    formUiState: FormUiState,
    dataViewModel: DataViewModel,
    onNextButtonClicked: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name Input
        OutlinedTextField(
            value = formUiState.name,
            onValueChange = { dataViewModel.setName(it) },
            label = { Text(text = stringResource(R.string.name) + "*") },
            leadingIcon =  {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        // Last Name Input
        OutlinedTextField(
            value = formUiState.lastName,
            onValueChange = { dataViewModel.setLastName(it) },
            label = { Text(text = stringResource(R.string.last_name) + "*") },
            leadingIcon =  {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        //  RadioGroup for Gender
        LabelledRadioGroup(
            imageVector = Icons.Default.Face,
            label = stringResource(R.string.gender),
            items = DataSource.genderOptions.map { id -> context.resources.getString(id) },
            selection = formUiState.gender,
            onItemClick =   { dataViewModel.setGender(it)}
        )

        // DatePicker for Birth date
        TextFieldDatePicker(
            label = stringResource(R.string.date_of_birth) + "*",
            value = formUiState.birth,
            onValueChange = { dataViewModel.setBirth(it) }
        )

        // School Grade Spinner
        TextFieldSpinnerSelector(
            label = stringResource(R.string.school_grade),
            imageVector = Icons.Default.Info,
            items = DataSource.scholarityLevels.map { id -> context.resources.getString(id) },
            selection = formUiState.scholarity,
            onSelectionChange = { dataViewModel.setScholarity(it) }
        )

        Text(
            modifier = Modifier
                .padding(20.dp),
            text = stringResource(R.string.fill_required_fields),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )

        // Next Button
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    logUserData(formUiState)
                    onNextButtonClicked()
                },
                enabled = isPersonalDataValid(formUiState)
            ) {
                Row {
                    Text(text = stringResource(R.string.next))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
fun PersonalDataLandscape(
    formUiState: FormUiState,
    dataViewModel: DataViewModel,
    onNextButtonClicked: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name & Last Name Inputs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = formUiState.name,
                onValueChange = { dataViewModel.setName(it) },
                label = { Text(text = stringResource(R.string.name) + "*") },
                leadingIcon =  {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
            OutlinedTextField(
                value = formUiState.lastName,
                onValueChange = { dataViewModel.setLastName(it) },
                label = { Text(text = stringResource(R.string.last_name) + "*") },
                leadingIcon =  {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
        }
        // Gender RadioGroup
        LabelledRadioGroup(
            imageVector = Icons.Default.Face,
            label = stringResource(R.string.gender),
            items = DataSource.genderOptions.map { id -> context.resources.getString(id) },
            selection = formUiState.gender,
            onItemClick =   { dataViewModel.setGender(it)}
        )

        // DatePicker for Birth date
        TextFieldDatePicker(
            label = stringResource(R.string.date_of_birth) + "*",
            value = formUiState.birth,
            onValueChange = { dataViewModel.setBirth(it) }
        )

        // School Grade Spinner
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            TextFieldSpinnerSelector(
                label = stringResource(R.string.school_grade),
                imageVector = Icons.Default.Info,
                items = DataSource.scholarityLevels.map { id -> context.resources.getString(id) },
                selection = formUiState.scholarity,
                onSelectionChange = { dataViewModel.setScholarity(it) }
            )
        }

        // Next Button
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    logUserData(formUiState)
                    onNextButtonClicked()
                },
                enabled = isPersonalDataValid(formUiState)
            ) {
                Row {
                    Text(text = stringResource(R.string.next))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = ""
                    )
                }
            }
        }

    }
}

fun isPersonalDataValid(formUiState: FormUiState): Boolean {
    return formUiState.name.isNotBlank() &&
            formUiState.lastName.isNotBlank() &&
            formUiState.birth.isNotBlank()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalScreenPreview(){
    val dataViewModel = DataViewModel()
    val formUiState by dataViewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()
    MaterialTheme {
        PersonalDataPortrait(
            formUiState,
            dataViewModel,
            onNextButtonClicked = {
                navController.navigate("contactData")
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalLandscapePreview(){
    val dataViewModel = DataViewModel()
    val formUiState by dataViewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()
    MaterialTheme {
        PersonalDataLandscape(
            formUiState,
            dataViewModel,
            onNextButtonClicked = {
                navController.navigate("contactData")
            }
        )
    }
}