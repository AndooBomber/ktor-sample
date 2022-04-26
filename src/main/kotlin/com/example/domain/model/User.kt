package com.example.domain.model

import com.example.adaptor.presentation.request.UserRequest

data class User(
    val email: String,
    val pass: String
) {
    companion object {
        fun create(request: UserRequest) = User(
            email = request.email,
            pass = request.pass
        )
    }
}
