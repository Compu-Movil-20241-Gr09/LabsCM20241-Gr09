package co.edu.udea.compumovil.gr09_20241.lab1.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import co.edu.udea.compumovil.gr09_20241.lab1.R
import java.util.Calendar
import java.util.Date

@Composable
fun LabelledDatePicker(
    value: String,
    onValueChange: ((String) -> Unit)
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            label = stringResource(R.string.date_of_birth),
            imageVector = Icons.Default.DateRange,
            readOnly = true
        )

        DatePickerComponent(
            onValueChange = onValueChange
        )
    }
}

@Composable
fun DatePickerComponent(
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
            onValueChange(mDate.value)
        }, mYear, mMonth, mDay
    )

    Button(onClick = {
        mDatePickerDialog.show()
    }
    ) {
        Text(text = stringResource(R.string.select_date), color = Color.White)
    }
}