package com.example.adaptor.presentation.route

import com.example.adaptor.presentation.response.AuthResponse
import com.example.adaptor.presentation.model.AuthUser
import com.example.adaptor.presentation.request.UserRequest
import com.example.adaptor.presentation.response.UserResponse
import com.example.domain.model.User
import com.example.domain.service.JwtService
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.request.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val jwtService by inject<JwtService>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/user") {
            post("/auth") {
                call.receive<UserRequest>().let {
                    val response = jwtService.certificate(User.create(it))
                    call.respond(AuthResponse(response))
                }
            }

            authenticate {
                get("/id") {
                    val user = call.principal<AuthUser>()
                    call.respond(UserResponse(user!!.id))
                }
            }
        }
    }
}
