package com.example.adaptor.presentation.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val accessToken: String
)