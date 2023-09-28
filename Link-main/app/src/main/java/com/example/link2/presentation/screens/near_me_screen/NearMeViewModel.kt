package com.example.link2.presentation.screens.near_me_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.link2.data.User
import com.example.link2.data.repository.FirebaseUserRepository
import com.example.link2.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NearMeViewModel @Inject constructor(
    private val userRepository: FirebaseUserRepository
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> = _usersLiveData

    fun loadUsersWithProfilePictures() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val users = userRepository.getAllUsersLiveData()
                _usersLiveData.postValue(users)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}


