package com.example.link2.utils.dashboardscreen_utils

import androidx.compose.ui.graphics.painter.Painter

data class DashboardData(
    val firstName: String,
    val lastName: String,
    val profilePicture: Painter,
    val username: String,
    val rating: Int,
    val recentMeets: List<String>,
    val ratingMovement: List<String>,
    val recentComments: List<String>,
    val newMessages: Int,
    val newComments: Int,
    val newRating: Int,
    val updates: Int,
    val alerts: Int,
    val totalMeets: String,
    val positiveMeetScore: String,
    val suggestedMeets: List<String>,
    val suggestionsForRatingImprovements: List<String>,
    val suggestPlacesForMeets: List<String>,
    val newFeatures: List<String>,
    val promotions: List<String>,
    val profileSettingsOnClick: () -> Unit,
    val accountSettingsOnClick: () -> Unit,
    val meetOnClick: () -> Unit,
    val logoutOnClick: () -> Unit
)
