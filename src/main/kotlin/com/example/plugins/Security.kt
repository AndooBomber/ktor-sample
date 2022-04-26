package com.example.plugins

import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.adaptor.presentation.model.AuthUser
import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*

fun Application.configureSecurity() {
    val config = HoconApplicationConfig(ConfigFactory.load("application.conf"))

    authentication {
        jwt {
            val jwtAudience = config.property("jwt.audience").getString()
            realm = config.property("jwt.realm").getString()
            verifier(
                JWT
                    .require(Algorithm.HMAC256("secret"))
                    .withAudience(jwtAudience)
                    .withIssuer(config.property("jwt.issuer").getString())
                    .build()
            )
            validate { credential ->
                credential.payload.getClaim("userId").let { claim ->
                    if (!claim.isNull) {
                        AuthUser(claim.asInt())
                    } else {
                        null
                    }
            }}
        }
    }
}
