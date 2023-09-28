package com.example.link2.data.repository

import com.google.firebase.auth.FirebaseUser

interface Repository
{

    //region Account Services
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: FirebaseUser?
    suspend fun createEmailPasswordAccount(
        firstName: String, lastName: String, email: String, password: String
    ): FirebaseUser?

    suspend fun emailPasswordLogin(email: String, password: String): FirebaseUser?
    suspend fun googleLogin(idToken: String?): FirebaseUser?

    suspend fun facebookLogin(token: String): FirebaseUser?
    suspend fun sendRecoveryEmail(email: String)

    //    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
    //endregion

}