package com.example.link2.presentation.screens.upload_profile_pic

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.link2.data.repository.ProfileRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProfilePictureViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun uploadProfilePicture(
        selectedImageUri: Uri,
        userId: String,
        onProfilePictureUploaded: (String) -> Unit,
        onUploadFailure: () -> Unit
    ) {
        profileRepository.uploadProfilePicture(
            selectedImageUri,
            userId,
            onProfilePictureUploaded,
            onUploadFailure
        )
    }
}
