package com.example.routes

import com.example.database.DatabaseFactory.userDao
import com.example.models.user.LoginBody
import com.example.models.user.UpdatePasswordBody
import com.example.models.user.UpdatePointBody
import com.example.models.user.User
import com.example.utils.Utils
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route("user") {
        post("login") {
            val loginBody = call.receive<LoginBody>()
            val account = loginBody.account ?: return@post call.respondText(
                "Missing account",
                status = HttpStatusCode.BadRequest
            )
            val password = loginBody.password ?: return@post call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            val user = userDao.user(account, password) ?: return@post call.respondText(
                "User had not been created or password is wrong!",
                status = HttpStatusCode.BadRequest
            )
            call.respond(user)
        }
        post("register") {
            val loginBody = call.receive<LoginBody>()
            val account = loginBody.account ?: return@post call.respondText(
                "Missing account",
                status = HttpStatusCode.BadRequest
            )
            val password = loginBody.password ?: return@post call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            val user = userDao.user(account)
            val response = if (user == null) {
                userDao.addUser(User(account, "User", 0, password, Utils.createToken(account, password)))
                    ?: return@post call.respondText(
                        "Register failed",
                        status = HttpStatusCode.BadRequest
                    )
            } else if (user.password != password) {
                return@post call.respondText(
                    "User had been created!",
                    status = HttpStatusCode.BadRequest
                )
            } else {
                user
            }
            call.respond(response)
        }
        post("password") {
            val passwordBody = call.receive<UpdatePasswordBody>()
            val account = passwordBody.account ?: return@post call.respondText(
                "Missing account",
                status = HttpStatusCode.BadRequest
            )
            val token = passwordBody.token ?: return@post call.respondText(
                "Missing token",
                status = HttpStatusCode.BadRequest
            )
            val password = passwordBody.newPassword ?: return@post call.respondText(
                "Missing newPassword",
                status = HttpStatusCode.BadRequest
            )
            val user = userDao.user(account) ?: return@post call.respondText(
                "account not found",
                status = HttpStatusCode.BadRequest
            )
            if (user.token != token) {
                return@post call.respondText(
                    "token is invalid",
                    status = HttpStatusCode.BadRequest
                )
            }
            if (userDao.editPassword(account, password)) {
                call.respondText(
                    "successful",
                    status = HttpStatusCode.Accepted
                )
            } else {
                call.respondText(
                    "update failed",
                    status = HttpStatusCode.ExpectationFailed
                )
            }
        }
        post("points") {
            val pointsBody = call.receive<UpdatePointBody>()
            val account = pointsBody.account ?: return@post call.respondText(
                text = "Missing account",
                status = HttpStatusCode.BadRequest
            )
            val token = pointsBody.token ?: return@post call.respondText(
                text = "Missing token",
                status = HttpStatusCode.BadRequest
            )
            val points = pointsBody.points ?: return@post call.respondText(
                text = "Missing points",
                status = HttpStatusCode.BadRequest
            )
            val user = userDao.user(account) ?: return@post call.respondText(
                "account not found",
                status = HttpStatusCode.BadRequest
            )
            if (user.token != token) {
                return@post call.respondText(
                    "token is invalid",
                    status = HttpStatusCode.BadRequest
                )
            }
            if (userDao.editPoints(account, points, token)) {
                call.respondText(
                    "successful",
                    status = HttpStatusCode.Accepted
                )
            } else {
                call.respondText(
                    "update failed",
                    status = HttpStatusCode.ExpectationFailed
                )
            }
        }
    }
}