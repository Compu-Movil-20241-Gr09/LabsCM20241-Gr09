package co.edu.udea.compumovil.gr09_20241.lab1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.edu.udea.compumovil.gr09_20241.lab1.ui.DataViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr09_20241.lab1.ui.ContactDataScreen
import co.edu.udea.compumovil.gr09_20241.lab1.ui.PersonalDataScreen

enum class FormScreen(@StringRes val title: Int){
    Personal(title = R.string.personal_information),
    Contact(title = R.string.contact_information),
    Summary(title = R.string.app_name)
}

@Composable
fun FormAppBar(
    currentScreen: FormScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun FormApp(
    viewModel: DataViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = FormScreen.valueOf(
        backStackEntry?.destination?.route ?: FormScreen.Personal.name
    )

    Scaffold(
        topBar = {
            FormAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = FormScreen.Personal.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = FormScreen.Personal.name) {
                PersonalDataScreen(
                    dataViewModel = viewModel,
                    onNextButtonClicked = {
                        navController.navigate(FormScreen.Contact.name)
                    }
                )
            }
            composable(route = FormScreen.Contact.name) {
                ContactDataScreen(
                    dataViewModel = viewModel,
                    onNextButtonClicked = {
                        navController.navigate(FormScreen.Personal.name)
                    }
                )
            }
            composable(route = FormScreen.Summary.name) {
                /* Por si quiero hacer la pantalla del resumen */
            }
        }
    }
}