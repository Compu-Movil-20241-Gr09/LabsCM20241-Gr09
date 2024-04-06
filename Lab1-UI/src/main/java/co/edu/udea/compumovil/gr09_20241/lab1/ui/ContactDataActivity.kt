package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource.latamCities
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState
import co.edu.udea.compumovil.gr09_20241.lab1.logUserData
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.AutoCompleteTextField
import co.edu.udea.compumovil.gr09_20241.lab1.ui.components.TextFieldSpinnerSelector

@Composable
fun ContactDataScreen(
    dataViewModel: DataViewModel = viewModel(),
    onNextButtonClicked: () -> Unit
){
    val formUiState by dataViewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        ContactDataPortrait(
            formUiState,
            dataViewModel,
            onNextButtonClicked
        )
    }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        ContactDataLandscape(
            formUiState,
            dataViewModel,
            onNextButtonClicked
        )
    }
}

@Composable
fun ContactDataPortrait(
    formUiState: FormUiState,
    dataViewModel: DataViewModel,
    onNextButtonClicked: () -> Unit
){
    val context = LocalContext.current

    // Cities filtered by country
    val citiesForSelectedCountry by remember(formUiState.country) {
        derivedStateOf {
            latamCities.filter { it.first == formUiState.country }
                .map { it.second }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Phone Number*
        OutlinedTextField(
            value = formUiState.phoneNumber,
            onValueChange = { dataViewModel.setPhoneNumber(it) },
            label = { Text(text = stringResource(R.string.phone_number) + "*") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        // E-mail*
        OutlinedTextField(
            value = formUiState.email,
            onValueChange = { dataViewModel.setEmail(it) },
            label = { Text(text = stringResource(R.string.email) + "*") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        // Country*
        AutoCompleteTextField(
            label = stringResource(R.string.country) + "*",
            imageVector = Icons.Default.Place,
            suggestions = DataSource.latamCountries,
            value = formUiState.country,
            onValueChange = { dataViewModel.setCountry(it) }
        )

        // City
        AutoCompleteTextField(
            label = stringResource(R.string.city),
            imageVector = Icons.Default.Place,
            suggestions = citiesForSelectedCountry,
            value = formUiState.city,
            onValueChange = { dataViewModel.setCity(it) }
        )

        // Address
        OutlinedTextField(
            value = formUiState.address,
            onValueChange = { dataViewModel.setAddress(it) },
            label = { Text(text = stringResource(R.string.address)) },
            leadingIcon =  {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
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
                enabled = isContactDataValid(formUiState)
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

fun isContactDataValid(formUiState: FormUiState): Boolean {
    return formUiState.phoneNumber.isNotBlank() &&
            formUiState.email.isNotBlank() &&
            formUiState.country.isNotBlank()
}

@Composable
fun ContactDataLandscape(
    formUiState: FormUiState,
    dataViewModel: DataViewModel,
    onNextButtonClicked: () -> Unit
) {
    val context = LocalContext.current

    // Cities filtered by country
    val citiesForSelectedCountry by remember(formUiState.country) {
        derivedStateOf {
            latamCities.filter { it.first == formUiState.country }
                .map { it.second }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Row for Phone Number and E-mail
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Phone Number*
            OutlinedTextField(
                value = formUiState.phoneNumber,
                onValueChange = { dataViewModel.setPhoneNumber(it) },
                label = { Text(text = stringResource(R.string.phone_number) + "*") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            // E-mail*
            OutlinedTextField(
                value = formUiState.email,
                onValueChange = { dataViewModel.setEmail(it) },
                label = { Text(text = stringResource(R.string.email) + "*") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
        }

        // Row for Country and City
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Country*
            AutoCompleteTextField(
                label = stringResource(R.string.country) + "*",
                imageVector = Icons.Default.Place,
                suggestions = DataSource.latamCountries,
                value = formUiState.country,
                onValueChange = { dataViewModel.setCountry(it) }
            )

            // City
            AutoCompleteTextField(
                label = stringResource(R.string.city),
                imageVector = Icons.Default.Place,
                suggestions = citiesForSelectedCountry,
                value = formUiState.city,
                onValueChange = { dataViewModel.setCity(it) }
            )
        }



        // Address
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formUiState.address,
            onValueChange = { dataViewModel.setAddress(it) },
            label = { Text(text = stringResource(R.string.address)) },
            leadingIcon =  {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        Text(
            modifier = Modifier.padding(20.dp),
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



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactScreenPreview(){
    val dataViewModel = DataViewModel()
    val formUiState by dataViewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()
    MaterialTheme {
        ContactDataPortrait(
            formUiState,
            dataViewModel,
            onNextButtonClicked = {
                navController.navigate("contactData")
            }
        )
    }
}