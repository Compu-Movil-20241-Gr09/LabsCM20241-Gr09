package co.edu.udea.compumovil.gr09_20241.lab1.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun ContactDataScreen(){
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        ContactDataPortrait()
    }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        ContactDataLandscape()
    }
}

@Composable
fun ContactDataPortrait(){

}

@Composable
fun ContactDataLandscape(){

}