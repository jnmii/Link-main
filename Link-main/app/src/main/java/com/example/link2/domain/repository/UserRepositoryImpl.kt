package com.example.link2.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.link2.data.User
import com.example.link2.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    UserRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

    private val userList = mutableListOf<User>()
    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val updatedUserList = mutableListOf<User>()
            for (userSnapshot in snapshot.children) {
                val user = userSnapshot.getValue(User::class.java)
                user?.let { updatedUserList.add(it) }
            }
            userList.clear()
            userList.addAll(updatedUserList)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle the error.
            // You can add error handling here if needed.
        }
    }

    init {
        // Start listening for changes when this class is created
        databaseReference.addValueEventListener(valueEventListener)
    }

    // Make sure to remove the listener when it's no longer needed
    fun removeListener() {
        databaseReference.removeEventListener(valueEventListener)
    }

    override suspend fun getAllUsersLiveData(): List<User> {
        return userList
    }


}

