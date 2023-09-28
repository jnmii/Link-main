package com.example.link2.presentation.screens.ratingScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun RatingButtonWithPoints(
    points: Int,
    onPointsSelected: (Int) -> Unit
) {
    var selectedPoints by remember { mutableStateOf(0) }

    Column {
        Text(text = "Select points for rating", fontSize = 18.sp)
        Button(
            onClick = {
                onPointsSelected(selectedPoints)
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Rate")
        }
    }
}

@Preview
@Composable
fun RatingButtonWithPointsPreview() {
    RatingButtonWithPoints(
        points = 0, // Initialize with 0 points
        onPointsSelected = { points ->
            // Handle the selected points
        }
    )
}
