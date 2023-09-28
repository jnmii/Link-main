package com.example.link2.data.repository

import com.example.link2.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class FirebaseUserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : UserRepository {

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()

    override suspend fun getAllUsersLiveData(): List<User> {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                val usersReference: DatabaseReference = db.getReference("users")

                usersReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val userList = mutableListOf<User>()

                        for (snapshot in dataSnapshot.children) {
                            val user = snapshot.getValue(User::class.java)
                            if (user != null) {
                                userList.add(user)
                            }
                        }

                        continuation.resume(userList)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        continuation.resumeWithException(databaseError.toException())
                    }
                })
            }
        }
    }

}
