package com.example.link2.data.repository

import android.net.Uri

interface ProfileRepository
{
    fun uploadProfilePicture(
        selectedImageUri: Uri,
        userId: String,
        onProfilePictureUploaded: (String) -> Unit,
        onUploadFailure: () -> Unit
    )

    fun fetchProfilePictures(
        userId: String, onSuccess: (List<String>) -> Unit, onFailure: () -> Unit
    )

    fun fetchProfilePicturesForCurrentUser(onSuccess: (List<String>) -> Unit, onFailure: () -> Unit)

}