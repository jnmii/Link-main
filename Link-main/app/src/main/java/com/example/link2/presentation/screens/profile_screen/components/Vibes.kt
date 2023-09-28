package com.example.link2.presentation.screens.profile_screen.components

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Vibes(
    vibes: List<VibeItem>
) {
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(minSize = 100.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(if (vibes.size>3) 100.dp else if(vibes.size<=2) 70.dp else 0.dp),
   ) {
        items(vibes) { vibe ->
            VibeItemCard(vibe)
        }
    }
}

data class VibeItem(
    val title: String,
    val emoji: String,
    val textColor: Int,
    val backgroundColor: Int
)

@Composable
fun VibeItemCard(
    vibe: VibeItem
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 2.dp
//        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = vibe.emoji,
                fontSize = 40.sp,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = vibe.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                style = TextStyle(fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Composable
fun DummyVibesList() {
    val dummyVibesList = listOf(
        VibeItem("Happy", "😄", Color.WHITE, Color.GREEN),
        VibeItem("Calm", "😌", Color.BLACK, Color.YELLOW),
        VibeItem("Excited", "🎉", Color.WHITE, Color.BLUE),
        VibeItem("Love", "❤️", Color.RED, Color.LTGRAY),
        VibeItem("Cool", "😎", Color.BLUE, Color.CYAN),
        VibeItem("Laugh", "😂", Color.WHITE, Color.MAGENTA),
        VibeItem("Peace", "✌️", Color.GREEN, Color.YELLOW),
        VibeItem("Confident", "💪", Color.BLACK, Color.WHITE),
        VibeItem("Playful", "🤪", Color.RED, Color.DKGRAY),
        VibeItem("Relaxed", "😊", Color.BLUE, Color.WHITE),
        VibeItem("Creative", "🎨", Color.WHITE, Color.YELLOW),
        VibeItem("Curious", "🤔", Color.BLUE, Color.WHITE),
        VibeItem("Energetic", "⚡", Color.YELLOW, Color.RED),
        // Add more dummy vibe items as needed
    )

    Column() {
        Text(
            text = "Vibes",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
        )

        Vibes(vibes = dummyVibesList)
    }
}
