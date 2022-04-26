package com.example

import com.example.adaptor.infrastructure.repository.UserRepositoryImpl
import com.example.adaptor.presentation.route.configureRouting
import com.example.domain.repository.UserRepository
import com.example.domain.service.JwtService
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.dsl.module

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Koin) {
            modules(applicationModule())
        }
        configureSecurity()
        configureRouting()
        configureMonitoring()
        configureSerialization()
    }.start(wait = true)
}

fun Application.applicationModule() = module(createdAtStart = true) {
    single { JwtService(get()) }
    single<UserRepository> { UserRepositoryImpl() }
}
