package com.example.link2.domain.repository

import android.net.Uri
import com.example.link2.data.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import java.util.UUID
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val storageReference: StorageReference
) : ProfileRepository {

    private val usersReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

    override fun uploadProfilePicture(
        selectedImageUri: Uri,
        userId: String,
        onProfilePictureUploaded: (String) -> Unit,
        onUploadFailure: () -> Unit
    ) {
        val imageName = "${userId}_${UUID.randomUUID()}.jpg"
        val imageRef = storageReference.child("profile_pictures").child(imageName)

        uploadImageToStorage(imageRef, selectedImageUri) { imageUrl ->
            storeProfilePictureUrl(userId, imageUrl)
            onProfilePictureUploaded(imageUrl)
        }
    }

    override fun fetchProfilePictures(userId: String, onSuccess: (List<String>) -> Unit, onFailure: () -> Unit) {
        val userReference = usersReference.child(userId)
        userReference.child("profilePictureUrls").addListenerForSingleValueEvent(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pictureUrls = mutableListOf<String>()
                for (childSnapshot in snapshot.children) {
                    val imageUrl = childSnapshot.getValue(String::class.java)
                    imageUrl?.let { pictureUrls.add(it) }
                }
                onSuccess(pictureUrls)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure()
            }
        })
    }

    override fun fetchProfilePicturesForCurrentUser(onSuccess: (List<String>) -> Unit, onFailure: () -> Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            fetchProfilePictures(userId, onSuccess, onFailure)
        } else {
            onFailure()
        }
    }

    private fun uploadImageToStorage(
        imageRef: StorageReference,
        selectedImageUri: Uri,
        onSuccess: (String) -> Unit
    ) {
        val uploadTask = imageRef.putFile(selectedImageUri)
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) throw task.exception ?: Exception()
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess(task.result.toString())
            }
        }
    }

    private fun storeProfilePictureUrl(userId: String, imageUrl: String) {
        val database = FirebaseDatabase.getInstance()
        val usersReference = database.reference.child("users")

        val userReference = usersReference.child(userId)

        // Update the current profile picture URL
        userReference.child("profilePictureUrl").setValue(imageUrl)

        // Store the new profile picture URL in a list of profilePictureUrls
        userReference.child("profilePictureUrls").push().setValue(imageUrl)
    }


}
