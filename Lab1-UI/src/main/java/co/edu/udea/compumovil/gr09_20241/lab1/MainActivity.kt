package co.edu.udea.compumovil.gr09_20241.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr09_20241.lab1.ui.DataViewModel
import co.edu.udea.compumovil.gr09_20241.lab1.ui.PersonalDataScreen
import co.edu.udea.compumovil.gr09_20241.lab1.ui.theme.Labs20241Gr09Theme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20241Gr09Theme {
               FormApp()
            }
        }
    }
}
