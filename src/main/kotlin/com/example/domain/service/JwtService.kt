package com.example.domain.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class JwtService(private val userRepository: UserRepository) {
    private val config = HoconApplicationConfig(ConfigFactory.load("application.conf"))

    suspend fun certificate(user: User): String {
        val id = userRepository.getId(user)

        return JWT.create()
                .withAudience(config.property("jwt.audience").getString())
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))) // 有効期限
                .withClaim("userId", id)
                .withIssuer(config.property("jwt.issuer").getString())
                .sign(Algorithm.HMAC256(config.property("jwt.secret").getString()))
    }
}