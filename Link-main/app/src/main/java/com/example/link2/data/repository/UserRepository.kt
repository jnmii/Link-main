package com.example.link2.data.repository

import androidx.lifecycle.LiveData
import com.example.link2.data.User

interface UserRepository {
    suspend fun getAllUsersLiveData(): List<User>

}

