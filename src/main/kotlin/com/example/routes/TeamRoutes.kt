package com.example.routes

import com.example.client.client
import com.example.client.getTeamPlayersStats
import com.example.client.getTeamsStats
import com.example.models.team.TeamPlayersStats
import com.example.models.team.TeamStats
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.teamRouting() {
    route("team") {
        get("{id}/players") {
            val season = call.parameters["season"] ?: return@get call.respondText(
                "Missing season",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getTeamPlayersStats(season, id)
            val body = response.body<TeamPlayersStats>()
            call.respond(body)
        }
        get("stats") {
            val season = call.parameters["season"] ?: return@get call.respondText(
                "Missing season",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: "0"
            val response = client.getTeamsStats(season, id)
            val body = response.body<TeamStats>()
            call.respond(body)
        }
    }
}