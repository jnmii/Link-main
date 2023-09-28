package com.example.link2.presentation.screens.profile_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.link2.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    // LiveData to hold the list of profile picture URLs
    private val _profilePictures = MutableLiveData<List<String>>()
    val profilePictures: LiveData<List<String>> = _profilePictures
    var selectedImageIndex: Int = 0
    val profilePicturesFlow: Flow<List<String>> = profilePictures.asFlow()

    var posts: List<String> = emptyList()

    fun fetchProfilePictures(userId: String) {
        profileRepository.fetchProfilePictures(userId,
            onSuccess = { pictureUrls ->
                _profilePictures.value = pictureUrls
            },
            onFailure = {

            }
        )
    }

    fun fetchProfilePicturesForCurrentUser() {
        profileRepository.fetchProfilePicturesForCurrentUser(
            onSuccess = { pictureUrls ->
                _profilePictures.value = pictureUrls
            },
            onFailure = {
                // Handle failure
            }
        )
    }
}
