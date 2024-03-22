package co.edu.udea.compumovil.gr09_20241.lab1

import androidx.compose.runtime.Composable
import co.edu.udea.compumovil.gr09_20241.lab1.ui.DataViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

enum class FormScreen(){
    Personal,
    Contact,
    Summary
}

@Composable
fun FormApp(
    viewModel: DataViewModel = viewModel()
    navController:NavHostController = rememberNavController()
){

}