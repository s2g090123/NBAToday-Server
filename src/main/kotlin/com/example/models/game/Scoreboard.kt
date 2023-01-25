package com.example.models.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Scoreboard(
    @SerialName("scoreboard")
    val scoreboard: Scoreboard?,
) {
    @Serializable
    data class Scoreboard(
        @SerialName("gameDate")
        val gameDate: String?, // e.g. 2022-12-14
        @SerialName("games")
        val games: List<Game>?
    ) {
        @Serializable
        data class Game(
            @SerialName("gameId")
            val gameId: String?, // e.g. 0022200414
            @SerialName("gameCode")
            val gameCode: String?, // e.g. 20221214/DETCHA
            @SerialName("gameStatus")
            val gameStatus: Int?, // e.g. 1 or 3
            @SerialName("gameStatusText")
            val gameStatusText: String?, // e.g. 7:00 pm ET or Final
            @SerialName("gameTimeUTC")
            val gameTime: String, // e.g. 2022-12-15T00:00:00Z
            @SerialName("gameLeaders")
            val gameLeaders: GameLeaders?,
            @SerialName("teamLeaders")
            val teamLeaders: GameLeaders?,
            @SerialName("homeTeam")
            val homeTeam: GameTeam?,
            @SerialName("awayTeam")
            val awayTeam: GameTeam?
        ) {
            @Serializable
            data class GameLeaders(
                val homeLeaders: GameLeader?,
                val awayLeaders: GameLeader?
            ) {
                @Serializable
                data class GameLeader(
                    val personId: Int, // e.g. 1626179
                    val name: String, // e.g. Terry Rozier
                    val jerseyNum: String, // e.g. 3
                    val position: String, // e.g. G
                    val teamTricode: String, // e.g. CHA
                    val points: Double, // e.g. 如果比賽已經結束： 22，否則：22.2
                    val rebounds: Double, // e.g. 如果比賽已經結束： 22，否則：22.2
                    val assists: Double // e.g. 如果比賽已經結束： 22，否則：22.2
                )
            }

            @Serializable
            data class GameTeam(
                val losses: Int, // 敗場場次(從這場之前), e.g. 2
                val score: Int, // 比分, e.g. 100
                val teamCity: String, // 城市名, e.g. LA
                val teamId: Int, // 隊伍id, e.g. 1610612746
                val teamName: String, // 隊伍名稱, e.g. Clippers
                val teamTricode: String, // 隊伍縮寫名稱, e.g. LAC
                val wins: Int, // 勝場場次(從這場之前), e.g. 2
                val periods: List<Period>
            ) {
                @Serializable
                data class Period(
                    val period: Int?, // 第幾節, e.g. 1
                    val periodType: String?, // e.g. REGULAR or OVERTIME
                    val score: Int? // 得分, e.g 20
                )
            }
        }
    }
}