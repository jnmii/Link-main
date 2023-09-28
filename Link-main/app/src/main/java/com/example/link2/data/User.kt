package com.example.link2.data

data class User(
    val userId: String,
    val profilePictureUrl: String,
    // Other properties
) {
    // Add a no-argument constructor
    constructor() : this("","")
}


