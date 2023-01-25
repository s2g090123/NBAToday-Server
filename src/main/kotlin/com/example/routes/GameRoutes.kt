package com.example.routes

import com.example.client.client
import com.example.client.getGameBoxScore
import com.example.client.getSchedule
import com.example.client.getScoreboard
import com.example.models.game.Game
import com.example.models.game.Scoreboard
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.gameRouting() {
    route("game") {
        get("scoreboard") {
            val leagueId = call.request.queryParameters["leagueID"] ?: "00"
            val gameDate = call.parameters["gameDate"] ?: return@get call.respondText(
                "Missing gameDate",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getScoreboard(leagueId, gameDate)
            val body = response.body<Scoreboard>()
            call.respond(body)
        }
        get("schedule") {
            val response = client.getSchedule()
            val body = response.body<String>()
            call.respond(body)
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getGameBoxScore(id)
            val body = response.body<Game>()
            call.respond(body)
        }
    }
}