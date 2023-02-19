package com.example.routes

import com.example.client.client
import com.example.client.getGameBoxScore
import com.example.client.getSchedule
import com.example.client.getScoreboard
import com.example.models.game.Game
import com.example.models.game.Scoreboard
import com.example.utils.Utils
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import java.util.*

fun Route.gameRouting() {
    route("game") {
        get("scoreboard") {
            val leagueId = call.parameters["leagueID"] ?: "00"
            val gameDate = call.parameters["gameDate"] ?: return@get call.respondText(
                "Missing gameDate",
                status = HttpStatusCode.BadRequest
            )
            val response = client.getScoreboard(leagueId, gameDate)
            val body = response.body<Scoreboard>()
            call.respond(body)
        }
        get("scoreboards") {
            val leagueId = call.parameters["leagueID"] ?: "00"
            val year = call.parameters["year"]?.toIntOrNull() ?: return@get call.respondText(
                "Missing year or year is wrong",
                status = HttpStatusCode.BadRequest
            )
            val month = call.parameters["month"]?.toIntOrNull() ?: return@get call.respondText(
                "Missing month or month is wrong",
                status = HttpStatusCode.BadRequest
            )
            val day = call.parameters["day"]?.toIntOrNull() ?: return@get call.respondText(
                "Missing day or day is wrong",
                status = HttpStatusCode.BadRequest
            )
            val total = call.parameters["total"]?.toIntOrNull()?.takeIf { it >= 1 } ?: 1
            val output = mutableListOf<Scoreboard>()
            (0 until total).map {
                async {
                    val calendar = Utils.getCalendar().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month - 1)
                        set(Calendar.DAY_OF_MONTH, day)
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, it)
                    val gameYear = calendar.get(Calendar.YEAR)
                    val gameMonth = calendar.get(Calendar.MONTH) + 1
                    val gameDay = calendar.get(Calendar.DAY_OF_MONTH)
                    val gameDate = Utils.formatDate(gameYear, gameMonth, gameDay)
                    val response = client.getScoreboard(leagueId, gameDate)
                    val body = response.body<Scoreboard>()
                    output.add(body)
                }
            }.awaitAll()
            call.respond(output)
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