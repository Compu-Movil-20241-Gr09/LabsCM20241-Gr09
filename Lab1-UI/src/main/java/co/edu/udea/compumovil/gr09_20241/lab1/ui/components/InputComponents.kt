package co.edu.udea.compumovil.gr09_20241.lab1.ui.components

import android.app.DatePickerDialog
import android.graphics.drawable.Icon
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.lab1.R
import java.util.Calendar
import java.util.Date

@Composable
fun BasicTextField(
    value: String,
    onValueChange: ((String) -> Unit),
    label: String,
    imageVector: ImageVector,
    readOnly: Boolean
){
    OutlinedTextField(
        modifier = Modifier
            .padding(2.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon =  {
            Icon(imageVector = imageVector, contentDescription = "")
        },
        readOnly = readOnly

    )
}

@Composable
fun LabelledRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {

    Row (
        modifier = modifier.padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = colors
        )

        Text(
            text = label
        )
    }
}

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selection: String,
    onItemClick: ((String) -> Unit)
) {
    Row (modifier = modifier){
        items.forEach { item ->
            LabelledRadioButton(
                label = item,
                selected = item == selection,
                onClick = {
                    onItemClick(item)
                }
            )
        }
    }
}

@Composable
fun LabelledRadioGroup(
    label: String,
    items: List<String>,
    selection: String,
    onItemClick: ((String) -> Unit)
){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = label)
        RadioGroup(items = items, selection = selection, onItemClick = onItemClick)
    }

}

@Composable
fun LabelledDatePicker(
    value: String,
    onValueChange: ((String) -> Unit)
){
    Row(){
        DatePickerComponent(
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun DatePickerComponent(
    value: String,
    onValueChange: ((String) -> Unit)
){
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Row(modifier = Modifier
        .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            label = stringResource(R.string.date_of_birth),
            imageVector = Icons.Default.DateRange,
            readOnly = true
        )

        Button(onClick = {
            mDatePickerDialog.show()
        }
        ) {
            Text(text = "Open Date Picker", color = Color.White)
        }
    }

}