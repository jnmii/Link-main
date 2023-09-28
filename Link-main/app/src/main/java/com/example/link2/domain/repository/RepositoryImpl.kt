package com.example.link2.domain.repository

import com.example.link2.data.repository.Repository
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth):Repository
{
    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = firebaseAuth.currentUser != null
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun createEmailPasswordAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser? {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await().user?.let {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName("$firstName $lastName")
//                .setPhotoUri(Uri.parse(""))
                .build()
            it.updateProfile(profileUpdates).await()
            return it
        }
        return null

    }

    override suspend fun emailPasswordLogin(email: String, password: String): FirebaseUser? {
        println("internal test $email")
        firebaseAuth.signInWithEmailAndPassword(email, password).await().user?.let {
            println("intttttt $it")
            return it
        }
        return null
    }

    override suspend fun googleLogin(idToken: String?): FirebaseUser? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        val user = firebaseAuth.signInWithCredential(credential).await().user
//        user?.let {
//            return it
//        }
        firebaseAuth.signInWithCredential(credential).await().user?.let {
            return it
        }
        return null
    }

    override suspend fun facebookLogin(token: String): FirebaseUser? {
        val credential = FacebookAuthProvider.getCredential(token)
        firebaseAuth.signInWithCredential(credential).await().user?.let {
            return it
        }
        return null
    }

    override suspend fun sendRecoveryEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }

    override suspend fun linkAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
//        LoginManager.getInstance().logOut()
    }
}