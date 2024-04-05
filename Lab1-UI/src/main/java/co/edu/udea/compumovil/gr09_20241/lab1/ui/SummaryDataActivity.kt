package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState

@Composable
fun SummaryDataScreen(
    dataViewModel: DataViewModel = viewModel()
){
    val formUiState by dataViewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        SummaryDataPortrait(
            formUiState
        )
    }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        SummaryDataLandscape(
            formUiState
        )
    }
}

@Composable
fun SummaryDataPortrait(
    formUiState: FormUiState
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.personal_information),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${stringResource(R.string.name)}: ${formUiState.name}"
        )
        Text(
            text = "${stringResource(R.string.gender)}: ${formUiState.gender}"
        )
        Text(
            text = "${stringResource(R.string.date_of_birth)}: ${formUiState.birth}"
        )
        Text(
            text = "${stringResource(R.string.school_grade)}: ${formUiState.scholarity}"
        )

        Text(
            text = stringResource(R.string.contact_information),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${stringResource(R.string.phone_number)}: ${formUiState.phoneNumber}"
        )
        Text(
            text = "${stringResource(R.string.email)}: ${formUiState.email}"
        )
        Text(
            text = "${stringResource(R.string.country)}: ${formUiState.country}"
        )
        Text(
            text = "${stringResource(R.string.city)}: ${formUiState.city}"
        )
        Text(
            text = "${stringResource(R.string.address)}: ${formUiState.address}"
        )
    }
}

@Composable
fun SummaryDataLandscape(
    formUiState: FormUiState
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.personal_information),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${stringResource(R.string.name)}: ${formUiState.name}"
            )
            Text(
                text = "${stringResource(R.string.gender)}: ${formUiState.gender}"
            )
            Text(
                text = "${stringResource(R.string.date_of_birth)}: ${formUiState.birth}"
            )
            Text(
                text = "${stringResource(R.string.school_grade)}: ${formUiState.scholarity}"
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.contact_information),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${stringResource(R.string.phone_number)}: ${formUiState.phoneNumber}"
            )
            Text(
                text = "${stringResource(R.string.email)}: ${formUiState.email}"
            )
            Text(
                text = "${stringResource(R.string.country)}: ${formUiState.country}"
            )
            Text(
                text = "${stringResource(R.string.city)}: ${formUiState.city}"
            )
            Text(
                text = "${stringResource(R.string.address)}: ${formUiState.address}"
            )
        }
    }
}
