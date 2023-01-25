package com.example.routes

import com.example.client.client
import com.example.client.getPlayerInfo
import com.example.client.getPlayerStats
import com.example.models.player.PlayerInfo
import com.example.models.player.PlayerStats
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.playerRouting() {
    route("player") {
        get("stats") {
            val season = call.request.queryParameters["season"] ?: return@get call.respondText(
                "Missing season",
                status = HttpStatusCode.BadRequest
            )
            val id = call.request.queryParameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getPlayerStats(season, id)
            val body = response.body<PlayerStats>()
            call.respond(body)
        }
        get("info") {
            val id = call.request.queryParameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getPlayerInfo(id)
            val body = response.body<PlayerInfo>()
            call.respond(body)
        }
    }
}