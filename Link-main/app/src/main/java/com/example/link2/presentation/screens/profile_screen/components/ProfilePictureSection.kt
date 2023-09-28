package com.example.link2.presentation.screens.profile_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun DisplayProfilePictures(profilePictures: List<String>) {

    var isImageClicked by remember { mutableStateOf(false) }
    var clickedImageIndex by remember { mutableStateOf(0) }

    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val pagerState = rememberPagerState(initialPage = clickedImageIndex)

    val onImageClicked: (Int) -> Unit = { index ->
        clickedImageIndex = index
        isImageClicked = !isImageClicked
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isImageClicked) screenHeight else screenHeight * 0.47f),
        contentAlignment = Alignment.Center
    ) {
        if (profilePictures.isNotEmpty()) {

            HorizontalPager(
                state = pagerState,
                pageCount = profilePictures.size,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { /* handle clicks here if needed */ }
            ) { index ->
                Image(
                    painter = rememberImagePainter(profilePictures[index]),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onImageClicked(index)
                        }
                )
            }

            // Camera icon and number of photos at top left
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                    .padding(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Camera",
                    tint = Color.White
                )

                Text(text = " ${profilePictures.size} Photos", color = Color.White)
            }


            // Close Button as a circular icon
            IconButton(
                onClick = { isImageClicked = false },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(40.dp)
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }


            // Box with dots and IconButton
            val modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(20.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-16).dp) // Adjust the offset value as needed

            Dots(size = profilePictures.size, pagerState, modifier)

        } else {
            // Placeholder content when profilePictures is empty
            Text(text = "No profile picture")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Dots(size: Int, pagerState: PagerState, modifier: Modifier)
{

    Box(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
        ) {
            repeat(size) { dotIndex ->
                Spacer(modifier = Modifier.size(8.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (dotIndex == pagerState.currentPage) Color.White else Color.Gray
                        )
                )
            }
        }
    }
}