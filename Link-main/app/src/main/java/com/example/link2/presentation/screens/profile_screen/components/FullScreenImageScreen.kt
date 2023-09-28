package com.example.link2.presentation.screens.profile_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.link2.presentation.screens.profile_screen.UserProfileViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FullScreenImageScreen(navController: NavController, viewModel: UserProfileViewModel)
{
//    viewModel.fetchProfilePicturesForCurrentUser()
    val selectedImageIndex = viewModel.selectedImageIndex
    val profilePictures = viewModel.profilePicturesFlow.collectAsState(emptyList()).value

    val pagerState = rememberPagerState(initialPage = selectedImageIndex)

    Box {
        // Close Button as a circular icon
        HorizontalPager(
            state = pagerState,
            pageCount = profilePictures.size,
            modifier = Modifier
                .fillMaxSize()
                .clickable { */
/* handle clicks here if needed *//*
 }
        ) { index ->
            Image(
                painter = rememberImagePainter(profilePictures[index]),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )
        }
        IconButton(
            onClick = { navController.navigateUp()},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(40.dp)
                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
        }
    }

}
*/



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FullScreenImageScreen(navController: NavController, viewModel: UserProfileViewModel) {
    val selectedImageIndex = viewModel.selectedImageIndex
    val profilePictures = viewModel.profilePicturesFlow.collectAsState(emptyList()).value

    val pagerState = rememberPagerState(initialPage = selectedImageIndex)
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val scaleState = remember { mutableStateOf(1f) }

        HorizontalPager(
            state = pagerState,
            pageCount = profilePictures.size,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        coroutineScope.launch {
                            pagerState.scrollBy(-pan.x)
                            pagerState.animateScrollToPage((zoom * pagerState.currentPage).roundToInt())
                        }
                    }
                }
        ) { index ->
            val painter = rememberImagePainter(profilePictures[index])
            Image(
                painter = rememberImagePainter(profilePictures[pagerState.currentPage]),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scaleState.value,
                        scaleY = scaleState.value
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scaleState.value *= zoom
                            scaleState.value = max(0.8f, min(2.5f, scaleState.value))
                            coroutineScope.launch {
                                pagerState.scrollBy(-pan.x)
                            }
                        }
                    }
            )
        }

        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                .align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
        }
    }
}


