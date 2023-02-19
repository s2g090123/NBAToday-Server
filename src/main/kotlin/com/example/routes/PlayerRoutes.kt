package com.example.routes

import com.example.client.client
import com.example.client.getPlayerInfo
import com.example.client.getPlayerStats
import com.example.models.player.PlayerDetail
import com.example.models.player.PlayerInfo
import com.example.models.player.PlayerStats
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async

fun Route.playerRouting() {
    route("player") {
        get("detail") {
            val season = call.parameters["season"] ?: return@get call.respondText(
                "Missing season",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val deferred1 = async {
                val response = client.getPlayerStats(season, id)
                response.body<PlayerStats>()
            }
            val deferred2 = async {
                val response = client.getPlayerInfo(id)
                response.body<PlayerInfo>()
            }
            val stats = deferred1.await()
            val info = deferred2.await()
            call.respond(PlayerDetail(info, stats))
        }
        get("stats") {
            val season = call.parameters["season"] ?: return@get call.respondText(
                "Missing season",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getPlayerStats(season, id)
            val body = response.body<PlayerStats>()
            call.respond(body)
        }
        get("info") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getPlayerInfo(id)
            val body = response.body<PlayerInfo>()
            call.respond(body)
        }
    }
}