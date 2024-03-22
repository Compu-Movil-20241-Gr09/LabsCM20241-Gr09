package co.edu.udea.compumovil.gr09_20241.lab1.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.lab1.R
import co.edu.udea.compumovil.gr09_20241.lab1.components.HeaderText
import co.edu.udea.compumovil.gr09_20241.lab1.components.InputLabelText
import co.edu.udea.compumovil.gr09_20241.lab1.components.RadioGroup

class PersonalDataActivity : AppCompatActivity() {
    /*
    nombres Obligatorio (EditText)
    apellidos  Obligatorio (EditText)
    genero RadioButton
    fecha de nacimiento Obligatorio (DatePicker)
    grado de escolaridad Spinner
    */

    // Hacer landscape tambien

    @Composable
    fun PersonalDataPortrait() {
        val genderOptions = listOf(stringResource(R.string.male),
            stringResource(R.string.female), stringResource(R.string.other)
        )

        var name by remember { mutableStateOf("")}
        var lastname by remember { mutableStateOf("")}
        var gender by remember { mutableStateOf(genderOptions.first()) }
        /*
            Date of birth
            day
            month
            year
         */

        Column (

        ) {
            HeaderText(label = stringResource(R.string.personal_information))
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(R.string.name))}
            )

            OutlinedTextField(
                value = lastname,
                onValueChange = { lastname = it },
                label = { Text(text = stringResource(R.string.last_name)) }
            )

            Row (verticalAlignment = Alignment.CenterVertically) {
                InputLabelText(label = stringResource(R.string.gender))
                
                RadioGroup(
                    items = genderOptions,
                    selection = gender,
                    onItemClick =   { clickedItem ->
                        gender = clickedItem
                    }
                )
            }

            Row (verticalAlignment = Alignment.CenterVertically){
                InputLabelText(label = stringResource(R.string.date_of_birth))

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select date")
                }
            }

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
}