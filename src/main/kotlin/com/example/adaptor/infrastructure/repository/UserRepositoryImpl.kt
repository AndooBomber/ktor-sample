package com.example.adaptor.infrastructure.repository

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun getId(user: User): Int {
        // todo DB
        if (user.email != "hoge@fuga.com" && user.pass != "hogepass") throw Exception("Invalid!")
        return 1
    }
}