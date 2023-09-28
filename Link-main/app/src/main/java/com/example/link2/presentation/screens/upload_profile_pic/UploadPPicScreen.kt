package com.example.link2.presentation.screens.upload_profile_pic

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.link2.presentation.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration

import java.util.*


@Composable
fun ProfilePictureUpload(navController: NavController) {
    val context = LocalContext.current
    val profilePictureViewModel = hiltViewModel<ProfilePictureViewModel>()
    // Get the UID of the currently authenticated user
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> selectedImageUri = uri }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth().fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display selected image or placeholder
        CircularImage(
            imageUrl = selectedImageUri.toString(),
            contentDescription = "Profile Picture",
            size = 120
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to select an image
        Button(
            onClick = { launcher.launch("image/*") }
        ) {
            Text(text = "Select Profile Picture")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to upload image
        Button(
            onClick = {
                selectedImageUri?.let { uri ->
                    if (userId != null)
                    {
                        profilePictureViewModel.uploadProfilePicture(selectedImageUri = uri, userId = userId, // Pass the user's ID here
                            onProfilePictureUploaded = { imageUrl ->
                                navController.navigate(Screen.ProfileScreen)
//                                Toast.makeText(context, "Uploaded successfully", Toast.LENGTH_SHORT).show()

                            }, onUploadFailure = {
                                Toast.makeText(context, "Upload failed", Toast.LENGTH_SHORT).show()
                            })
                    }
                }


            },
            enabled = selectedImageUri != null
        ) {
            Text(text = "Upload Profile Picture")
        }
    }
}


@Composable
fun CircularImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Int = 120
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(Color.Gray), // Add background color to circular area
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp) // Maintain the aspect ratio
                .clip(CircleShape) // Clip the image to be circular
        )
    }
}