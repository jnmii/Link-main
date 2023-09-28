package com.example.link2.presentation.screens.register_screen_2.components

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateOfBirthPicker(
    dateOfBirth: LocalDate, onDateChange: (LocalDate) -> Unit, dateOfBirthError: String?
)
{
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    val dateStr = dateOfBirth.format(formatter)

    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog)
    {
        val datePickerDialog = DatePickerDialog(
            context, { _, year, month, dayOfMonth ->
                onDateChange(LocalDate.of(year, month + 1, dayOfMonth))
                showDialog = false
            }, dateOfBirth.year, dateOfBirth.monthValue - 1, dateOfBirth.dayOfMonth
        )
        datePickerDialog.show()
        showDialog = false
    }

    OutlinedTextField(value = dateStr,
        onValueChange = { },
        label = { Text("Date of Birth") },
        trailingIcon = {
            Icon(Icons.Default.CalendarMonth,
                contentDescription = "Date Picker Icon",
                modifier = Modifier.clickable { showDialog = true })
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        enabled = false,
        supportingText = {
            if (dateOfBirthError != null)
            {
                Text(
                    text = dateOfBirthError,
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 12.sp
                    ),

                )
            }
        }
    )
}
