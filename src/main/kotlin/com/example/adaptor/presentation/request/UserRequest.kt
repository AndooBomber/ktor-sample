package com.example.adaptor.presentation.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val email: String,
    val pass: String
)