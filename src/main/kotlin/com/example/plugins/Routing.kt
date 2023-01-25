package com.example.plugins

import com.example.routes.gameRouting
import com.example.routes.playerRouting
import com.example.routes.teamRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("nba") {
            playerRouting()
            teamRouting()
            gameRouting()
        }
    }
}
