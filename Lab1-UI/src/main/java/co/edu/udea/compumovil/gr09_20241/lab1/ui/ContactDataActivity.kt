package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource
import co.edu.udea.compumovil.gr09_20241.lab1.data.DataSource.latamCities
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState
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
        ContactDataLandscape()
    }
}

@Composable
fun ContactDataPortrait(
    formUiState: FormUiState,
    dataViewModel: DataViewModel,
    onNextButtonClicked: () -> Unit
){
    val context = LocalContext.current

    // Spinner de ciudades
    val citiesForSelectedCountry by remember(formUiState.country) {
        derivedStateOf {
            latamCities.filter { it.first == formUiState.country }
                .map { it.second }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
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
                keyboardType = KeyboardType.Phone
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
                keyboardType = KeyboardType.Email
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
            onValueChange = { dataViewModel.setCity(it) },
        )

        // Address
        OutlinedTextField(
            value = formUiState.address,
            onValueChange = { dataViewModel.setAddress(it) },
            label = { Text(text = stringResource(R.string.address)) },
            leadingIcon =  {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            },
            singleLine = true
        )

        // Next Button
        Button(onClick = onNextButtonClicked ) {
            Row {
                Text(text = stringResource(R.string.next))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")
            }
        }
    }
}

@Composable
fun ContactDataLandscape(){
    Text(text = "Hola")
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