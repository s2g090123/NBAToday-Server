package com.example.client

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

private const val STATS_BASE_URL = "https://stats.nba.com/stats"
private const val CDN_BASE_URL = "https://cdn.nba.com"

@OptIn(ExperimentalSerializationApi::class)
val client = HttpClient {
    expectSuccess = true
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
    install(HttpCookies)
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        })
    }
}

private fun HttpRequestBuilder.statsBuilder(block: HttpRequestBuilder.() -> Unit) {
    headers {
        append("Referer", "https://stats.nba.com/")
        append("Connection", "keep-alive")
        append(
            "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36"
        )
        append("Accept", "*/*")
        append("Accept-Language", "en-US")
        append("Cache-Control", "no-cache")
        append("Origin", "https://www.nba.com")
    }
    apply(block)
}

private suspend fun HttpClient.statsRequest(
    endpoint: String,
    block: HttpRequestBuilder.() -> Unit
): HttpResponse {
    cookies("http://0.0.0.0:8080/")
    return request("$STATS_BASE_URL/$endpoint") {
        statsBuilder(block)
    }
}

private suspend fun HttpClient.cdnRequest(
    endpoint: String,
    block: HttpRequestBuilder.() -> Unit
): HttpResponse {
    cookies("http://0.0.0.0:8080/")
    return request("$CDN_BASE_URL/$endpoint") {
        apply(block)
    }
}

suspend fun HttpClient.getPlayerStats(
    season: String,
    playerId: String
) = statsRequest("playerdashboardbyyearoveryear") {
    method = HttpMethod.Get
    url {
        parameters.apply {
            append("Season", season)
            append("SeasonType", "Regular Season")
            append("PlayerID", playerId)
            append("MeasureType", "Base")
            append("Month", "0")
            append("OpponentTeamID", "0")
            append("PaceAdjust", "N")
            append("PerMode", "Totals")
            append("Period", "0")
            append("LastNGames", "0")
            append("DateFrom", "")
            append("DateTo", "")
            append("GameSegment", "")
            append("Location", "")
            append("Outcome", "")
            append("PlusMinus", "N")
            append("Rank", "N")
            append("SeasonSegment", "")
            append("VsConference", "")
            append("VsDivision", "")
        }
    }
}

suspend fun HttpClient.getPlayerInfo(
    playerId: String
) = statsRequest("commonplayerinfo") {
    method = HttpMethod.Get
    url {
        parameters.apply {
            append("PlayerID", playerId)
        }
    }
}

suspend fun HttpClient.getTeamPlayersStats(
    season: String,
    teamId: String
) = statsRequest("teamplayerdashboard") {
    method = HttpMethod.Get
    url {
        parameters.apply {
            append("Season", season)
            append("SeasonType", "Regular Season")
            append("TeamID", teamId)
            append("MeasureType", "Base")
            append("Month", "0")
            append("OpponentTeamID", "0")
            append("PaceAdjust", "N")
            append("PerMode", "Totals")
            append("Period", "0")
            append("LastNGames", "0")
            append("DateFrom", "")
            append("DateTo", "")
            append("GameSegment", "")
            append("Location", "")
            append("Outcome", "")
            append("PORound", "")
            append("PlusMinus", "N")
            append("Rank", "N")
            append("SeasonSegment", "")
            append("VsConference", "")
            append("VsDivision", "")
        }
    }
}

suspend fun HttpClient.getTeamsStats(
    season: String,
    teamId: String
) = statsRequest("leaguedashteamstats") {
    method = HttpMethod.Get
    url {
        parameters.apply {
            append("Season", season)
            append("SeasonType", "Regular Season")
            append("TeamID", teamId)
            append("MeasureType", "Base")
            append("Month", "0")
            append("OpponentTeamID", "0")
            append("PaceAdjust", "N")
            append("PerMode", "Totals")
            append("Period", "0")
            append("LastNGames", "0")
            append("Conference", "")
            append("DateFrom", "")
            append("DateTo", "")
            append("Division", "")
            append("GameScope", "")
            append("GameSegment", "")
            append("Location", "")
            append("Outcome", "")
            append("PORound", "")
            append("PlayerExperience", "")
            append("PlayerPosition", "")
            append("PlusMinus", "N")
            append("Rank", "N")
            append("SeasonSegment", "")
            append("StarterBench", "")
            append("TwoWay", "")
            append("VsConference", "")
            append("VsDivision", "")
        }
    }
}

suspend fun HttpClient.getScoreboard(
    leagueId: String,
    gameDate: String
) = statsRequest("scoreboardv3") {
    method = HttpMethod.Get
    url {
        parameters.apply {
            append("LeagueID", leagueId)
            append("GameDate", gameDate)
        }
    }
}

suspend fun HttpClient.getSchedule() = cdnRequest("static/json/staticData/scheduleLeagueV2_32.json") {
    method = HttpMethod.Get
}

suspend fun HttpClient.getGameBoxScore(
    gameId: String
) = cdnRequest("static/json/liveData/boxscore/boxscore_$gameId.json") {
    method = HttpMethod.Get
}