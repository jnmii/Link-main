package com.example.link2.di

import com.example.link2.data.repository.FirebaseUserRepository
import com.example.link2.data.repository.ProfileRepository
import com.example.link2.data.repository.Repository
import com.example.link2.data.repository.UserRepository
import com.example.link2.domain.repository.ProfileRepositoryImpl
import com.example.link2.domain.repository.RepositoryImpl
import com.example.link2.domain.repository.UserRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule
{
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth
    {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepository(
        firebaseAuth: FirebaseAuth
    ): Repository
    {
        return RepositoryImpl(firebaseAuth)
    }

//    @Provides
//    @Singleton
//    fun provideFirebaseStorage(): FirebaseFirestore
//    {
//        return FirebaseFirestore.getInstance();
//    }

    @Provides
    @Singleton
    fun provideStorageReference(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }

    @Provides
    @Singleton
    fun provideProfileRepository(storageReference: StorageReference): ProfileRepository {
        return ProfileRepositoryImpl(storageReference)
    }



//    @Provides
//    @Singleton
//    fun provideUserRepository(firebaseAuth: FirebaseAuth): UserRepository {
//        return UserRepositoryImpl(firebaseAuth) // Pass the FirebaseAuth instance
//    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepository(firebaseAuth: FirebaseAuth): UserRepository {
        return FirebaseUserRepository(firebaseAuth)
    }

}