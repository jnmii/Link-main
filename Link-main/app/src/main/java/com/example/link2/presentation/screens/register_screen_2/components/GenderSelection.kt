package com.example.link2.presentation.screens.register_screen_2.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.example.link2.ui.theme.DarkColorScheme
import com.example.link2.utils.validation_utils.ValidationUtils

@Composable
fun GenderSelection(
    selectedGender: ValidationUtils.Gender?, onGenderSelected: (ValidationUtils.Gender) -> Unit
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        val genderOptions = ValidationUtils.Gender.values()


        genderOptions.forEach { gender ->
            Row(
                Modifier
//                    .weight(1f)
                    .selectable(selected = (gender == selectedGender),
                        onClick = { onGenderSelected(gender) })
                    .padding(8.dp)) {
                RadioButton(
                    selected = (gender == selectedGender),
                    onClick = { onGenderSelected(gender) },
                    colors = RadioButtonDefaults.colors(selectedColor = DarkColorScheme.primary)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    gender.toString(),
                    fontWeight = FontWeight.Medium,
                    color = if (gender == selectedGender) DarkColorScheme.primary else Color.Gray
                )
            }
        }
    }
}
